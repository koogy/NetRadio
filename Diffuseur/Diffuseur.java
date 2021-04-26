package Diffuseur;

import java.util.ArrayList;

public class Diffuseur {

    String diffuseur_id;
    String multidiffusion_address;
    int multidiffusion_port;
    int tcp_port;

    ArrayList<String> diffuseur_messages = new ArrayList<String>();

    public Diffuseur(){
        this.diffuseur_id ="DIFFBOI";
        this.multidiffusion_address = "225.1.2.4";
        this.multidiffusion_port = 4040;
        this.tcp_port = 3035;
    }

    public void populate_array(){
        for(int i =  0; i < 10;i++){
            diffuseur_messages.add("C'est le numéro: " + Integer.toString(i));
        }
    }

    public void print_array(){
        for( String message : diffuseur_messages){
            System.out.println("Message numéro : " + message);
        }
    }

    public void display_diffuseur_information(){

    System.out.println("---------------------------------------");
    System.out.println("[DIFFUSEUR ID] : " + this.diffuseur_id);
    System.out.println("[MULTIDIFUSION ADDRESS] : " + this.multidiffusion_address);
    System.out.println("[MULTIDIFUSION PORT] : " + this.multidiffusion_port);
    System.out.println("[TCP PORT] : " + this.tcp_port);
    System.out.println("---------------------------------------");
    System.out.println();

    }

    public void start_diffuseur(){
        ReceptionTCP diff_tcp = new ReceptionTCP(this);
        DiffuseurUDP diff_udp = new DiffuseurUDP(this);

        Thread t_tcp = new Thread(diff_tcp);
        Thread t_udp = new Thread(diff_udp);

        t_tcp.start();
        t_udp.start();
    }
    public static void main(String[] args) {
        Diffuseur diffuseur = new Diffuseur();
        diffuseur.display_diffuseur_information();
        diffuseur.populate_array();
        diffuseur.start_diffuseur();
        
    }
    
}
