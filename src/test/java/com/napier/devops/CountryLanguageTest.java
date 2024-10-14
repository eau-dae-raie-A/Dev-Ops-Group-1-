package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CountryLanguage class.
 * This class tests the functionality of setting and getting attributes such as country code,
 * language, official status, and percentage. It also verifies the toString method.
 */
public class CountryLanguageTest {

    // Instance of CountryLanguage class for testing purposes
    private CountryLanguage countryLanguage;

    /**
     * Initializes a new CountryLanguage object before each test.
     * Ensures that each test has a fresh instance of CountryLanguage for isolated testing.
     */
    @BeforeEach
    public void setUp() {
        countryLanguage = new CountryLanguage();
    }

    /**
     * Test for setting and getting the country code.
     * Verifies that the country code is correctly stored and can be retrieved accurately.
     */
    @Test
    public void testSetAndGetCountryCode() {
        countryLanguage.setCountryCode("USA");
        assertEquals("USA", countryLanguage.getCountryCode(), "Country code should be 'USA'");
    }

    /**
     * Test for setting and getting the language.
     * Confirms that the language is correctly stored and can be retrieved accurately.
     */
    @Test
    public void testSetAndGetLanguage() {
        countryLanguage.setLanguage("English");
        assertEquals("English", countryLanguage.getLanguage(), "Language should be 'English'");
    }

    /**
     * Test for setting and getting the isOfficial attribute.
     * Ensures that the official status is accurately stored and can be retrieved.
     * 'T' signifies that the language is official.
     */
    @Test
    public void testSetAndGetIsOfficial() {
        countryLanguage.setIsOfficial("T");
        assertEquals("T", countryLanguage.getIsOfficial(), "is Official should be 'T'");
    }

    /**
     * Test for setting and getting the percentage attribute.
     * Verifies that the percentage is correctly stored and can be retrieved,
     * with a tolerance for floating-point comparisons.
     */
    @Test
    public void testSetAndGetPercentage() {
        countryLanguage.setPercentage(81.7);
        assertEquals(81.7, countryLanguage.getPercentage(), 0.001, "Percentage should be 81.7");
    }

    /**
     * Test for the toString method to ensure proper string representation.
     * Assembles an expected output and compares it to the actual output from toString.
     */
    @Test
    public void testToString() {
        countryLanguage.setCountryCode("USA");
        countryLanguage.setLanguage("English");
        countryLanguage.setIsOfficial("T");
        countryLanguage.setPercentage(81.7);

        String expected = "CountryLanguage{countryCode='USA', language='English', isOfficial='T', percentage=81.7}";
        assertEquals(expected, countryLanguage.toString(), "toString should return the correct representation of the CountryLanguage object");
    }
}
