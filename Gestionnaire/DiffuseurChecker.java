package Gestionnaire;

import java.io.*;
import java.net.*;
import Messages.*;

public class DiffuseurChecker implements Runnable {

    DiffuseurList diffuseurList;

    public DiffuseurChecker(DiffuseurList diffuseurList) {
        this.diffuseurList = diffuseurList;
        /* diffuseurList.getList().add("DIFFBOI 225.1.2.4 DESKTOP-6MR9G5O/127.0.1.1 303"); */
    }

    public void run() {
        try {
            while (true) {
                int i = 0;

                /*  Mauvaise façon, à refaire, il faudrai plutot crée une liste contenant les sockets des diffuseurs ça a plus de sens */
                for (DiffuseurInformation d : diffuseurList.getList()) {
                    try {
                        Socket socket = d.getSocket();
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

                    } catch (SocketException  e) {
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}
