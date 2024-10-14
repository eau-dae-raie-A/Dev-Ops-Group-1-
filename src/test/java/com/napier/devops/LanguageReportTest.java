package com.napier.devops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LanguageReportTest {

    @Test
    void testSetAndGetLanguage() {
        LanguageReport report = new LanguageReport();
        report.setLanguage("English");
        assertEquals("English", report.getLanguage(), "The language should be English.");

        report.setLanguage(null);
        assertNull(report.getLanguage(), "The language should be null.");
    }

    @Test
    void testSetAndGetSpeakerCount() {
        LanguageReport report = new LanguageReport();
        report.setSpeakerCount(500000000);
        assertEquals(500000000, report.getSpeakerCount(), "The speaker count should be 500,000,000.");
    }

    @Test
    void testSetAndGetWorldPercentage() {
        LanguageReport report = new LanguageReport();
        report.setWorldPercentage(6.5);
        assertEquals(6.5, report.getWorldPercentage(), "The world percentage should be 6.5.");
    }

    @Test
    void testGetWorldPercentageString() {
        LanguageReport report = new LanguageReport();
        report.setWorldPercentage(12.3456);
        assertEquals("12.35%", report.getWorldPercentageString(), "The formatted world percentage should be 12.35%.");
    }

    @Test
    void testWorldPercentageStringFormatting() {
        LanguageReport report = new LanguageReport();
        report.setWorldPercentage(99.999);
        assertEquals("100.00%", report.getWorldPercentageString(), "The world percentage string should be rounded to 100.00%.");
    }

    @Test
    void testWorldPercentageZeroValue() {
        LanguageReport report = new LanguageReport();
        report.setWorldPercentage(0.0);
        assertEquals("0.00%", report.getWorldPercentageString(), "The world percentage should be formatted as 0.00%.");
    }
}
