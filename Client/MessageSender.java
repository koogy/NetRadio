package Client;

import java.io.*;
import java.net.*;
import Messages.MessageType;
import Messages.Message;

public class MessageSender implements Runnable {

    Client client;

    public MessageSender(Client client) {
        this.client = client;
    }

    /* need function to check validity of a message */
    @Override
    public void run() {
        BufferedReader input_reader = new BufferedReader(new InputStreamReader(System.in));
        boolean validMessage = false;
        MessageType last_message_type = MessageType.NONE;

        try {
            while (true) {
                Socket socket = new Socket(client.diffuseur_address, client.tcp_port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println("------\n" + "Hello babe");
                String message ="";
                String user_input ="";
                if (last_message_type == MessageType.NONE) {
                    user_input = input_reader.readLine();
                    if (user_input.length() > 5) {
                         message = user_input.substring(5, user_input.length());
                         String test = "a";
                        if (user_input.startsWith("MESS ")) {
                            Message.sendMessage(out,"MESS " + client.client_id + " " + message);
                            validMessage = true;
                            last_message_type = MessageType.MESS;

                        } else if (user_input.startsWith("LAST ")) {
                            try{
                                /* Quel comportement si nb > au nombre de message dans le diffuseur ? */
                                int nb = Integer.parseInt(message);
                                if(nb<0 || nb > 999){
                                    throw new NumberFormatException();
                                }
                            } catch (NumberFormatException nb){
                                System.out.println("Wrong format : LAST␣nb-mess where nb-mess >=0 and nb-mess <= 999");
                                out.close();
                                socket.close();
                                continue;                            
                            }
                            Message.sendMessage(out,"LAST " + Message.formatNumber(message));
                            validMessage = true;
                            last_message_type = MessageType.LAST;
                        } else {
                            System.out.println("Unknown command");
                            validMessage = false;
                        }
                    }
                }
                    
                if (last_message_type == MessageType.LAST) {
                        System.out.println("In LAST");
                        String message_from_server = in.readLine();
                        while (!message_from_server.equals("EDM")) {
                            System.out.println("Message from server : " + message_from_server);
                            message_from_server = in.readLine();
                        }
    
                        System.out.println("Set flag to true");
                        last_message_type = MessageType.NONE;
                } else {
                    if(validMessage){
                        String message_from_server = in.readLine();
                        System.out.println("Message from server : " + message_from_server);
                        last_message_type = MessageType.NONE;
                    }
               
                }
                

           
                System.out.println("Bye babe");

                out.close();
                socket.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
