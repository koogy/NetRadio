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
                                if (message.startsWith(MessageType.REGI.getValue())) {
                                    System.out.println(message);
                                    if(diffuseur_list.canAdd()){
                                        
                                        diffuseur_list.addDiffuseur(new DiffuseurInformation(message.substring(5, message.length()), socket));
                                        Message.sendMessage(out, MessageType.REOK.getValue());
                                    } else {
                                        in.close();
                                        out.close();
                                        socket.close();
                                    }
                                   
                                    
                                } else if (message.equals(MessageType.LIST.getValue())) {
                                    Message.sendMessage(out, MessageType.LINB.getValue()  + diffuseur_list.getSize());

                                    for( int i = 0 ; i < diffuseur_list.getSize();i++){
                                        String diffuseur_information = diffuseur_list.getList().get(i).getInformation();
                                        System.out.println(diffuseur_list.getList().get(i).getInformation());
                                        Message.sendMessage(out, MessageType.ITEM.getValue() + diffuseur_information);
                                       
                                    }
                                   /*  in.close();
                                    out.close();
                                    socket.close();
                                     */
                                } else {
                                    System.out.println("Message recu :" + message);
                                    Message.sendMessage(out,message);
                                }
            
                            }
            /* 
                            in.close();
                            out.close();
                            socket.close(); */
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
        DiffuseurChecker dc = new DiffuseurChecker(g.diffuseur_list);
        Thread t_dc = new Thread(dc);
        t_dc.start();
        g.start_gestionnaire();
    }
}
