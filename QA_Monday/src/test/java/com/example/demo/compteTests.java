package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.sql.DriverManager;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.runners.Parameterized.*;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;



@RunWith(Parameterized.class)
public class compteTests {


    @Parameters(name = "{0} est {1}")
    public static Collection<Object[]> data(){

        return asList(new Object[][]{
                {"Mauvaise Adresse", false},
                {"Francois@gmail.com", true},
                {"David@gmail.com", true},
                {"Luc@gmail.com", true},
        });
    }

    @Parameter(0)
    public String adress;
    @Parameter(1)
    public boolean result;

    @Test
    public void should_not_found_account(){

        compte essai = new compte("Faux","XxXxX");
        assertThat(essai.liste_Adresse.containsAddress(essai.getName())).isFalse();
    }

    @Test
    public void should_found_account(){

        compte essai = new compte("Luc@gmail.com","XxXxX");
        assertThat(essai.liste_Adresse.containsAddress(essai.getName())).isTrue();
    }

    @Test
    public void should_found_and_not_found(){
        compte essai = new compte("Sans Importance","XxXxX");
        assertThat(essai.liste_Adresse.containsAddress(adress)).isEqualTo(result);
    }


}
