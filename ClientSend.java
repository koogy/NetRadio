import java.net.*;
import java.io.*;

/* Client envoyant un message a un diffuseur. Le port doit etre specifie en argument à l'execution */

public class ClientSend{
    static String id;
    static String mess;
    public static void main(String[] argv){
	id = "ID1";
	mess = "message";
	//Rempli id jusqu'à 8 caracteres avec #
	for(int i=id.length();i<8;i++){
	    id += "#";
	}
	//Rempli mess jusqu'à 140 caracteres avec #
	for(int i=mess.length();i<140;i++){
	    mess += "#";
	}
        try{
	    int port=(Integer.parseInt(argv[0]));
	    Socket socket=new Socket("localhost", port);
	    BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	    //156 octets à récupérer dans le diffuseur du coup (si je me trompe pas)
	    pw.print("MESS" + " " + id + " " + mess + "\n");
	    String recu=br.readLine();
            System.out.println("Message du diffuseur : " + recu);
	    pw.flush();    
            pw.close();
            br.close();
	    socket.close();
	}
	catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
	}
    }
}
