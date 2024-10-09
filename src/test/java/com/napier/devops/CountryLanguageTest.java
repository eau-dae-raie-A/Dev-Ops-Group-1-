package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CountryLanguageTest {

    // Instance of CountryLanguage class
    private CountryLanguage countryLanguage;

    // Initialize a new CountryLanguage object before each test
    @BeforeEach
    public void setUp() {
        countryLanguage = new CountryLanguage();
    }

    // Test for setting and getting the country code
    @Test
    public void testSetAndGetCountryCode() {
        countryLanguage.setCountryCode("USA");
        assertEquals("USA", countryLanguage.getCountryCode(), "Country code should be 'USA'");
    }

    // Test for setting and getting the language
    @Test
    public void testSetAndGetLanguage() {
        countryLanguage.setLanguage("English");
        assertEquals("English", countryLanguage.getLanguage(), "Language should be 'English'");
    }

    // Test for setting and getting the isOfficial attribute
    @Test
    public void testSetAndGetIsOfficial() {
        countryLanguage.setIsOfficial("T");
        assertEquals("T", countryLanguage.getIsOfficial(), "isOfficial should be 'T'");
    }

    // Test for setting and getting the percentage attribute
    @Test
    public void testSetAndGetPercentage() {
        countryLanguage.setPercentage(81.7);
        assertEquals(81.7, countryLanguage.getPercentage(), 0.001, "Percentage should be 81.7");
    }

    // Test the toString method for proper string representation
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
