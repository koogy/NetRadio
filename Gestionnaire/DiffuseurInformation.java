package Gestionnaire;

import java.io.*;
import java.net.*;
import Messages.*;


public class DiffuseurInformation {

    Socket socket;
    String information;

    public DiffuseurInformation(String information,Socket socket){
        this.socket = socket;
        this.information = information;
    }

    public Socket getSocket(){
        return this.socket;
    }

    public String getInformation(){
        return this.information;
    }
    
}
