package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the City class.
 * This class verifies the functionality of setting and getting various attributes of the City class,
 * such as ID, name, country code, district, and population. It also tests the toString method.
 */
public class CityTest {

    // Instance of City class used for testing
    private City city;

    /**
     * Initializes a new City object before each test.
     * Ensures that each test has a fresh instance of City for isolated testing.
     */
    @BeforeEach
    public void setUp() {
        city = new City();
    }

    /**
     * Test for setting and getting the city's ID.
     * Verifies that the city's ID is accurately stored and retrieved.
     */
    @Test
    public void testSetAndGetId() {
        city.setId(123);
        assertEquals(123, city.getId(), "City ID should be 123");
    }

    /**
     * Test for setting and getting the city's name.
     * Confirms that the city's name is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetName() {
        city.setName("Napier");
        assertEquals("Napier", city.getName(), "City name should be 'Napier'");
    }

    /**
     * Test for setting and getting the city's country code.
     * Ensures that the country code is properly stored and can be accurately retrieved.
     */
    @Test
    public void testSetAndGetCountryCode() {
        city.setCountryCode("NZL");
        assertEquals("NZL", city.getCountryCode(), "Country code should be 'NZL'");
    }

    /**
     * Test for setting and getting the city's district.
     * Verifies that the district is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetDistrict() {
        city.setDistrict("Hawke's Bay");
        assertEquals("Hawke's Bay", city.getDistrict(), "District should be 'Hawke's Bay'");
    }

    /**
     * Test for setting and getting the city's population.
     * Confirms that the population is stored and retrieved accurately.
     */
    @Test
    public void testSetAndGetPopulation() {
        city.setPopulation(65000);
        assertEquals(65000, city.getPopulation(), "Population should be 65,000");
    }

    /**
     * Test for the toString method to ensure proper string representation.
     * Assembles an expected string output and compares it to the actual output from toString.
     */
    @Test
    public void testToString() {
        // Set values for City object
        city.setId(123);
        city.setName("Napier");
        city.setCountryCode("NZL");
        city.setDistrict("Hawke's Bay");
        city.setPopulation(65000);

        // Expected string representation
        String expected = "City{id=123, name='Napier', countryCode='NZL', district='Hawke's Bay', population=65000}";

        // Verify the toString output matches the expected format
        assertEquals(expected, city.toString(), "toString should return the correct representation of the City object");
    }
}
