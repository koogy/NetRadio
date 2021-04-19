import java.net.*;
import java.io.*;
import java.util.*;

/* Diffuseur envoyant la liste des n derniers messages au client, et recevant les messages d'un client. Compatible avec ClientMessList et ClientSend */

public class DiffMessList{
    static String id;
    static String mess;
    public static void main(String[] argv){
	//On met d'abord dans messDiff 100 messages
        LinkedList< String> messDiff = new LinkedList< String>();
    	for(int j = 1 ; j <=100 ; j++){
	    	String mess = "Bonjour a toutes et a tous " + Integer.toString(j);
	    	if (mess.length() < 138){
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
		//ne gere pas encore directement l'envoi de donnees en parallele
		//pw.print("MESS" + " " + id + " " + mess + "\n");
		//On lit le message du Client
		String recu=br.readLine();
		System.out.println("Message recu : " + recu);

		//Cas avec ClientMessList
		
		if(recu.substring(0,4).equals("LAST")){
		    int taille=messDiff.size();
		    int nbMess=Integer.parseInt(recu.substring(5));
		    int pos;
		    if(nbMess>taille){
			pos=0;
		    }else{
			pos=taille-nbMess;
		    }
		    System.out.println(pos);
		    while(pos<taille){
			int numMess=pos+1;
			String envoi="OLDM " + numMess + " " + idDiff + " " + messDiff.get(pos);
			pw.print(envoi);
			pw.flush();
			pos++;
		    }
		    pw.print("ENDM");
		    pw.flush();

		    //Cas avec ClientSend
		    
		}else if(recu.substring(0,4).equals("MESS")){
		    String nvMess=recu.substring(5);
		    System.out.println(nvMess);
		    //Du coup la je stock l'id avec le message, a voir si il faut pas faire mieux
		    messDiff.add(nvMess);
		    pw.print("ACKM");
		    pw.flush();
		}
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
