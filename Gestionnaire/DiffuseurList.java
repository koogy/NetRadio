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
            System.out.println("It's false");
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
        if(diffuseurs.size() == 0){
            diffuseurs.add(diffuseur);
            return;
        }

        for(int i = 0 ; i < diffuseurs.size();i++){
            if(!((diffuseurs.get(i).getInformation()).substring(0,13).equals(diffuseur.getInformation().substring(0,13)))){
                diffuseurs.add(diffuseur);
            }
        }
       
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
