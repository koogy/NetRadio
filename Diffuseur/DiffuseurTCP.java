package Diffuseur;

import java.util.LinkedList;
import java.net.*;
import java.util.Random;
import java.io.*;
import Messages.*;

public class DiffuseurTCP implements Runnable {
    Diffuseur diffuseur;

    public DiffuseurTCP(Diffuseur diffuseur) {
        this.diffuseur = diffuseur;
    }

    @Override
    public void run() {
        BufferedReader input_reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String user_input = input_reader.readLine();
                if (user_input.equals(MessageType.REGI.getValue())) {

                    Socket socket = new Socket("localhost", diffuseur.gestionnaire_port);

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                    Message.sendMessage(out,
                            MessageType.REGI.getValue() + " " + this.diffuseur.diffuseur_id + " "
                                    + this.diffuseur.multidiffusion_address + " "
                                    + Inet4Address.getLocalHost().getHostAddress() + " " + this.diffuseur.tcp_port);

                    System.out.println(in.readLine());
                    out.close();

                } else {
                    System.out.println("Unknown command");
                }

            } catch (Exception e) {
                System.out.println("Failed to connect to Gestionnaire");
            }

        }
    }

}
