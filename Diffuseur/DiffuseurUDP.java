package Diffuseur;

import java.util.LinkedList;
import java.net.*;
import java.util.Random;

import Messages.*;

public class DiffuseurUDP implements Runnable {

    Diffuseur diffuseur;

    public DiffuseurUDP(Diffuseur diffuseur) {
        this.diffuseur = diffuseur;
    }

    @Override
    public void run() {
        try {
            Random rand = new Random();
            DatagramSocket dso = new DatagramSocket();
            byte[] data;
            while (true) {
                String diffuseur_message = "";
                diffuseur_message = MessageType.DIFF.getValue();
                diffuseur_message += Message.formatNumber(diffuseur.diffuseur_messages.getNum_mess(), "0000") + " ";
                diffuseur_message += diffuseur.diffuseur_messages.getMessage();

                diffuseur.messages_sent.add(diffuseur_message.substring(5));

                data = diffuseur_message.getBytes();
                InetSocketAddress ia = new InetSocketAddress(diffuseur.multidiffusion_address, diffuseur.tcp_port);
                DatagramPacket paquet = new DatagramPacket(data, data.length, ia);
                dso.send(paquet);
                Thread.sleep(3000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}