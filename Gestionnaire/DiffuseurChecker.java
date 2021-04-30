package Gestionnaire;

import java.io.*;
import java.net.*;
import Messages.*;

public class DiffuseurChecker implements Runnable {

    DiffuseurList diffuseurList;

    public DiffuseurChecker(DiffuseurList diffuseurList) {
        this.diffuseurList = diffuseurList;

    }

    public void run() {
        try {
            while (true) {
                int i = 0;
                for (DiffuseurInformation d : diffuseurList.getList()) {
                    String[] diffuseur_informations = d.getInformation().split("\\s+");
                    System.out.println(diffuseur_informations[2] + diffuseur_informations[3]);

                   /*  Socket socket = new Socket(diffuseur_informations[2], Integer.parseInt(diffuseur_informations[3])); */
                    Socket socket = d.getSocket();
                    System.out.println(socket);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                    Message.sendMessage(out, MessageType.RUOK.getValue()); 
                    String message_server = in.readLine();
                    System.out.println("M : " + message_server);
                    if (!message_server.equals(MessageType.IMOK.getValue())) {
                        System.out.println("Deleting from list");
                        diffuseurList.getList().remove(i);
                        socket.close();
                    }

                    i++;
                }
                Thread.sleep(2000);
            }

            
        } catch (Exception e) {
         /*    System.out.println(e);
            e.printStackTrace(); */
        }
    }

}

