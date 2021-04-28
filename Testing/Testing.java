package Testing;
import Messages.MessageType;
import Messages.Message;

public class Testing {
    
    public static void main(String [] args){
/*  */
        String nb_mess = "391";
        nb_mess = ("000" + nb_mess).substring(nb_mess.length());
        System.out.println(nb_mess);
/*  */
        System.out.println(MessageType.ACKM.getValue());
    }
}
