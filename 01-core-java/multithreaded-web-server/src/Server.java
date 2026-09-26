import java.io.*;
import java.net.*;

// Step 1 of the tutorial: a single-threaded server that greets each client.
// Run: java Server.java   then in another terminal: telnet localhost 8010 (or curl --http0.9 localhost:8010)
public class Server{

    public void run() throws IOException{
        int port =8010;

        try (ServerSocket socket=new ServerSocket(port)) {  // ServerSocket is a predefined class
            while(true){
                System.out.println("Server is Listening on port "+port);
                try (Socket acceptedConnection=socket.accept();
                     PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream(), true)) { // true = auto-flush on println
                    System.out.println("Connection accepted from client "+acceptedConnection.getRemoteSocketAddress());
                    toClient.println("Hello from the Server");
                } catch(IOException ex){
                    System.err.println("Client connection failed: "+ex.getMessage());
                }
            }
        }
    }

    public static void main(String [] args){

        Server server=new Server();

        try{
            server.run();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
