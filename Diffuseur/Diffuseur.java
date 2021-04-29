package Diffuseur;
import java.io.*;
import java.util.ArrayList;
import Messages.*;

public class Diffuseur {

    String diffuseur_id;
    String multidiffusion_address;
    int multidiffusion_port;
    int tcp_port;

    MessageDiffuseur diffuseur_messages;

    public Diffuseur(String filename) {
        diffuseur_messages = new MessageDiffuseur();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            this.diffuseur_id =reader.readLine();
            this.multidiffusion_address =reader.readLine();
            this.multidiffusion_port =Integer.parseInt(reader.readLine());
            this.tcp_port =Integer.parseInt(reader.readLine());
        
            reader.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();

        }
    }

 



    public void display_diffuseur_information() {
        System.out.println("---------------------------------------");
        System.out.println("[DIFFUSEUR ID] : " + this.diffuseur_id);
        System.out.println("[MULTIDIFUSION ADDRESS] : " + this.multidiffusion_address);
        System.out.println("[MULTIDIFUSION PORT] : " + this.multidiffusion_port);
        System.out.println("[TCP PORT] : " + this.tcp_port);
        System.out.println("---------------------------------------");
        System.out.println();
    }

    public void start_diffuseur() {
        ReceptionTCP reception_tcp = new ReceptionTCP(this);
        DiffuseurUDP diff_udp = new DiffuseurUDP(this);
        DiffuseurTCP diff_tcp = new DiffuseurTCP(this);

        Thread t_tcp = new Thread(reception_tcp);
        Thread t_udp = new Thread(diff_udp);
        Thread r_tcp = new Thread(diff_tcp);

        t_tcp.start();
        t_udp.start();
        r_tcp.start();
    }

    public static void main(String[] args) {
        Diffuseur diffuseur = new Diffuseur(args[0]);
        diffuseur.display_diffuseur_information();

        diffuseur.start_diffuseur();
    }

}
