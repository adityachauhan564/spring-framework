#!/usr/bin/env bash
# Starts the whole microservice system in the right order and waits until it is ready.
#   ./start-all.sh          (from 04-microservices; Git Bash, macOS or Linux)
#   ./stop-all.sh           stops everything again
# Logs go to logs/<service>.log. Needs JDK 21; the Maven wrapper downloads everything else.
set -euo pipefail
cd "$(dirname "$0")"
mkdir -p logs

wait_for() {                       # wait_for <name> <url> <text-to-find> <seconds>
  local name=$1 url=$2 text=$3 deadline=$((SECONDS + $4))
  printf "  waiting for %-34s" "$name ..."
  while [ "$SECONDS" -lt "$deadline" ]; do
    if curl -s --max-time 2 "$url" 2>/dev/null | grep -qi "$text"; then echo " ready"; return 0; fi
    sleep 1
  done
  echo " NOT ready after $4s - see logs/"; return 1
}

start() {                          # start <log-name> <folder> <jar> [extra args...]
  local name=$1 folder=$2 jar=$3; shift 3
  # each service starts from ITS OWN folder (the config server finds ../git-local-config-repo that way)
  (cd "$folder" && nohup java -jar "target/$jar" "$@" > "../logs/$name.log" 2>&1 &)
}

echo "1/3 Building all services (first run downloads dependencies)..."
./mvnw -q -B package -DskipTests

echo "2/3 Starting services in order..."
start config-server       spring-cloud-config-server   spring-cloud-config-server-0.0.1-SNAPSHOT.jar
# the config server has no Actuator: "ready" = it serves a config file
wait_for "config server :8888"      http://localhost:8888/limit-service-microservices/dev propertySources 90

start naming-server       naming-server                naming-server-0.0.1-SNAPSHOT.jar
wait_for "naming server (Eureka) :8761" http://localhost:8761/actuator/health UP 90

start limits-service      limits-service               limit-service-microservices-0.0.1-SNAPSHOT.jar
start currency-exchange-8000 currency-exchange-service currency-exchange-service-0.0.1-SNAPSHOT.jar --server.port=8000
start currency-exchange-8001 currency-exchange-service currency-exchange-service-0.0.1-SNAPSHOT.jar --server.port=8001
start currency-conversion currency-conversion-service  currency-conversion-service-0.0.1-SNAPSHOT.jar
wait_for "limits-service :8080"      http://localhost:8080/actuator/health UP 90
wait_for "currency-exchange :8000"   http://localhost:8000/actuator/health UP 90
wait_for "currency-exchange :8001"   http://localhost:8001/actuator/health UP 90
wait_for "currency-conversion :8100" http://localhost:8100/actuator/health UP 90

start api-gateway         api-gateway                  api-gateway-0.0.1-SNAPSHOT.jar
wait_for "api-gateway :8765"         http://localhost:8765/actuator/health UP 90

echo "3/3 Waiting for Eureka to list the services (registration takes up to ~30 s)..."
wait_for "CURRENCY-EXCHANGE in Eureka"   http://localhost:8761/eureka/apps/CURRENCY-EXCHANGE instanceId 90
wait_for "CURRENCY-CONVERSION-SERVICE"   http://localhost:8761/eureka/apps/CURRENCY-CONVERSION-SERVICE instanceId 90
wait_for "gateway route to exchange"     http://localhost:8765/currency-exchange/from/USD/to/INR conversionMultiple 90

cat <<'EOF'

All services are up. Try:
  curl localhost:8080/limits
  curl localhost:8765/currency-exchange/from/USD/to/INR                       (run twice: environment 8000 / 8001)
  curl localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
  open http://localhost:8761 for the Eureka dashboard
Stop everything with ./stop-all.sh
EOF
