#!/usr/bin/env bash
# Stops every service started by start-all.sh, found by the port it listens on.
# (Killing by port finds the real Java process, even when the shell that started it is gone.)
PORTS="8765 8100 8001 8000 8080 8761 8888"

for port in $PORTS; do
  if command -v lsof >/dev/null 2>&1; then                     # macOS / Linux
    pids=$(lsof -ti tcp:"$port" -sTCP:LISTEN 2>/dev/null || true)
    [ -n "$pids" ] && kill $pids && echo "stopped port $port (pid $pids)"
  else                                                         # Git Bash on Windows
    pid=$(netstat -ano 2>/dev/null | grep -E "[:.]$port .*LISTENING" | awk '{print $NF}' | head -1)
    [ -n "$pid" ] && taskkill //F //PID "$pid" >/dev/null && echo "stopped port $port (pid $pid)"
  fi
done
echo "done"
