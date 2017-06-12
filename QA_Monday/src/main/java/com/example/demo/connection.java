package com.example.demo;

import java.sql.*;

public class connection {
    /* Connexion à la base de données */

    String utilisateur = "root";
    String motDePasse = "";
    Connection connexion = null;
    String dbName = "qa";
    String url = "jdbc:mysql://localhost:3306/" + dbName;


    public boolean testconnection() {
        // On test la connexion avec la BDD
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

            connexion.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checklogs(String username, String password) throws SQLException {
            // On vérifie que le compte existe dans la BDD
        try{
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

            String query = "SELECT idCompte FROM " + dbName + ".compte WHERE Login = '" + username + "' AND Password = '" + password + "'";
            int id_compte = 0;
            Statement statement = connexion.createStatement();
            ResultSet res = statement.executeQuery(query);

            //Récupération du résultat de la requête SQL (on récupère l'id du compte)
            while(res.next()) {
                id_compte = res.getInt("idCompte");
            }

            // si id_compte a changé de valeur, c'est que le compte recherché existe dans la base de donnée
            if(id_compte == 0)  {
                connexion.close();
                return false;
            } else {
                connexion.close();
                return true;
            }

        }   catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    }