package Gestionnaire;
import java.io.*;
import java.net.*;
import Messages.*;

public class Gestionnaire {

    int port;
    int max_size = 3;

    DiffuseurList diffuseur_list;
    public Gestionnaire(int port){
        this.port= port;
        diffuseur_list = new DiffuseurList();
    }
    

    public void start_gestionnaire() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            while (true) {
                Socket socket = server.accept();
                new Thread(){
                   public void run(){
                        try{
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                            String message = in.readLine();
     
                            if (message != null) {
                                if (message.equals(MessageType.REGI.getValue())) {
                                    if(diffuseur_list.canAdd()){
                                        diffuseur_list.addDiffuseur(message.substring(5, message.length()));
                                        Message.sendMessage(out, MessageType.REOK.getValue());
                                    } else {
                                        Message.sendMessage(out, MessageType.RENO.getValue());
                                        in.close();
                                        out.close();
                                        socket.close();
                                    }
                                   
                                    
                                }  else {
                                    System.out.println("Message recu :" + message);
                                    Message.sendMessage(out,message);
                                }
            
                            }
            
                            in.close();
                            out.close();
                            socket.close();
                        } catch (Exception e){

                        }
                    
                   }
                }.start();
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        Gestionnaire g = new Gestionnaire(6060);
        DiffuseurChecker dc = new DiffuseurChecker(g);
        Thread t_dc = new Thread(dc);
        t_dc.start();
        g.start_gestionnaire();
    }
}
