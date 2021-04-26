package Diffuseur;

import java.net.*;

import java.io.*;

public class ReceptionTCP implements Runnable {

    Diffuseur diffuseur;

    public ReceptionTCP(Diffuseur diffuseur){
        this.diffuseur = diffuseur;
    }

    @Override
    public void run() {
        try{
            ServerSocket server=new ServerSocket(3030);
            while(true){
                Socket socket=server.accept();
                BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    
                String mess=br.readLine();
                System.out.println("Message recu :"+mess);
                br.close();
                pw.close();
                socket.close();
            } }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
    
