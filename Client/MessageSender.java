package Client;
import java.io.*;
import java.net.*;

/* C'est du TCP, il faudra crée une socket socket pour TCP dans le diffuseur */

public class MessageSender implements Runnable {

    int port;
    public MessageSender(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket socket = new Socket("localhost", this.port);

               BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
               BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
               String userInput;
               userInput = stdIn.readLine();
               pw.println(userInput);
            
               pw.flush();
               pw.close();
               br.close();
               socket.close();
           } catch (Exception e) {
               e.printStackTrace();
           }

       }
        }
           


    }

