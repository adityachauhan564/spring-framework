import java.io.*;
import java.net.*;

public class Server{

    public void run() throws SocketException, IOException{
        int port =8010;

        ServerSocket socket=new ServerSocket(port);  // ServerSocket is a predefined class 
        socket.setSoTimeout(10000);

        while(true){
            try{

                System.out.println("Server is Listening on port "+port);
                Socket acceptedConnection=socket.accept();
                System.out.println("Connection accepted from client "+acceptedConnection.getRemoteSocketAddress());
                
                PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient =new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello from the Server");
                
            } catch(IOException ex){
            }
        }
    }

    public static void main(String [] args){

        Server server=new Server();

        try{
            server.run();
        } catch(Exception ex){
        }
       
        System.out.println("Hello Server");
    }
}