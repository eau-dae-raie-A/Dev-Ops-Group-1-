package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityTest {

    // Instance of the City class to be tested
    private City city;

    /**
     * Sets up a new City object before each test.
     * This ensures each test starts with a fresh instance.
     */
    @BeforeEach
    public void setUp() {
        city = new City();
    }

    /**
     * Tests setting and getting the ID of the city.
     * Sets a specific ID and verifies that the retrieved ID matches the expected value.
     */
    @Test
    public void testSetAndGetId() {
        city.setId(123); // Set ID to 123
        assertEquals(123, city.getId(), "City ID should be 123"); // Verify the ID
    }

    /**
     * Tests setting and getting the name of the city.
     * Sets a specific name and verifies that the retrieved name matches the expected value.
     */
    @Test
    public void testSetAndGetName() {
        city.setName("Napier"); // Set name to 'Napier'
        assertEquals("Napier", city.getName(), "City name should be 'Napier'"); // Verify the name
    }

    /**
     * Tests setting and getting the country code of the city.
     * Sets a specific country code and verifies that the retrieved code matches the expected value.
     */
    @Test
    public void testSetAndGetCountryCode() {
        city.setCountryCode("NZL"); // Set country code to 'NZL'
        assertEquals("NZL", city.getCountryCode(), "Country code should be 'NZL'"); // Verify the country code
    }

    /**
     * Tests setting and getting the district of the city.
     * Sets a specific district and verifies that the retrieved district matches the expected value.
     */
    @Test
    public void testSetAndGetDistrict() {
        city.setDistrict("Hawke's Bay"); // Set district to 'Hawke's Bay'
        assertEquals("Hawke's Bay", city.getDistrict(), "District should be 'Hawke's Bay'"); // Verify the district
    }

    /**
     * Tests setting and getting the population of the city.
     * Sets a specific population and verifies that the retrieved population matches the expected value.
     */
    @Test
    public void testSetAndGetPopulation() {
        city.setPopulation(65000); // Set population to 65,000
        assertEquals(65000, city.getPopulation(), "Population should be 65,000"); // Verify the population
    }

    /**
     * Tests the toString method to ensure it returns the correct string representation of the City object.
     * Sets all properties of the City object and verifies the output string matches the expected format.
     */
    @Test
    public void testToString() {
        // Set city properties
        city.setId(123);
        city.setName("Napier");
        city.setCountryCode("NZL");
        city.setDistrict("Hawke's Bay");
        city.setPopulation(65000);

        // Define expected string representation
        String expected = "City{id=123, name='Napier', countryCode='NZL', district='Hawke's Bay', population=65000}";

        // Verify that toString returns the correct representation
        assertEquals(expected, city.toString(), "toString should return the correct representation of the City object");
    }
}
