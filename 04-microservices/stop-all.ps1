# Stops every service started by start-all.ps1, found by the port it listens on.
foreach ($port in 8765, 8100, 8001, 8000, 8080, 8761, 8888) {
    $conn = Get-NetTCPConnection -LocalPort $port -State Listen -ErrorAction SilentlyContinue
    foreach ($processId in ($conn.OwningProcess | Select-Object -Unique)) {
        Stop-Process -Id $processId -Force -Confirm:$false
        Write-Host "stopped port $port (pid $processId)"
    }
}
Write-Host 'done'
