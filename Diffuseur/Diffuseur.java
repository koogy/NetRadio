package Diffuseur;


public class Diffuseur {

    public static void main(String[] args) {
        int port = 3030;
        DiffuseurTCP diff_tcp = new DiffuseurTCP();
        DiffuseurUDP diff_udp = new DiffuseurUDP();

        Thread t_tcp = new Thread(diff_tcp);
        Thread t_udp = new Thread(diff_udp);

        t_tcp.start();
        t_udp.start();

    }
    
}
