package topic46_sockets;

import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/*
 * Topic    : Server and clients together in one program
 * Key idea : The server runs on a background thread, and two clients talk to it AT THE SAME TIME.
 *            Port 0 means "operating system, give me any free port" - so it never clashes
 *            with something else already running on your computer.
 * Run      : java -cp out topic46_sockets.SocketDemo
 * Try this : Change the pool size in Server.serve to 1 and watch the clients wait for each other.
 */
public class SocketDemo {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            int port = serverSocket.getLocalPort();          // which free port did we get?
            System.out.println("server on port " + port);

            Thread server = new Thread(() -> {
                try {
                    Server.serve(serverSocket, 2);          // serve exactly two clients, then stop
                } catch (Exception e) {
                    System.err.println("server failed: " + e.getMessage());
                }
            });
            server.start();

            // two clients, running at the same time
            CompletableFuture<List<String>> asha = CompletableFuture.supplyAsync(() -> talk(port, "I'm Asha"));
            CompletableFuture<List<String>> ravi = CompletableFuture.supplyAsync(() -> talk(port, "I'm Ravi"));
            System.out.println("asha got: " + asha.join());
            System.out.println("ravi got: " + ravi.join());
            server.join();
        }
    }

    // helper: Client.talk throws a checked IOException, which a lambda can't throw - so wrap it in an unchecked one
    private static List<String> talk(int port, String message) {
        try {
            return Client.talk("localhost", port, List.of(message));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
    }
}
