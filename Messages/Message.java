package Messages;
import java.io.*;

public class Message {


    public static String formatMessage(String message) {
        return message + "\r\n";
    }

    public static void sendMessage(PrintWriter out, String message) {
        out.print(formatMessage(message));
        out.flush();
    }

    public static String formatNumber(int nb_mess,String zero) {
        return formatNumber(Integer.toString(nb_mess),zero);
    }

    public static String formatNumber(String nb_mess,String zero) {
        return (zero + nb_mess).substring(nb_mess.length());
    }

    public static String formatID(String id) {
        return id = (id +"########").substring(0,8);
    }

    public static String completeMessage(String message){
        if(message.length() > 140){
            return message.substring(0,140);
        }
        String temp = "";
        for(int i = 0 ; i < 140;i++){
            temp +="#";
        }

        return (message + temp).substring(0,140);

    }

}
