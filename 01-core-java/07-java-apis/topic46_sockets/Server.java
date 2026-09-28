package topic46_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Topic    : A multithreaded server (step 2 of the "build a web server" tutorial)
 * Key idea : ServerSocket.accept() waits for the next client and returns a Socket for it.
 *            Step 1 served one client at a time. Here each client is handed to a thread pool,
 *            so one slow client no longer blocks everyone else.
 * Run      : java -cp out topic46_sockets.Server            (listens on port 8010, stop with Ctrl+C)
 *            then, in another terminal: java -cp out topic46_sockets.Client
 * Next     : SocketDemo runs server and clients together in one program
 */
public class Server {

    static final int PORT = 8010;

    // Serves clients from the given socket. maxClients < 0 means "forever".
    static void serve(ServerSocket serverSocket, int maxClients) throws IOException {
        try (ExecutorService pool = Executors.newFixedThreadPool(10)) {
            for (int served = 0; maxClients < 0 || served < maxClients; served++) {
                Socket client = serverSocket.accept();          // blocks until a client connects
                pool.submit(() -> handle(client));               // one pool thread per connected client
            }
        }                                                        // waits for the running clients to finish
    }

    // Talks to ONE client: echoes each line until the client says "bye".
    static void handle(Socket client) {
        // try-with-resources closes the socket, reader and writer even if something fails
        try (client;
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {   // true = flush on println
            out.println("Hello from the server (" + Thread.currentThread().getName() + ")");
            String line;
            while ((line = in.readLine()) != null && !line.equals("bye")) {
                out.println("echo: " + line);
            }
            out.println("goodbye");
        } catch (IOException e) {
            System.err.println("client connection failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);
            serve(serverSocket, -1);
        }
    }
}
