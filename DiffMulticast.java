import java.io.*;
import java.net.*;

// Très incomplet, c'est juste le début... Pour l'instant il ne diffuse que ses propres messages, mais il y a surement des erreurs : je n'ai pas encore testé...

public class DiffMulticast {
	static String idDiff;	// Au plus 8 caractères
    
    public  static void main(String[] argv){
    	String mess = "Bonjour a toutes et a tous";
    	if (mess.length() < 8)
    	{
    		for(int i = mess.length(); i < 138 ; i++){	//Rempli mess jusqu'à 138 caracteres avec #
	    		mess += "#";
    		}
		}
		mess = mess + "\r\n";	// mess a 140 caracteres comme attendu
    	int portRecep = (Integer.parseInt(argv[0]));	// Pour recevoir les messages des clients
    	int portMulticast = (Integer.parseInt(argv[1]));
    	idDiff = "idDiff1";
    	if (idDiff.length() < 8)
    	{
    		for(int i = idDiff.length(); i < 8 ; i++){	//Rempli idDiff jusqu'à 8 caracteres avec #
	    		idDiff += "#";
    		}
		}
        try{
            DatagramSocket dso = new DatagramSocket();
            byte[]data;
            for(int i = 0; i <= 9999; i++){
                String s = "DIFF " + i + " " + idDiff + " " + mess;
                data = s.getBytes();
                InetSocketAddress ia = new InetSocketAddress("225.1.2.4", portMulticast); // Juste pour le moment : il faudra ensuite ne plus stocker l'adresse en dur
                DatagramPacket paquet = new 
						DatagramPacket(data, data.length, ia);
                dso.send(paquet);
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}