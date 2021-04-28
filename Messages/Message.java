package Messages;
import java.io.*;

public class Message {

    public static String formatMessage(String message) {
        return message + "\n";
    }

    public static void sendMessage(PrintWriter out, String message) {
        out.print(formatMessage(message));
        out.flush();
    }

    public static String formatNumber(String nb_mess) {
        return ("000" + nb_mess).substring(nb_mess.length());
    }

}
