package com.example.demo; /**
 * Created by torgu on 06/06/2017.
 */

import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionTest {

    @Test
    public void should_connect_to_account() throws SQLException, ClassNotFoundException {
        Authentication authentication = new Authentication("root", "", "qa");

        String username = "toto";
        String password = "toto";

        assertThat(authentication.checklogs(username, password)).isTrue();
    }

    @Test
    public void should_not_connect_to_account() throws SQLException, ClassNotFoundException {
        Authentication authentication = new Authentication("root", "", "qa");

        String username = "";
        String password = "";

        assertThat(authentication.checklogs(username, password)).isFalse();
    }

    @Test(expected = SQLException.class)
    public void should_throw_SQLException_when_database_name_is_not_correct() throws SQLException, ClassNotFoundException {
        Authentication authentication = new Authentication("root", "", "q");

        String username = "toto";
        String password = "toto";

        authentication.checklogs(username, password);
    }

    @Test(expected = SQLException.class)
    public void should_throw_SQLException_when_identifiers_are_not_corrects() throws SQLException, ClassNotFoundException {
        Authentication authentication = new Authentication("root", "", "qa");

        String username = "c'est dommage";
        String password = "ça marche plus";

        authentication.checklogs(username, password);
    }

}
