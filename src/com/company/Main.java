package com.company; // J'ai laissé le package de base. si tu veux modifier le rangement pour les tests fait ce qui te plais

public class Main {

    public static void main(String[] args) {
	    compte un_compte=new compte("Francois@gmail.com","1234");
        compte un_second_compte=new compte("existepas","1234");
        //System.out.println(un_compte.getName()+" "+un_compte.getPassword());
        //System.out.print(un_compte.liste_Adresse.getListe_compte_existant());
        un_compte.Reset();
        un_second_compte.Reset();
        //System.out.println(un_compte.getPassword());
        //System.out.println(un_second_compte.getPassword());

    }
}
