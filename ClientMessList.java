import java.net.*;
import java.io.*;

/* Client demandant la liste des nb_mess derniers messages au diffuseur */

public class ClientMessList{
    static String nb_mess;
    public static void main(String[] argv){
	nb_mess = "003";
        try{
	    int port=(Integer.parseInt(argv[0]));
	    Socket socket=new Socket("localhost", port);
	    BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	    pw.print("LAST" + " " + nb_mess + "\n");
	    pw.flush();
	    String recu=br.readLine();
	    while(!recu.equals("ENDM")){
            System.out.println("Message du diffuseur : " + recu);
	    recu=br.readLine();
	    }    
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
