package Gestionnaire;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Messages.*;

public class DiffuseurChecker implements Runnable {

    DiffuseurList diffuseurList;

    public DiffuseurChecker(DiffuseurList diffuseurList) {
        this.diffuseurList = diffuseurList;

    }

    public void run() {

        while (true) {
            String message_server = "";
            int i = 0;
            boolean isOff = false;
            Socket socket = new Socket();
            try {

                for (DiffuseurInformation d : diffuseurList.getList()) {
                    String[] diffuseur_informations = d.getInformation().split("\\s+");
                    socket = new Socket(diffuseur_informations[2], Integer.parseInt(diffuseur_informations[3]));
                    socket.setSoTimeout(100000000);

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                    Message.sendMessage(out, MessageType.RUOK.getValue());
                    message_server = in.readLine();

                    System.out.println("M : " + message_server);
                    if (!message_server.equals(MessageType.IMOK.getValue())) {
                        isOff = true;
                    }

                    i++;
                }
                Thread.sleep(2000);
            } catch (Exception e) {
                try {
                    System.out.println("Deleting from list");
                    diffuseurList.getList().remove(i);
                    socket.close();
                    isOff = false;

                } catch (Exception ex) {

                }

                continue;
            }

        }

    }
}

/*
 * Socket -> non bloquant -> Envoyé le message au tcp -> attendre -> read -> si
 * vide bye bye
 */