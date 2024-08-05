package simple_tcp_multithread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private final Socket socket;
    private final ServerMain serverMainThread;

    public ServerThread(Socket socket, ServerMain serverMain) {
        this.socket = socket;
        this.serverMainThread = serverMain;
    }

    @Override
    public void run() {
        try {
            int client_number = serverMainThread.getAndIncClientCounter();
            System.out.println("Client " + client_number + " has connected");

            // I/O buffers:
            BufferedReader inSocket = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter outSocket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

            outSocket.println("Welcome! You are client number " + client_number + ". What's your name? "); // send "Welcome!" to the Client
            String message = inSocket.readLine();
            System.out.println("client says: " + message); // display Client's message in the console

            socket.close();
            System.out.println("Client " + client_number + " has disconnected.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
