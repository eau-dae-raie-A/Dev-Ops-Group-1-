package com.napier.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LanguageReport class.
 */
class LanguageReportTest {

    /**
     * Tests the setLanguage and getLanguage methods to ensure that
     * the language is set and retrieved correctly, including a null case.
     */
    @Test
    void testSetAndGetLanguage() {
        // Create a LanguageReport instance and set the language to "English"
        LanguageReport report = new LanguageReport();
        report.setLanguage("English");

        // Assert that the language is correctly retrieved
        assertEquals("English", report.getLanguage(), "The language should be English.");

        // Set the language to null and verify that the language is null
        report.setLanguage(null);
        assertNull(report.getLanguage(), "The language should be null.");
    }

    /**
     * Tests the setSpeakerCount and getSpeakerCount methods to ensure that
     * the speaker count is set and retrieved correctly.
     */
    @Test
    void testSetAndGetSpeakerCount() {
        // Create a LanguageReport instance and set the speaker count to 500,000,000
        LanguageReport report = new LanguageReport();
        report.setSpeakerCount(500000000);

        // Assert that the speaker count is correctly retrieved
        assertEquals(500000000, report.getSpeakerCount(), "The speaker count should be 500,000,000.");
    }

    /**
     * Tests the setWorldPercentage and getWorldPercentage methods to ensure that
     * the world percentage is set and retrieved correctly.
     */
    @Test
    void testSetAndGetWorldPercentage() {
        // Create a LanguageReport instance and set the world percentage to 6.5
        LanguageReport report = new LanguageReport();
        report.setWorldPercentage(6.5);

        // Assert that the world percentage is correctly retrieved
        assertEquals(6.5, report.getWorldPercentage(), "The world percentage should be 6.5.");
    }

    /**
     * Tests the getWorldPercentageString method to ensure the world percentage
     * is formatted correctly to two decimal places followed by a percent sign.
     */
    @Test
    void testGetWorldPercentageString() {
        // Create a LanguageReport instance and set the world percentage to 12.3456
        LanguageReport report = new LanguageReport();
        report.setWorldPercentage(12.3456);

        // Assert that the formatted world percentage string is correct
        assertEquals("12.35%", report.getWorldPercentageString(), "The formatted world percentage should be 12.35%.");
    }

    /**
     * Tests the rounding behavior of the getWorldPercentageString method to ensure
     * it rounds correctly when the world percentage is close to a whole number.
     */
    @Test
    void testWorldPercentageStringFormatting() {
        // Create a LanguageReport instance and set the world percentage to 99.999
        LanguageReport report = new LanguageReport();
        report.setWorldPercentage(99.999);

        // Assert that the formatted world percentage string rounds up to 100.00%
        assertEquals("100.00%", report.getWorldPercentageString(), "The world percentage string should be rounded to 100.00%.");
    }

    /**
     * Tests the getWorldPercentageString method when the world percentage is set to zero,
     * ensuring the formatting correctly displays 0.00%.
     */
    @Test
    void testWorldPercentageZeroValue() {
        // Create a LanguageReport instance and set the world percentage to 0.0
        LanguageReport report = new LanguageReport();
        report.setWorldPercentage(0.0);

        // Assert that the formatted world percentage string is "0.00%"
        assertEquals("0.00%", report.getWorldPercentageString(), "The world percentage should be formatted as 0.00%.");
    }
}
