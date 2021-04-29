

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


public class Testing {

    public static void main(String[] args) {
        /*
         * String nb_mess = "391"; nb_mess = ("000" +
         * nb_mess).substring(nb_mess.length()); System.out.println(nb_mess);
         * 
         * System.out.println(MessageType.ACKM.getValue());
         */

      /*   printFile("./Configs/client-config.txt");
        printFile("./Configs/diff-config.txt"); */

/*         Scanner sc = new Scanner(System.in);
        System.out.println(sc.nextLine()); */

        String nb_mess = "RADIO";
        nb_mess = (nb_mess +"########" ).substring(0,8); 
        System.out.println(nb_mess);
        }

    public static void printFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();

        }
    }
}
