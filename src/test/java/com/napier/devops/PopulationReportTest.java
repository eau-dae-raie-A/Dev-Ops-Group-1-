package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PopulationReport class.
 * Tests the functionality of setting and getting values for various attributes
 * such as name, total population, city population, and non-city population.
 */
public class PopulationReportTest {

    // Instance of PopulationReport used for testing
    private PopulationReport populationReport;

    /**
     * Initializes a new PopulationReport object before each test.
     * This ensures that each test has a fresh instance of PopulationReport to work with.
     */
    @BeforeEach
    public void setUp() {
        populationReport = new PopulationReport();
    }

    /**
     * Tests the setName and getName methods.
     * Sets the name of the geographical area and checks if the name is correctly retrieved.
     */
    @Test
    public void testSetAndGetName() {
        populationReport.setName("Asia");
        assertEquals("Asia", populationReport.getName(), "Name should be 'Asia'");
    }

    /**
     * Tests the setTotalPopulation and getTotalPopulation methods.
     * Sets the total population and verifies if it matches the expected value.
     */
    @Test
    public void testSetAndGetTotalPopulation() {
        populationReport.setTotalPopulation(4458000000L);
        assertEquals(4458000000L, populationReport.getTotalPopulation(), "Total population should be 4,458,000,000");
    }

    /**
     * Tests the setCityPopulation and getCityPopulation methods.
     * Sets the city population and checks if the returned value is as expected.
     */
    @Test
    public void testSetAndGetCityPopulation() {
        populationReport.setCityPopulation(2100000000L);
        assertEquals(2100000000L, populationReport.getCityPopulation(), "City population should be 2,100,000,000");
    }

    /**
     * Tests the setNonCityPopulation and getNonCityPopulation methods.
     * Sets the non-city population and verifies if it retrieves the expected value.
     */
    @Test
    public void testSetAndGetNonCityPopulation() {
        populationReport.setNonCityPopulation(2358000000L);
        assertEquals(2358000000L, populationReport.getNonCityPopulation(), "Non-city population should be 2,358,000,000");
    }
}
