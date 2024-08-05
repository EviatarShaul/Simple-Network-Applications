package simple_tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() throws Exception{
        ServerSocket serverSocket = new ServerSocket(2020); // Opening a new port
        System.out.println("Port 2020 is opened");

        Socket socket = serverSocket.accept();
        System.out.println("Client " + socket.getInetAddress() + " has connected");

        // I/O buffers:
        BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outSocket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

        outSocket.println("Welcome!"); // send "Welcome!" to the Client
        String message = inSocket.readLine();
        System.out.println("client says: " + message); // display Client's message in the console

        socket.close();
        System.out.println("Socket is closed.");
    }

    public static void main(String[] args) {
        try {
            new Server();
        }catch (Exception e) {
            // TODO handle
            e.printStackTrace();
        }
    }
}