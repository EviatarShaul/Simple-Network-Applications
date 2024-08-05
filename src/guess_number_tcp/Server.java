package guess_number_tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    public Server() throws Exception{
        ServerSocket serverSocket = new ServerSocket(2020); // Opening a new port
        System.out.println("Port 2020 is opened");

        Socket socket = serverSocket.accept();
        System.out.println("Client " + socket.getInetAddress() + " has connected");

        // I/O buffers:
        BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outSocket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

        String message;
        int secretNumber = new Random().nextInt(1,11);
        do {
            outSocket.println("Guess a number [1-10]: ");
            message = inSocket.readLine();
        } while (!(Integer.parseInt(message) == secretNumber));

        outSocket.println("You Got it, number is " + secretNumber);
        System.out.println("Secret number is out, Exiting the App");

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