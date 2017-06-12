package com.example.demo;

import org.junit.Test;

import java.sql.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BDDTests {

    String utilisateur = "root";
    String motDePasse = "";
    Connection connexion = null;
    String dbName = "qa";
    String url = "jdbc:mysql://localhost:3306/" + dbName;

    BDD bdd = new BDD();

    @Test
    public void should_be_connected() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            connexion.close();

        } catch (Exception e) {
            fail("Connexion fail");
            e.printStackTrace();
        }

    }

    @Test
    public void account_should_exist() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

        String log = "Working";
        String fin = "";

        String query = "SELECT Login FROM " + dbName + ".compte WHERE Login = '" + log + "'";

        Statement statement = connexion.createStatement();
        ResultSet result = statement.executeQuery(query);

        while(result.next()) {
            fin = result.getString("Login");
        }

        if (fin.length() <= 0)
            fail("Le compte n'existe pas");
        connexion.close();
    }

    @Test
    public void account_not_should_exist() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

        String log = "NotWorking";
        String fin = "";

        String query = "SELECT Login FROM " + dbName + ".compte WHERE Login = '" + log + "'";

        Statement statement = connexion.createStatement();
        ResultSet result = statement.executeQuery(query);

        while(result.next()) {
            fin = result.getString("Login");
        }

        if (fin.length() > 0)
            fail("Le compte existe");
        connexion.close();
    }

    @Test
    public void password_should_not_match_with_account() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

        String log = "Working";
        String pass = "azerty";
        String fin = "";
        String fin2 = "";

        String query = "SELECT Login, Password FROM " + dbName + ".compte WHERE Login = '" + log + "'";

        Statement statement = connexion.createStatement();
        ResultSet result = statement.executeQuery(query);

        Statement statement2 = connexion.createStatement();
        ResultSet result2 = statement2.executeQuery(query);

        while(result.next() && result2.next()) {
            fin = result.getString("Login");
            fin2 = result2.getString("Password");
        }

        if (log.equals(fin) == true && pass.equals(fin2) == true)
            fail("Le compte existe et le mot de passe associé est correct");

        connexion.close();
    }

    @Test
    public void password_should_match_with_account() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

        String log = "Working";
        String pass = "xxxxxx";
        String fin = "";
        String fin2 = "";

        String query = "SELECT Login, Password FROM " + dbName + ".compte WHERE Login = '" + log + "'";

        Statement statement = connexion.createStatement();
        ResultSet result = statement.executeQuery(query);

        Statement statement2 = connexion.createStatement();
        ResultSet result2 = statement2.executeQuery(query);

        while(result.next() && result2.next()) {
            fin = result.getString("Login");
            fin2 = result2.getString("Password");
        }

        if (!(log.equals(fin) == true && pass.equals(fin2) == true))
        fail("Information non valide");

        connexion.close();
    }

    @Test
    public void add_account_success(){}

    @Test
    public void add_account_failure(){}

    @Test
    public void should_succesfully_change_password(){}

    @Test
    public void should_fail_to_change_password(){}

}
























/*
    @Test
    public void should_throw_operation_exception_when_divide_by_zero(){
        int a = 4;
        int b = 0;

        try {
            calculator.divide(a, b);
            fail("Should have thrown an exception");
        } catch (OperationException ex) {
            assertThat(ex.getMessage()).isNotNull();
            ex.printStackTrace();
        }
    }

*/