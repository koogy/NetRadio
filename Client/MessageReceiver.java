package Client;
import java.io.*;
import java.net.*;

public class MessageReceiver implements Runnable {
    int port;
    public MessageReceiver(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            MulticastSocket socket = new MulticastSocket(this.port);
            socket.joinGroup(InetAddress.getByName("225.1.2.4"));
            byte[] data = new byte[100];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            while (true) {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}