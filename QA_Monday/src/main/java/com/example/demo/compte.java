package com.example.demo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Created by Michaël on 30/05/2017.
 */
public class compte {
    private String name;
    private String password;
    BDD liste_Adresse = new BDD();


    compte(String name,String password){
        this.name=name;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    void Reset(){// faire un moyen de recupération de mdp avec les adresse

        Scanner sc = new Scanner(System.in);
        if(liste_Adresse.containsAddress(this.name)){
            System.out.println("Saisissez le mot de passe associé à votre compte : ");
            String entree=sc.nextLine();
                if(entree.equals(this.password)) {
                    System.out.println("Saisissez le nouveau mot de passe que vous souhaitez associer à votre compte : ");
                    entree = sc.nextLine();  // ajouter une confirmation de l'entree (entrer le mdp deux fois)
                    System.out.println("Saisissez à nouveau, le nouveau mot de passe que vous souhaitez associer à votre compte : ");
                    if(entree.equals(sc.nextLine())){
                        this.password=entree;
                        System.out.println("Merci, votre mot de passe à été changé");
                    }else {//permettre de reboucler sur l'étape précedente. plutot que de reverifier l'ancien mdp.
                        System.out.print("Les deux champs ne sont pas identiques. Le changement de mot de apsse est annulé");
                    }
                }else{
                    System.out.println("Le mot de passe que vous avez saisie n'est pas correcte");
                }
        }else{
            System.out.println("Votre compte n'existe pas");// Situation impossible car il faut que le compte existe pour se connecter et avoir accès à l'option reset_password
        }
    }
}
