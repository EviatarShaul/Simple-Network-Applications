package simple_tcp_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            System.out.println("Client has connected");

            // I/O buffers:
            BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outSocket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

            outSocket.println("Welcome! What's your name? "); // send "Welcome!" to the Client
            String message = inSocket.readLine();
            System.out.println("client says: " + message); // display Client's message in the console

            socket.close();
            System.out.println("Socket is closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
