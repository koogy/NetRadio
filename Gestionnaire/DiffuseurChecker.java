package Gestionnaire;
import java.io.*;
import java.net.*;
import Messages.*;

public class DiffuseurChecker implements Runnable{

    Gestionnaire gestionnaire;

    public DiffuseurChecker(Gestionnaire gestionnaire){
        this.gestionnaire = gestionnaire;
    }

    public void run(){
        try {
            while (true) {
                new Thread(){
                   public void run(){
                            for(String d :  gestionnaire.diffuseur_list.getList()){
                                /* Socket socket = new Socket(client.diffuseur_address, client.tcp_port); */

                            }
               
                            
                    
                   }
                }.start();
              
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    
}

