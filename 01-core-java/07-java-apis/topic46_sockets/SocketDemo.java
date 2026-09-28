package topic46_sockets;

import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/*
 * Topic    : Server and clients in one program
 * Key idea : the server runs on a background thread; two clients talk to it AT THE SAME TIME.
 *            Port 0 asks the operating system for any free port, so this never clashes.
 * Run      : java -cp out topic46_sockets.SocketDemo
 * Try this : change the pool size in Server.serve to 1 and see the clients wait for each other.
 */
public class SocketDemo {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            int port = serverSocket.getLocalPort();
            System.out.println("server on port " + port);

            Thread server = new Thread(() -> {
                try {
                    Server.serve(serverSocket, 2);          // serve exactly two clients, then stop
                } catch (Exception e) {
                    System.err.println("server failed: " + e.getMessage());
                }
            });
            server.start();

            CompletableFuture<List<String>> asha = CompletableFuture.supplyAsync(() -> talk(port, "I'm Asha"));
            CompletableFuture<List<String>> ravi = CompletableFuture.supplyAsync(() -> talk(port, "I'm Ravi"));
            System.out.println("asha got: " + asha.join());
            System.out.println("ravi got: " + ravi.join());
            server.join();
        }
    }

    private static List<String> talk(int port, String message) {
        try {
            return Client.talk("localhost", port, List.of(message));
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
    }
}
