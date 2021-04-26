package Client;
import java.io.*;
import java.net.*;

public class Client {

    String client_id;
    String multidiffusion_address;
    int multidiffusion_port;
    String diffuseur_address;
    int tcp_port; 

    public Client(){
        this.client_id ="CLIENTBOI";
        this.multidiffusion_address = "225.1.2.4";
        this.multidiffusion_port = 4040;
        this.diffuseur_address = "127.0.0.1";
        this.tcp_port = 3035;
    }

    public void start_client(){
        MessageReceiver receiver = new MessageReceiver(this);
        MessageSender sender = new MessageSender(this);
        System.out.println(multidiffusion_address);

        Thread t_receiver = new Thread(receiver);

        t_receiver.start();
    }

    
    public void display_diffuseur_information(){

        System.out.println("---------------------------------------");
        System.out.println("[CLIENT ID] : " + this.client_id);
        System.out.println("[MULTIDIFUSION ADDRESS] : " + this.multidiffusion_address);
        System.out.println("[MULTIDIFUSION PORT] : " + this.multidiffusion_port);
        System.out.println("[TCP PORT] : " + this.tcp_port);
        System.out.println("---------------------------------------");
        System.out.println();
    
        }

    public static void main(String[] args) {
        Client client = new Client();
        client.start_client();
    }
}