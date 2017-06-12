package com.example.demo;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import java.sql.*;

public class Authentication {
    /* Connexion à la base de données */

    private String user;
    private String password ;
    private Connection connexion = null;
    private String dbName;
    private String url;

    public Authentication(String user, String password, String dbName) {
        this.user = user;
        this.password = password;
        this.dbName = dbName;
        this.url = "jdbc:mysql://localhost:3306/"+dbName;
    }

    public boolean checklogs(String username, String passwd) throws ClassNotFoundException, SQLException {
            // On vérifie que le compte existe dans la BDD
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, user, password);

            String query = "SELECT idCompte FROM " + dbName + ".compte WHERE Login = '" + username + "' AND Password = '" + passwd + "'";
            System.out.println(query);
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
            } else {
                connexion.close();
                return true;
            }
        } catch (SQLException e){
            throw new SQLException();
        } catch (Exception e) {
            throw new SQLException();
        }

        return false;
    }
}