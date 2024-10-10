package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CountryLanguageTest {

    // Instance of the CountryLanguage class to be tested
    private CountryLanguage countryLanguage;

    /**
     * Sets up a new CountryLanguage object before each test.
     * Ensures that each test has a fresh instance to work with.
     */
    @BeforeEach
    public void setUp() {
        countryLanguage = new CountryLanguage();
    }

    /**
     * Tests setting and getting the country code.
     * Sets a specific country code and verifies that the retrieved value matches the expected code.
     */
    @Test
    public void testSetAndGetCountryCode() {
        countryLanguage.setCountryCode("USA"); // Set country code to 'USA'
        assertEquals("USA", countryLanguage.getCountryCode(), "Country code should be 'USA'"); // Verify the country code
    }

    /**
     * Tests setting and getting the language.
     * Sets a specific language and verifies that the retrieved value matches the expected language.
     */
    @Test
    public void testSetAndGetLanguage() {
        countryLanguage.setLanguage("English"); // Set language to 'English'
        assertEquals("English", countryLanguage.getLanguage(), "Language should be 'English'"); // Verify the language
    }

    /**
     * Tests setting and getting the isOfficial attribute.
     * Sets the attribute to indicate whether the language is official and verifies the expected value.
     */
    @Test
    public void testSetAndGetIsOfficial() {
        countryLanguage.setIsOfficial("T"); // Set isOfficial to 'T' (True)
        assertEquals("T", countryLanguage.getIsOfficial(), "isOfficial should be 'T'"); // Verify isOfficial attribute
    }

    /**
     * Tests setting and getting the percentage attribute.
     * Sets a specific percentage and verifies that the retrieved value matches the expected percentage within a tolerance.
     */
    @Test
    public void testSetAndGetPercentage() {
        countryLanguage.setPercentage(81.7); // Set percentage to 81.7
        assertEquals(81.7, countryLanguage.getPercentage(), 0.001, "Percentage should be 81.7"); // Verify the percentage
    }

    /**
     * Tests the toString method to ensure it returns the correct string representation of the CountryLanguage object.
     * Sets all properties and verifies that the output string matches the expected format.
     */
    @Test
    public void testToString() {
        // Set properties of the CountryLanguage object
        countryLanguage.setCountryCode("USA");
        countryLanguage.setLanguage("English");
        countryLanguage.setIsOfficial("T");
        countryLanguage.setPercentage(81.7);

        // Define the expected string representation
        String expected = "CountryLanguage{countryCode='USA', language='English', isOfficial='T', percentage=81.7}";

        // Verify that toString returns the correct representation
        assertEquals(expected, countryLanguage.toString(), "toString should return the correct representation of the CountryLanguage object");
    }
}
