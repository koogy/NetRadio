package Diffuseur;

import java.net.*;

import java.io.*;
import Messages.*;

public class ReceptionTCP implements Runnable {

    Diffuseur diffuseur;

    public ReceptionTCP(Diffuseur diffuseur) {
        this.diffuseur = diffuseur;
    }

 
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(diffuseur.tcp_port);
            while (true) {
                Socket socket = server.accept();
                new Thread(){
                   public void run(){
                        try{
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                            String message = in.readLine();
                            if (message != null) {
                                if (message.startsWith(MessageType.MESS.getValue())) {
                                    diffuseur.diffuseur_messages.addMessage(message.substring(5, message.length()));
                                    Message.sendMessage(out, MessageType.ACKM.getValue());
                                    
                                } else if (message.startsWith(MessageType.LAST.getValue())) {
                               
                                    int nb_mess = Integer.parseInt(message.substring(5, 8));
                                    
                                    while (nb_mess != 0 && nb_mess < diffuseur.diffuseur_messages.getSize()) {
                                        Message.sendMessage(out, MessageType.OLDM.getValue() +(diffuseur.messages_sent
                                                .get(diffuseur.messages_sent.size() - nb_mess)));
                                        
                                        nb_mess -= 1;
                                    }
                                    Message.sendMessage(out, MessageType.ENDM.getValue());
                                
                                // MYOU serait donc un nouveau type de message, en reponse a MDIF
                                } else if (message.startsWith(MessageType.MDIF.getValue())) {
                                    
                                    int num_mess = Integer.parseInt(message.substring(5, 8));

                                    if (num_mess < diffuseur.messages_sent.size()){
                                        Message.sendMessage(out, MessageType.MYOU.getValue() +(diffuseur.messages_sent
                                                .get(num_mess + 1)));
                                    }
                                    else {
                                        System.out.println("[Asking for a message not multicasted yet, so last multicasted message send]");
                                        Message.sendMessage(out, MessageType.MYOU.getValue() +(diffuseur.messages_sent
                                                .get(diffuseur.messages_sent.size() - 1)));
                                    }

                             
                                } else if (message.equals(MessageType.RUOK.getValue())) {
                                    Message.sendMessage(out, MessageType.IMOK.getValue());
                                }
                                else {
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
}
