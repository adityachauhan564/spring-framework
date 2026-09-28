package topic46_sockets.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Solutions for topic46_sockets/Exercises.java
public class ExercisesSolution {

    static String respond(String request) {
        String[] parts = request.split(" ", 2);           // the command, then the rest
        return switch (parts[0]) {
            case "PING" -> "PONG";
            case "UPPER" -> parts.length > 1 ? parts[1].toUpperCase() : "";
            case "ADD" -> {
                String[] numbers = parts[1].split(" ");
                yield String.valueOf(Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]));
            }
            default -> "ERROR unknown command";
        };
    }

    static String ask(String host, int port, String request) throws IOException {
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {   // true: println flushes
            out.println(request);
            return in.readLine();                          // blocks until the server's line arrives
        }
    }

    public static void main(String[] args) throws Exception {
        check("PONG".equals(respond("PING")), "exercise 1 PING");
        check("HI".equals(respond("UPPER hi")) && "5".equals(respond("ADD 2 3")), "exercise 1 UPPER / ADD");
        check("ERROR unknown command".equals(respond("DANCE")), "exercise 1 unknown");

        try (ServerSocket serverSocket = new ServerSocket(0)) {
            Thread server = new Thread(() -> answerOne(serverSocket));
            server.start();
            check("PONG".equals(ask("localhost", serverSocket.getLocalPort(), "PING")), "exercise 2");
            server.join();
        }
        System.out.println("All exercises pass");
    }

    private static void answerOne(ServerSocket serverSocket) {
        try (Socket client = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            out.println(respond(in.readLine()));
        } catch (IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
