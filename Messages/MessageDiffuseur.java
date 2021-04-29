package Messages;

import java.util.ArrayList;

public class MessageDiffuseur {
    ArrayList<String> diffuseur_messages;
    int num_mess = -1;
    
    public MessageDiffuseur(){
        diffuseur_messages = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            diffuseur_messages.add("C'est le numéro: " + Integer.toString(i));
        }
    }

    public int getSize(){
        return diffuseur_messages.size();
    }

    public int getNum_mess(){
        return num_mess;
    }

    public String getMessage(){
        num_mess +=1;
        return diffuseur_messages.get(num_mess % getSize());
    }

    public String getMessage(int index){
        return diffuseur_messages.get(index);
    }

    public void addMessage(String message){
        diffuseur_messages.add(message);
    }

    public void print_array() {
        for (String message : diffuseur_messages) {
            System.out.println("Le message : " + message);
        }
    }

    
}
