package Diffuseur;

import java.net.*;

import java.io.*;

public class ReceptionTCP implements Runnable {

    Diffuseur diffuseur;

    public ReceptionTCP(Diffuseur diffuseur) {
        this.diffuseur = diffuseur;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(diffuseur.multidiffusion_port);
            while (true) {
                Socket socket = server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                String message = br.readLine();
                System.out.println("Message recu :" + message);
                pw.println(message);
                pw.flush();
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
