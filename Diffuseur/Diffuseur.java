package Diffuseur;
import java.io.*;
import java.util.ArrayList;

public class Diffuseur {

    String diffuseur_id;
    String multidiffusion_address;
    int multidiffusion_port;
    int tcp_port;

    ArrayList<String> diffuseur_messages = new ArrayList<String>();

    public Diffuseur(String filename) {
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

    public void populate_array() {
        for (int i = 0; i < 10; i++) {
            diffuseur_messages.add( i+ " " + diffuseur_id  +" C'est le numéro: " + Integer.toString(i));
        }
    }

    public void print_array() {
        for (String message : diffuseur_messages) {
            System.out.println("Message numéro : " + message);
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
        ReceptionTCP diff_tcp = new ReceptionTCP(this);
        DiffuseurUDP diff_udp = new DiffuseurUDP(this);

        Thread t_tcp = new Thread(diff_tcp);
        Thread t_udp = new Thread(diff_udp);

        t_tcp.start();
        t_udp.start();
    }

    public static void main(String[] args) {
        Diffuseur diffuseur = new Diffuseur(args[0]);
        diffuseur.display_diffuseur_information();
        diffuseur.populate_array();
        diffuseur.start_diffuseur();
    }

}
