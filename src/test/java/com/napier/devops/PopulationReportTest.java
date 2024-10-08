package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PopulationReportTest {

    private PopulationReport populationReport;

    @BeforeEach
    public void setUp() {
        populationReport = new PopulationReport();
    }

    // Test for setting and getting the name
    @Test
    public void testSetAndGetName() {
        populationReport.setName("Asia");
        assertEquals("Asia", populationReport.getName(), "Name should be 'Asia'");
    }

    // Test for setting and getting the total population
    @Test
    public void testSetAndGetTotalPopulation() {
        populationReport.setTotalPopulation(4458000000L);
        assertEquals(4458000000L, populationReport.getTotalPopulation(), "Total population should be 4,458,000,000");
    }

    // Test for setting and getting the city population
    @Test
    public void testSetAndGetCityPopulation() {
        populationReport.setCityPopulation(2100000000L);
        assertEquals(2100000000L, populationReport.getCityPopulation(), "City population should be 2,100,000,000");
    }

    // Test for setting and getting the city population percentage
    @Test
    public void testSetAndGetCityPopulationPercentage() {
        populationReport.setCityPopulationPercentage(47.1);
        assertEquals(47.1, populationReport.getCityPopulationPercentage(), 0.001, "City population percentage should be 47.1");
    }

    // Test for setting and getting the non-city population
    @Test
    public void testSetAndGetNonCityPopulation() {
        populationReport.setNonCityPopulation(2358000000L);
        assertEquals(2358000000L, populationReport.getNonCityPopulation(), "Non-city population should be 2,358,000,000");
    }

    // Test for setting and getting the non-city population percentage
    @Test
    public void testSetAndGetNonCityPopulationPercentage() {
        populationReport.setNonCityPopulationPercentage(52.9);
        assertEquals(52.9, populationReport.getNonCityPopulationPercentage(), 0.001, "Non-city population percentage should be 52.9");
    }
}
