package topic46_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Exercises for topic 46.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic46_sockets.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. The server's "protocol" - the rules for how it answers one request line:
    //      "PING"          -> "PONG"
    //      "UPPER <text>"  -> the text in capital letters   ("UPPER hi" -> "HI")
    //      "ADD <a> <b>"   -> the sum                       ("ADD 2 3" -> "5")
    //      anything else   -> "ERROR unknown command"
    static String respond(String request) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Send ONE request line to host:port, and return the one line the server replies.
    //    Remember: without a flush, your request never actually leaves your side.
    static String ask(String host, int port, String request) throws IOException {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    public static void main(String[] args) throws Exception {
        check("PONG".equals(respond("PING")), "exercise 1 PING");
        check("HI".equals(respond("UPPER hi")) && "5".equals(respond("ADD 2 3")), "exercise 1 UPPER / ADD");
        check("ERROR unknown command".equals(respond("DANCE")), "exercise 1 unknown");

        // exercise 2 is tested against a real server that uses YOUR respond()
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            Thread server = new Thread(() -> answerOne(serverSocket));
            server.start();
            check("PONG".equals(ask("localhost", serverSocket.getLocalPort(), "PING")), "exercise 2");
            server.join();
        }
        System.out.println("All exercises pass");
    }

    // a tiny server that answers just one client, using respond()
    private static void answerOne(ServerSocket serverSocket) {
        try (Socket client = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            out.println(respond(in.readLine()));
        } catch (IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
