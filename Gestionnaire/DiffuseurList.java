package Gestionnaire;
import java.io.*;
import java.net.*;
import Messages.*;

import java.util.ArrayList;

public class DiffuseurList {
    ArrayList<String> diffuseurs;
    int max_size = 5;

    public DiffuseurList(){
        diffuseurs = new ArrayList<String>();
    }

    public boolean canAdd(){
        if(diffuseurs.size()== max_size){
            return false;
        }

        return true;
    }

    public int getSize(){
        return diffuseurs.size();
    }

    public String getDiffuseur(int index){
        return diffuseurs.get(index);
    }

    public void addDiffuseur(String diffuseur){
        diffuseurs.add(diffuseur);
    }

    public void print_array() {
        for (String d : diffuseurs) {
            System.out.println(d);
        }
    }

    public ArrayList<String> getList(){
        return this.diffuseurs;
    }

}
