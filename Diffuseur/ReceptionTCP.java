package Diffuseur;

import java.net.*;

import java.io.*;

public class ReceptionTCP implements Runnable {

    Diffuseur diffuseur;

    public ReceptionTCP(Diffuseur diffuseur) {
        this.diffuseur = diffuseur;
    }

    public String formatMessage(String message) {
        return message + "\r\n";
    }

    public void sendMessage(PrintWriter pw, String message) {
        pw.print(formatMessage(message));
        pw.flush();
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(diffuseur.tcp_port);
            while (true) {
                Socket socket = server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                String message = br.readLine();

                /*
                 * Un diffuseur peut de plus recevoir des messages d’utilisateurs qu’il pourra
                 * diffuser
                 */
                if (message != null) {
                    if (message.startsWith("MESS ")) {
                        diffuseur.diffuseur_messages.add(message.substring(5, message.length()));
                        sendMessage(pw,message);
                    }
                  
                }
               
                System.out.println("Message recu :" + message);
                sendMessage(pw,message);


                br.close();
                pw.close();
                socket.close();
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
