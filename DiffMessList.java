import java.net.*;
import java.io.*;
import java.util.*;

/* NON TERMINE : Diffuseur envoyant la liste des n derniers messages au client. */

public class DiffMessList{
    static String id;
    static String mess;
    public static void main(String[] argv){
        LinkedList< String> messDiff = new LinkedList< String>();
    	for(int j = 0 ; j < 100 ; j++){
	    	String mess = "Bonjour a toutes et a tous" + Integer.toString(j);
	    	if (mess.length() < 8){
	    		for(int i = mess.length(); i < 138 ; i++){	//Rempli mess jusqu'à 138 caracteres avec #
		    		mess += "#";
	    		}
		}
		mess = mess + "\r\n";	// mess a 140 caracteres comme attendu
		messDiff.add(mess);
	}
	String idDiff="idDiff02";
        try{
	    int port=(Integer.parseInt(argv[0]));
	    ServerSocket server=new ServerSocket(port);
	    while(true){
		Socket socket=server.accept();
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		//156 octets à récupérer dans le diffuseur du coup (si je me trompe pas)
		pw.print("MESS" + " " + id + " " + mess + "\n");
		String recu=br.readLine();
		if(recu.substring(0,4).equals("LAST")){
		    int taille=messDiff.size();
		    int nbMess=Integer.parseInt(recu.substring(5));
		    int pos;
		    if(nbMess>taille){
			pos=0;
		    }else{
			pos=taille-nbMess;
		    }
		    while(pos>=taille){
			String envoi="OLDM " + pos + 1 + " " + idDiff + " " + messDiff.get(pos);
			pw.print(envoi);
			pw.flush();
			pos++;
		    }
		}
		pw.print("ENDM");
		pw.flush();
		pw.close();
		br.close();
		socket.close();
	    }
	}
	catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
	}
    }
}
