package Client;
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        /* DatagramSocket socket = new DatagramSocket(3030); */
        int port = 3030;
        MessageReceiver receiver = new MessageReceiver(port);
        MessageSender sender = new MessageSender(port);

        Thread t_receiver = new Thread(receiver);
        Thread t_sender = new Thread(sender);

        t_receiver.start();
        t_sender.start();

    }
}