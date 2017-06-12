package com.example.demo;

import java.util.ArrayList;

/**
 * Created by Michaël on 30/05/2017.
 */

public class BDD {

    private ArrayList<String> liste_compte_existant = new ArrayList<>();

    BDD(){
        this.liste_compte_existant=new ArrayList<>();
        liste_compte_existant.add("Francois@gmail.com");
        liste_compte_existant.add("David@gmail.com");
        liste_compte_existant.add("Luc@gmail.com");
    }

    public ArrayList<String> getListe_compte_existant() {
        return liste_compte_existant;
    }

    public boolean containsAddress(String name){ if(liste_compte_existant.contains(name)){return true;} return false; }
}
