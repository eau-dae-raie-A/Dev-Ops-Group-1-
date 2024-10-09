package com.napier.devops;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static DatabaseService dbService;

    // Instance of City class
    private City city;

    // Initialize a new City object before each test
    @BeforeEach
    public void setUp() {
        city = new City();
    }

    // Test for setting and getting the city's ID
    @Test
    public void testSetAndGetId() {
        city.setId(123);
        assertEquals(123, city.getId(), "City ID should be 123");
    }

}