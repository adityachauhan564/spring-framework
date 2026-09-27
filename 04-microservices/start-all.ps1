# Starts the whole microservice system in the right order and waits until it is ready.
#   .\start-all.ps1        (from 04-microservices, in PowerShell)
#   .\stop-all.ps1         stops everything again
# Logs go to logs\<service>.log. Needs JDK 21; the Maven wrapper downloads everything else.
$ErrorActionPreference = 'Stop'
Set-Location $PSScriptRoot
New-Item -ItemType Directory -Force logs | Out-Null

function Wait-For($name, $url, $text, $seconds) {
    Write-Host ("  waiting for {0,-34}" -f "$name ...") -NoNewline
    for ($i = 0; $i -lt $seconds; $i++) {
        try {
            $body = (Invoke-WebRequest -Uri $url -UseBasicParsing -TimeoutSec 2).Content
            if ($body -match $text) { Write-Host ' ready'; return }
        } catch { }
        Start-Sleep -Seconds 1
    }
    Write-Host " NOT ready after ${seconds}s - see logs\"
}

function Start-Service($name, $folder, $jar, $extraArgs = @()) {
    # each service starts from ITS OWN folder (the config server finds ..\git-local-config-repo that way)
    Start-Process java -ArgumentList (@('-jar', "target\$jar") + $extraArgs) -WorkingDirectory $folder `
        -RedirectStandardOutput "logs\$name.log" -RedirectStandardError "logs\$name.err.log" -WindowStyle Hidden | Out-Null
}

Write-Host '1/3 Building all services (first run downloads dependencies)...'
.\mvnw.cmd -q -B package -DskipTests
if ($LASTEXITCODE -ne 0) { throw 'build failed' }

Write-Host '2/3 Starting services in order...'
Start-Service config-server spring-cloud-config-server spring-cloud-config-server-0.0.1-SNAPSHOT.jar
Wait-For 'config server :8888' http://localhost:8888/limit-service-microservices/dev propertySources 90

Start-Service naming-server naming-server naming-server-0.0.1-SNAPSHOT.jar
Wait-For 'naming server (Eureka) :8761' http://localhost:8761/actuator/health UP 90

Start-Service limits-service limits-service limit-service-microservices-0.0.1-SNAPSHOT.jar
Start-Service currency-exchange-8000 currency-exchange-service currency-exchange-service-0.0.1-SNAPSHOT.jar @('--server.port=8000')
Start-Service currency-exchange-8001 currency-exchange-service currency-exchange-service-0.0.1-SNAPSHOT.jar @('--server.port=8001')
Start-Service currency-conversion currency-conversion-service currency-conversion-service-0.0.1-SNAPSHOT.jar
Wait-For 'limits-service :8080' http://localhost:8080/actuator/health UP 90
Wait-For 'currency-exchange :8000' http://localhost:8000/actuator/health UP 90
Wait-For 'currency-exchange :8001' http://localhost:8001/actuator/health UP 90
Wait-For 'currency-conversion :8100' http://localhost:8100/actuator/health UP 90

Start-Service api-gateway api-gateway api-gateway-0.0.1-SNAPSHOT.jar
Wait-For 'api-gateway :8765' http://localhost:8765/actuator/health UP 90

Write-Host '3/3 Waiting for Eureka to list the services (registration takes up to ~30 s)...'
Wait-For 'CURRENCY-EXCHANGE in Eureka' http://localhost:8761/eureka/apps/CURRENCY-EXCHANGE instanceId 90
Wait-For 'CURRENCY-CONVERSION-SERVICE' http://localhost:8761/eureka/apps/CURRENCY-CONVERSION-SERVICE instanceId 90
Wait-For 'gateway route to exchange' http://localhost:8765/currency-exchange/from/USD/to/INR conversionMultiple 90

Write-Host @'

All services are up. Try:
  curl.exe localhost:8080/limits
  curl.exe localhost:8765/currency-exchange/from/USD/to/INR                    (run twice: environment 8000 / 8001)
  curl.exe localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
  open http://localhost:8761 for the Eureka dashboard
Stop everything with .\stop-all.ps1
'@
