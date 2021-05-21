package Gestionnaire;
import java.io.*;
import java.net.*;
import Messages.*;
    /* Create empty list */
/*     struct DiffuseurList* head = NULL;
    push_to_list(&head,"REGI BOI 213.2130213.32");
    push_to_list(&head,"REGI GURL 213.2130213.32");
    push_to_list(&head,"REGI efa 213.2130213.32");
    push_to_list(&head,"REGI feaq 213.2130213.32");
 /*    delete(&head,3); */
/*     print_list(head); */
/*     printf("%d \n",getSize(head));
    printf("%d \n",canAdd(head)); */ 
public class Gestionnaire {

    int port;
    int max_size = 3;

    DiffuseurList diffuseur_list;
    public Gestionnaire(int port){
        this.port= port;
        diffuseur_list = new DiffuseurList();
    }
    

    public void start_gestionnaire() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            while (true) {
                Socket socket = server.accept();
                new Thread(){
                   public void run(){
                        try{
                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                            String message = in.readLine();
                            if (message != null) {
                                if (message.startsWith(MessageType.REGI.getValue())) {
                                    System.out.println(message);
                                    if(diffuseur_list.canAdd()){
                                        
                                        diffuseur_list.addDiffuseur(new DiffuseurInformation(message.substring(5, message.length()), socket));
                                        Message.sendMessage(out, MessageType.REOK.getValue());
                                    } else {
                                        in.close();
                                        out.close();
                                        socket.close();
                                    }
                                   
                                    
                                } else if (message.equals(MessageType.LIST.getValue())) {
                                    Message.sendMessage(out, MessageType.LINB.getValue()  + diffuseur_list.getSize());

                                    for( int i = 0 ; i < diffuseur_list.getSize();i++){
                                        String diffuseur_information = diffuseur_list.getList().get(i).getInformation();
                                        System.out.println(diffuseur_list.getList().get(i).getInformation());
                                        Message.sendMessage(out, MessageType.ITEM.getValue() + diffuseur_information);
                                       
                                    }
                                   /*  in.close();
                                    out.close();
                                    socket.close();
                                     */
                                }else if (message.startsWith(MessageType.MGES.getValue())) {
                                    String message_m = message.substring(5, message.length());
                                    for( int i = 0 ; i < diffuseur_list.getSize();i++){
                                        Socket diffuseur_socket = diffuseur_list.getList().get(i).getSocket();
                                        BufferedReader in_m = new BufferedReader(new InputStreamReader(diffuseur_socket.getInputStream()));
                                        PrintWriter out_m = new PrintWriter(new OutputStreamWriter(diffuseur_socket.getOutputStream()));
                                        Message.sendMessage(out_m, MessageType.MESS.getValue() + " " + message_m);
                                        String message_from_server = in.readLine();
                    					if (message_from_server.equals(MessageType.ACKM.getValue())) {
                    					    System.out.println(message_from_server);
                    					}
                                        in_m.close();
                    				    out_m.close();
                    				    diffuseur_socket.close();
                                    }
                                   /*  in.close();
                                    out.close();
                                    socket.close();
                                     */
                                } else {
                                    System.out.println("Message recu :" + message);
                                    Message.sendMessage(out,message);
                                }
            
                            }
            /* 
                            in.close();
                            out.close();
                            socket.close(); */
                        } catch (Exception e){

                        }
                    
                   }
                }.start();
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        Gestionnaire g = new Gestionnaire(6060);
        DiffuseurChecker dc = new DiffuseurChecker(g.diffuseur_list);
        Thread t_dc = new Thread(dc);
        t_dc.start();
        g.start_gestionnaire();
    }
}
