package Diffuseur;

import java.util.LinkedList;
import java.net.*;
import java.util.Random;

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
                int randomNum = rand.nextInt(diffuseur.diffuseur_messages.size());
                String diffuseur_message =  diffuseur.diffuseur_messages.get(randomNum);
                data = diffuseur_message.getBytes();
                InetSocketAddress ia = new InetSocketAddress(diffuseur.multidiffusion_address, diffuseur.tcp_port);
                DatagramPacket paquet = new DatagramPacket(data, data.length, ia);
                dso.send(paquet);
                Thread.sleep(2000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}