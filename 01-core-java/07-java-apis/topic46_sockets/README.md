# Topic 46 · Networking with sockets

**Difficulty:** Intermediate · **Needs:** [44 File I/O](../topic44_file_io/), [41 Executors](../../06-concurrency/topic41_executors_and_futures/) · **Next:** [47 Modern Java features](../../08-advanced-java/topic47_modern_java/)

## Why it matters
Every web server, database driver and microservice call is, at the bottom, two programs talking over a socket. Spring Boot hides this completely, which is why it's worth seeing once: a server waiting for connections, a client connecting, lines going back and forth, and one thread per client so a slow user doesn't block the others. It also explains a bug that confuses everyone once: the message that never arrives because it was never flushed.

## What you'll learn
- `ServerSocket` (listen, `accept`) and `Socket` (connect)
- Reading and writing lines over a socket, with flushing
- Serving many clients at once with a thread pool
- Closing sockets with try-with-resources

## Run it
From `07-java-apis` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic46_sockets.SocketDemo     # server + 2 clients in one program: the easy way to see it work

java -cp out topic46_sockets.Server         # or the real thing: terminal 1, stop with Ctrl+C
java -cp out topic46_sockets.Client         # terminal 2
```
This topic started as step 1 of a "build a multithreaded web server" tutorial. The server is now at step 2, with a thread per client.

## Key concepts
- **The server:** `new ServerSocket(port)` claims the port. `accept()` **blocks** until a client connects, and returns a `Socket` for that client.
- **The client:** `new Socket(host, port)` connects. `getOutputStream()` sends and `getInputStream()` receives.
- **Flushing:** a `PrintWriter` buffers its output. Without `flush()`, or `new PrintWriter(out, true)` for auto-flush on `println`, the other side receives **nothing**.
- **One thread per client:** handing each accepted socket to a pool keeps the `accept` loop free for the next client.
- **Port 0** asks the operating system for any free port, which is handy for tests.

## Exercises
`Exercises.java` (run `java -cp out topic46_sockets.Exercises`):
1. A small command protocol: `PING`, `UPPER`, `ADD`
2. A client method that asks a real server one question

## Common mistakes
- An unflushed `PrintWriter`, where both sides wait forever.
- Serving clients in the `accept` loop itself, one at a time.
- Not closing sockets, which leak until the program runs out of file handles.
- "Address already in use": another program, or a previous run, still holds the port.

## Related topics
- [41 Executors](../../06-concurrency/topic41_executors_and_futures/): the pool behind the server
- [03 Spring Boot](../../../03-spring-boot/): an embedded Tomcat does all of this for you

## Revision checklist
- [ ] I can explain why an unflushed writer sends nothing.
- [ ] I can say what `accept()` does, and why the server hands clients to a pool.
- [ ] I close sockets with try-with-resources.
