package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityTest {

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

    // Test for setting and getting the city's name
    @Test
    public void testSetAndGetName() {
        city.setName("Napier");
        assertEquals("Napier", city.getName(), "City name should be 'Napier'");
    }

    // Test for setting and getting the city's country code
    @Test
    public void testSetAndGetCountryCode() {
        city.setCountryCode("NZL");
        assertEquals("NZL", city.getCountryCode(), "Country code should be 'NZL'");
    }

    // Test for setting and getting the city's district
    @Test
    public void testSetAndGetDistrict() {
        city.setDistrict("Hawke's Bay");
        assertEquals("Hawke's Bay", city.getDistrict(), "District should be 'Hawke's Bay'");
    }

    // Test for setting and getting the city's population
    @Test
    public void testSetAndGetPopulation() {
        city.setPopulation(65000);
        assertEquals(65000, city.getPopulation(), "Population should be 65,000");
    }

    // Test the toString method for proper string representation
    @Test
    public void testToString() {
        city.setId(123);
        city.setName("Napier");
        city.setCountryCode("NZL");
        city.setDistrict("Hawke's Bay");
        city.setPopulation(65000);

        String expected = "City{id=123, name='Napier', countryCode='NZL', district='Hawke's Bay', population=65000}";
        assertEquals(expected, city.toString(), "toString should return the correct representation of the City object");
    }
}
