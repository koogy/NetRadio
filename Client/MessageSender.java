package Client;

import java.io.*;
import java.net.*;

public class MessageSender implements Runnable {

    Client client;

    public MessageSender(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        BufferedReader input_reader = new BufferedReader(new InputStreamReader(System.in));
        boolean validMessage = false;
        try {
            while (true) {
                Socket socket = new Socket(client.diffuseur_address, client.tcp_port);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                String user_input = input_reader.readLine();
                if (user_input.length() > 5) {
                    String message = user_input.substring(5, user_input.length());
                    if (user_input.startsWith("MESS ")) {
                        pw.print("MESS " + client.client_id + " " + message + "\n\r");
                        pw.flush();
                        validMessage = true;
                    } else if (user_input.startsWith("LAST ")) {
                        pw.print("LAST " + message + "\n\r");
                        pw.flush();
                        validMessage = true;
                    } else {
                        validMessage = false;
                    }
                }

                if (validMessage) {
                    System.out.println(br.readLine());
                }

                pw.close();
                socket.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
