package simple_tcp_multithread;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public ServerMain() throws Exception {
        ServerSocket serverSocket = new ServerSocket(2020);
        System.out.println("Port 2020 is opened");


        // server always listen
        while (true) {
            Socket socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket, this);
            Thread thread = new Thread(serverThread);
            thread.start();
        }
    }

    private int clientCounter = 1;

    public int getAndIncClientCounter() {
        return clientCounter++;
    }

    public static void main(String[] args) {
        try {
            new ServerMain();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
