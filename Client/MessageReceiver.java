package Client;

import java.net.*;

public class MessageReceiver implements Runnable {

    Client client;

    public MessageReceiver(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            client.display_diffuseur_information();
            MulticastSocket socket = new MulticastSocket(client.multidiffusion_port);
            socket.joinGroup(InetAddress.getByName(client.multidiffusion_address));
            byte[] data = new byte[100];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            while (true) {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
<<<<<<< HEAD
<<<<<<< HEAD
                //System.out.println(message);
=======

                if(client.display){
                    System.out.println(message); 
                }
                
>>>>>>> 4b1dda7a8cb22d3583e35149041440595c487bc1
=======
                /* System.out.println(message); */
>>>>>>> parent of 4b1dda7... final version, need to add test file
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}