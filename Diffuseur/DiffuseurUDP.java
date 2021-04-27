package Diffuseur;

import java.util.LinkedList;
import java.net.*;
import java.util.Random;

public class DiffuseurUDP implements Runnable {

    static String idDiff; // Au plus 8 caractères

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
                String s = diffuseur.diffuseur_messages.get(randomNum);
                data = s.getBytes();
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