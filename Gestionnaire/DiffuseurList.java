package Gestionnaire;
import java.io.*;
import java.net.*;
import Messages.*;

import java.util.ArrayList;

public class DiffuseurList {
    ArrayList<DiffuseurInformation> diffuseurs;
    int max_size = 5;

    public DiffuseurList(){
        diffuseurs = new ArrayList<DiffuseurInformation>();
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

    public String getInformation(int index){
        return diffuseurs.get(index).getInformation();
    }

    public void addDiffuseur(DiffuseurInformation diffuseur){
        diffuseurs.add(diffuseur);
    }

    public void print_array() {
        for(int i = 0; i < getSize() ; i++){
            System.out.println(diffuseurs.get(i).getInformation());
        }
    }

    public ArrayList<DiffuseurInformation> getList(){
        return this.diffuseurs;
    }

}
