package com.napier.devops;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Integration test class for the App and DatabaseService classes.
 * This class tests the interaction between the application and the database.
 */
public class AppIntegrationIT {

    // Instance variables for DatabaseService and App classes
    private DatabaseService databaseService;
    private App app;

    /**
     * Sets up the test environment before each test.
     * Initializes DatabaseService and App instances and connects to the database.
     */
    @BeforeEach
    public void setUp() {
        // Initialize DatabaseService and App instances
        databaseService = new DatabaseService();
        app = new App(databaseService);

        // Connect to the database with specified host and delay settings
        databaseService.connect("localhost:33060", 30000);
    }

    /**
     * Cleans up the test environment after each test.
     * Disconnects from the database to ensure clean state for each test.
     */
    @AfterEach
    public void tearDown() {
        // Disconnect from the database
        databaseService.disconnect();
    }

    /**
     * Tests the database connection to ensure it is established.
     */
    @Test
    public void testDatabaseConnection() {
        assertNotNull(databaseService.getConnection(), "The database connection should be established.");
    }

    /**
     * Tests retrieval of countries by population.
     * Ensures that the list of countries is not null and contains entries.
     */
    @Test
    public void testRetrieveCountriesByPopulation() {
        List<Country> countries = databaseService.getCountriesByPopulation();
        assertNotNull(countries, "The list of countries should not be null.");
        assertTrue(countries.size() > 0, "The list of countries should contain at least one entry.");
    }

    /**
     * Tests retrieval of cities by continent.
     * Ensures that the list of cities is not null and contains entries.
     */
    @Test
    public void testRetrieveCitiesByContinent() {
        List<City> cities = databaseService.getCitiesByContinent("Asia");
        assertNotNull(cities, "The list of cities should not be null.");
        assertTrue(cities.size() > 0, "The list of cities should contain at least one entry.");
    }

    /**
     * Tests retrieval of top populated countries globally.
     * Ensures the list has exactly the requested number of entries.
     */
    @Test
    public void testTopPopulatedCountries() {
        List<Country> topCountries = databaseService.getTopPopulatedCountries(5);
        assertNotNull(topCountries, "The list of top populated countries should not be null.");
        assertEquals(5, topCountries.size(), "The list should contain exactly 5 countries.");
    }

    /**
     * Tests population data retrieval by region.
     * Ensures that the list of population reports is not null and contains entries.
     */
    @Test
    public void testPopulationDataByRegion() {
        List<PopulationReport> populationReports = databaseService.getPopulationByRegion();
        assertNotNull(populationReports, "The population report list should not be null.");
        assertTrue(populationReports.size() > 0, "The population report list should contain entries.");
    }


    /**
     * Tests retrieval of countries by a specified continent.
     */
    @Test
    public void testRetrieveCountriesByContinent() {
        List<Country> asianCountries = databaseService.getCountriesByContinent("Asia");
        assertNotNull(asianCountries, "The list of countries should not be null.");
        assertTrue(asianCountries.size() > 0, "The list of countries in Asia should contain at least one entry.");
    }

    /**
     * Tests retrieval of countries by a specified region.
     */
    @Test
    public void testRetrieveCountriesByRegion() {
        List<Country> middleEastCountries = databaseService.getCountriesByRegion("Middle East");
        assertNotNull(middleEastCountries, "The list of countries should not be null.");
        assertTrue(middleEastCountries.size() > 0, "The list of countries in the Middle East should contain at least one entry.");
    }

    /**
     * Tests retrieval of top populated cities in a specified continent.
     */
    @Test
    public void testRetrieveTopPopulatedCitiesByContinent() {
        List<City> topAsianCities = databaseService.getTopPopulatedCitiesByContinent("Asia", 5);
        assertNotNull(topAsianCities, "The list of top populated cities should not be null.");
        assertEquals(5, topAsianCities.size(), "The list should contain exactly 5 cities.");
    }

    /**
     * Tests retrieval of top populated cities in a specified country.
     */
    @Test
    public void testRetrieveTopPopulatedCitiesByCountry() {
        List<City> topUSACities = databaseService.getTopPopulatedCitiesByCountry("USA", 5);
        assertNotNull(topUSACities, "The list of top populated cities should not be null.");
        assertEquals(5, topUSACities.size(), "The list should contain exactly 5 cities.");
    }

    /**
     * Tests retrieval of top populated capital cities globally.
     */
    @Test
    public void testRetrieveTopPopulatedCapitalCities() {
        List<City> topCapitalCities = databaseService.getTopPopulatedCapitalCities(3);
        assertNotNull(topCapitalCities, "The list of top populated capital cities should not be null.");
        assertEquals(3, topCapitalCities.size(), "The list should contain exactly 3 capital cities.");
    }

    /**
     * Tests population data retrieval by country.
     */
    @Test
    public void testPopulationDataByCountry() {
        List<PopulationReport> populationReports = databaseService.getPopulationByCountry();
        assertNotNull(populationReports, "The population report list should not be null.");
        assertTrue(populationReports.size() > 0, "The population report list should contain entries.");
    }

    /**
     * Tests retrieval of cities in a specified country.
     */
    @Test
    public void testRetrieveCitiesByCountry() {
        List<City> citiesInCountry = databaseService.getCitiesByCountry("FRA");
        assertNotNull(citiesInCountry, "The list of cities should not be null.");
        assertTrue(citiesInCountry.size() > 0, "The list of cities in the country should contain at least one entry.");
    }

    /**
     * Tests retrieval of capital cities by a specified continent.
     */
    @Test
    public void testRetrieveCapitalCitiesByContinent() {
        List<City> capitalCitiesInContinent = databaseService.getCapitalCitiesByContinent("Europe");
        assertNotNull(capitalCitiesInContinent, "The list of capital cities should not be null.");
        assertTrue(capitalCitiesInContinent.size() > 0, "The list of capital cities in the continent should contain at least one entry.");
    }

    /**
     * Tests population data retrieval by continent.
     */
    @Test
    public void testPopulationDataByContinent() {
        List<PopulationReport> continentPopulationData = databaseService.getPopulationByContinent();
        assertNotNull(continentPopulationData, "The continent population data list should not be null.");
        assertTrue(continentPopulationData.size() > 0, "The continent population data list should contain entries.");
    }
}
