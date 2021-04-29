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
                for (String d : diffuseurList.getList()) {
                    String[] diffuseur_info = d.split("\\s+");
                    try {

                        Socket socket = new Socket(diffuseur_info[1], Integer.parseInt(diffuseur_info[3]));
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                        Message.sendMessage(out, MessageType.RUOK.getValue());

                        /* Add timeout function here ... */
                        String message_server = in.readLine();
                        if (!message_server.equals(MessageType.IMOK.getValue())) {
                            diffuseurList.getList().remove(i);
                            socket.close();
                        }
                        i++;

                    } catch (Exception e) {

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
