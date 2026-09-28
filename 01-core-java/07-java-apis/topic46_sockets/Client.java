package topic46_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : A client
 * Key idea : new Socket(host, port) connects to a server. Its output stream sends,
 *            its input stream receives. Lines need a flush, or nothing leaves the buffer.
 * Run      : java -cp out topic46_sockets.Client      (start the Server first)
 */
public class Client {

    // Connects, sends the messages then "bye", and returns every line the server answered.
    static List<String> talk(String host, int port, List<String> messages) throws IOException {
        List<String> replies = new ArrayList<>();
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {   // auto-flush on println
            replies.add(in.readLine());                    // the greeting
            for (String message : messages) {
                out.println(message);
                replies.add(in.readLine());                // wait for the answer to this line
            }
            out.println("bye");
            replies.add(in.readLine());                    // "goodbye"
        }
        return replies;
    }

    public static void main(String[] args) throws IOException {
        for (String reply : talk("localhost", Server.PORT, List.of("hi", "how are you?"))) {
            System.out.println("server: " + reply);
        }
    }
}
