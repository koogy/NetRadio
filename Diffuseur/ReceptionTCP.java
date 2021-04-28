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
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                String message = in.readLine();
                /*
                 * Un diffuseur peut de plus recevoir des messages d’utilisateurs qu’il pourra
                 * diffuser
                 */
                if (message != null) {
                    if (message.startsWith("MESS ")) {
                        diffuseur.diffuseur_messages.add(message.substring(5, message.length()));
                        Message.sendMessage(out, message);
                        
                    } else if (message.startsWith("LAST ")) {
                        /*
                         * nb-mess est un entier (compris entre 0 et 999) : +3 Ne fonctionne pas avec 8
                         * pour le moment
                         */
                        /* Todo check number is >0 and <= 999 */
                        int nb_mess = Integer.parseInt(message.substring(5, 6));
                        
                        while (nb_mess != 0 && nb_mess < diffuseur.diffuseur_messages.size()) {
                            Message.sendMessage(out, diffuseur.diffuseur_messages
                                    .get(diffuseur.diffuseur_messages.size() - nb_mess));
                            
                            nb_mess -= 1;
                        }
                        out.print("EDM\n\r");
                        out.flush();
                    } else {
                        System.out.println("Message recu :" + message);
                        Message.sendMessage(out,message);
                    }

                }

                in.close();
                out.close();
                socket.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
