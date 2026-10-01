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
 * Topic    : A server that can talk to many clients at once (step 2 of the "build a web server" tutorial)
 * Key idea : - A ServerSocket listens on a port, like a shop's phone number.
 *            - accept() waits for the next caller and gives you a Socket - one phone line to that caller.
 *            - Step 1 served one client at a time, so everybody else waited in line.
 *              Here each client is handed to a thread pool - like a shop with 10 helpers -
 *              so one slow customer doesn't block everyone else.
 * Run      : java -cp out topic46_sockets.Server            (listens on port 8010, stop it with Ctrl+C)
 *            then, in another terminal: java -cp out topic46_sockets.Client
 * Next     : SocketDemo runs the server and the clients together in one program
 */
public class Server {

    static final int PORT = 8010;

    // Serves clients on the given socket. maxClients < 0 means "keep going forever".
    static void serve(ServerSocket serverSocket, int maxClients) throws IOException {
        try (ExecutorService pool = Executors.newFixedThreadPool(10)) {           // 10 helpers
            for (int served = 0; maxClients < 0 || served < maxClients; served++) {
                Socket client = serverSocket.accept();          // waits here until a client connects
                pool.submit(() -> handle(client));               // give this client to a free helper thread
            }
        }                                                        // waits for the clients still being served
    }

    // Talks to ONE client: repeats back (echoes) each line, until the client says "bye".
    static void handle(Socket client) {
        // try-with-resources closes the socket, the reader and the writer, even if something fails
        try (client;
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));      // to READ what the client sends
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {   // to SEND to the client. true = send right away on println
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
