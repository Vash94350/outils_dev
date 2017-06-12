package com.example.demo;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Louis on 11/06/2017.
 */
public class InsertTest {

    Insert ins = new Insert();
    Connection connection = ins.BDDConnect("jdbc:mysql://localhost:3306/qa", "root", "");
    Statement statement = connection.createStatement();

    public InsertTest() throws SQLException {
    }

    @Test
    public void should_be_same_password() {

        assertThat(ins.checkPassword("test", "test")).isTrue();
    }

    @Test
    public void should_not_be_same_password() {

        assertThat(ins.checkPassword("test", "test2")).isFalse();
    }

    @Test
    public void should_be_a_valid_mail() {

        assertThat(ins.checkMail("test@test")).isTrue();
    }

    @Test
    public void should_not_be_a_valid_mail() {

        assertThat(ins.checkMail("testtest")).isFalse();
    }

    @Test
    public void should_throw_SQLException() {

        try {
            ins.BDDConnect("jdbc:mysql://localhost:3306/qa", "oot", "");
            fail("Should have thrown an exception");
        } catch (SQLException e) {

        }
    }

    @Test
    public void should_insert_compte() throws SQLException {

        String log = "coucou";
        String pass = "motdepasse";
        String mail = "hello@hello";

        assertThat(statement.executeUpdate( "INSERT INTO compte (Login, Password, Email) " +
                "VALUES ( '" + log + "', '" + pass + "' , '" + mail + "');" )).isPositive();

    }

    /*@Test
    public void should_not_insert_compte() throws SQLException {

        String log = "coucou";
        String pass = "motdepasse";
        String mail = "hello@hello";

        try {
            assertThat(statement.executeUpdate( "INSERT INTO compte (Login, Password, Email) " +
                    "VALUES ( '" + log + "', '" + pass + "' , '" + mail + "');" )).isPositive();
            fail("Should have thrown an exception");
        } catch (SQLException e) {

        }
    }
    */
}