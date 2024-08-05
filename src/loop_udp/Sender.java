package loop_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender {

    public Sender() throws Exception {

        DatagramSocket socket = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;

        while (true) {
            // sending part
            System.out.println("Enter your message: ");
            String message = scanner.nextLine();
            if (message.toLowerCase().equals("exit"))
                flag = true;
            byte[] buffer = message.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("127.0.0.1"), 2020);

            socket.send(packet);
            System.out.println("Sent: " + message);

            // Receiving part
            buffer = new byte[1500]; // 1500 = MTU
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            // Transfer the data from buffer to message string
            message = new String(buffer).trim();
            System.out.println("Received: " + message);
            if (flag) {
                break;
            }
        }

    }

    public static void main(String[] args) {
        try {
            new Sender();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
