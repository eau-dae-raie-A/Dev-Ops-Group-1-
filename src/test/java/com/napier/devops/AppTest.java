package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static DatabaseService databaseService;

    @BeforeAll
    static void init()
    {
        databaseService = new DatabaseService();
    }

    @Test
    void displayCountriesTestNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        databaseService.displayCountries(null);
    }
}