package loop_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Receiver {

    public Receiver() throws Exception {
        DatagramSocket socket = new DatagramSocket(2020);
        System.out.println("Receiver is running.");

        String inMessage = "";
        while (!inMessage.toLowerCase().equals("exit")) {

            byte[] buffer = new byte[1500]; // 1500 = MTU - Maximum Transmission Unit
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet); // retrieving sender's message
            inMessage = new String(buffer).trim();
            System.out.println("Received: " + inMessage);
            InetAddress sendersAddress = packet.getAddress();
            int sendersPort = packet.getPort();
            String outMessage = "Ok.";
            buffer = outMessage.getBytes();
            packet = new DatagramPacket(buffer, buffer.length, sendersAddress, sendersPort);
            socket.send(packet);
            System.out.println("Sent: " + outMessage);
        }

    }

    public static void main(String[] args) {
        try {
            new Receiver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
