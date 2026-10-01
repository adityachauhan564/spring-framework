package topic46_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * Topic    : A client - the one who makes the call
 * Key idea : new Socket(host, port) connects to a server, like dialling a phone number.
 *            - Its output stream is your mouth: what you SEND.
 *            - Its input stream is your ear: what you RECEIVE.
 *            Text you send waits in a buffer until it is "flushed". Without a flush, nothing actually
 *            leaves - like typing a WhatsApp message and never pressing send.
 * Run      : java -cp out topic46_sockets.Client      (start the Server first)
 */
public class Client {

    // Connects, sends each message, then says "bye", and returns every line the server replied.
    static List<String> talk(String host, int port, List<String> messages) throws IOException {
        List<String> replies = new ArrayList<>();
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {   // true = flush automatically on every println
            replies.add(in.readLine());                    // the server says hello first
            for (String message : messages) {
                out.println(message);
                replies.add(in.readLine());                // wait for the reply to this line
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
