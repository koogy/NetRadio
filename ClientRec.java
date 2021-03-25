import java.io.*;
import java.net.*;

/* Client ecoutant des messages d'un diffuseur. Le port doit etre specifie en argument à l'execution */

public class ClientRec{
    public  static void main(String[] argv){
        try{
	    int port=(Integer.parseInt(argv[0]));
            MulticastSocket mso=new MulticastSocket(port);
            mso.joinGroup(InetAddress.getByName("225.1.2.4"));
	    //La taille d'un message DIFF
            byte[]data=new byte[161];
            DatagramPacket paquet=new DatagramPacket(data,data.length);
            while(true){
                mso.receive(paquet);
                String messDiff=new String(paquet.getData(),0,paquet.getLength());
                System.out.println(messDiff);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
