package com.example.demo;

import java.sql.*;

/**
 * Created by chris on 02/06/2017.
 */
public class Insert {

    public boolean checkPassword(String pass1, String pass2) {

        if(pass1.equals(pass2)) {
            return true;
        }

        return false;
    }

    public boolean checkMail(String mail) {

        if(mail.indexOf('@') == -1) {
            return false;
        }

        return true;
    }

    public Connection BDDConnect(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void Connect(String Log, String Pass, String Pass2, String Mail) {
        try {
            // vérif que Driver présent
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver O.K.");

            // lien vers la bdd
            String url = "jdbc:mysql://localhost:3306/qa";
            String user = "root";
            String passwd = "";

            // vérif que les deux pass sont différents
            if(!checkPassword(Pass, Pass2))
                return;

            // vérif que l'adresse mail contient bien une @
            if (!checkMail(Mail))
                return;

            // Connection à la base
            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");

            // Préparation pour requête
            Statement statement = conn.createStatement();

            // Vérif que le Login ou l'email ne sont pas déjà présent dans la base
            ResultSet resultat = statement.executeQuery( "SELECT Login, Email  FROM compte;" );
            while ( resultat.next() ) {
                String LoginUtilisateur = resultat.getString( "Login" );
                String EmailUtilisateur = resultat.getString( "Email" );
                if( LoginUtilisateur.equals(Log)){
                    System.out.println("Login existe déjà !");
                    return;
                }
                if( EmailUtilisateur.equals(Mail)){
                    System.out.println("Email existe déjà !");
                    return;
                }
            }

            // Insertion dans la bdd
            int insertion = statement.executeUpdate( "INSERT INTO compte (Login, Password, Email) " +
                    "VALUES ( '"+Log+"', '"+Pass+"' , '"+Mail+"');" );
            System.out.println("Insertion réussi");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


