import java.net.*;
import java.io.*;
import java.util.*;

/* Gestionnaire incomplet */

public class Gestionnaire{
    public static void main(String[] argv){
	LinkedList< String> diffList = new LinkedList< String>();
        try{
	    int port=(Integer.parseInt(argv[0]));
	    ServerSocket server=new ServerSocket(port);
	    while(true){
		Socket socket=server.accept();
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		String recu=br.readLine();
		System.out.println("Message recu : " + recu);
		if(recu.substring(0,4).equals("REGI") && diffList.size()<100){
		    diffList.add(recu.substring(5));
		    pw.print("REOK");
		    pw.flush();
		}else{
		    pw.print("RENO");
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
