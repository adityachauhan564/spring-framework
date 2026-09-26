# Multithreaded Web Server (Step 1)

> Java socket programming from a YouTube "Complete Java Full Stack" series - step 1 of building a multithreaded server.

## What it teaches
- `ServerSocket` / `Socket` basics: listen, accept, write a response
- Try-with-resources for sockets and writers
- Why output must be flushed (`PrintWriter(out, true)` = auto-flush)

## Run it
No build file. From this folder (Java 11+ runs a single source file directly):

```bash
java src/Server.java
# in another terminal:
curl --http0.9 localhost:8010      # or: telnet localhost 8010
```

Expected reply: `Hello from the Server`. Stop the server with Ctrl+C.

## Read the code in this order
1. `src/Server.java` - the whole server
2. `src/Client.java` - empty, to be written next

## Revision notes
- `new ServerSocket(port)` binds the port; `accept()` blocks until a client connects and returns a `Socket` for that client.
- Without `flush()` (or auto-flush) a `PrintWriter` keeps data in its buffer and the client receives nothing - this was the original bug.
- Try-with-resources closes the socket even if writing throws, so connections don't leak.
- This version is **single-threaded**: clients are served one at a time. The next step is a thread (or `ExecutorService` thread pool) per accepted connection.
- The reply is not a valid HTTP response (no status line/headers), which is why `curl` needs `--http0.9`.

## Status
🚧 Partial - step 1 works (verified with curl in this cleanup). Not multithreaded yet, and `Client.java` is an empty class.
