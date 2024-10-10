package com.napier.devops;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AppIntegrationIT {

    private DatabaseService databaseService;
    private App app;

    @BeforeEach
    public void setUp() {
        // Initialize DatabaseService and App instances
        databaseService = new DatabaseService();
        app = new App(databaseService);

        // Connect to MySQL or other suitable test database (replace parameters as necessary)
        databaseService.connect("localhost:33060", 30000);
    }

    @AfterEach
    public void tearDown() {
        // Disconnect from the test database
        databaseService.disconnect();
    }

    @Test
    public void testDatabaseConnection() {
        assertNotNull(databaseService.getConnection(), "The database connection should be established.");
    }

    @Test
    public void testRetrieveCountriesByPopulation() {
        List<Country> countries = databaseService.getCountriesByPopulation();
        assertNotNull(countries, "The list of countries should not be null.");
        assertTrue(countries.size() > 0, "The list of countries should contain at least one entry.");
    }

    @Test
    public void testRetrieveCitiesByContinent() {
        List<City> cities = databaseService.getCitiesByContinent("Asia");
        assertNotNull(cities, "The list of cities should not be null.");
        assertTrue(cities.size() > 0, "The list of cities should contain at least one entry.");
    }

    @Test
    public void testTopPopulatedCountries() {
        List<Country> topCountries = databaseService.getTopPopulatedCountries(5);
        assertNotNull(topCountries, "The list of top populated countries should not be null.");
        assertEquals(5, topCountries.size(), "The list should contain exactly 5 countries.");
    }

    @Test
    public void testPopulationDataByRegion() {
        List<PopulationReport> populationReports = databaseService.getPopulationByRegion();
        assertNotNull(populationReports, "The population report list should not be null.");
        assertTrue(populationReports.size() > 0, "The population report list should contain entries.");
    }

    // New tests below
    @Test
    public void testRetrieveCountriesByContinent() {
        List<Country> asianCountries = databaseService.getCountriesByContinent("Asia");
        assertNotNull(asianCountries, "The list of countries should not be null.");
        assertTrue(asianCountries.size() > 0, "The list of countries in Asia should contain at least one entry.");
    }

    @Test
    public void testRetrieveCountriesByRegion() {
        List<Country> middleEastCountries = databaseService.getCountriesByRegion("Middle East");
        assertNotNull(middleEastCountries, "The list of countries should not be null.");
        assertTrue(middleEastCountries.size() > 0, "The list of countries in the Middle East should contain at least one entry.");
    }

    @Test
    public void testRetrieveTopPopulatedCitiesByContinent() {
        List<City> topAsianCities = databaseService.getTopPopulatedCitiesByContinent("Asia", 5);
        assertNotNull(topAsianCities, "The list of top populated cities should not be null.");
        assertEquals(5, topAsianCities.size(), "The list should contain exactly 5 cities.");
    }

    @Test
    public void testRetrieveTopPopulatedCitiesByCountry() {
        List<City> topUSACities = databaseService.getTopPopulatedCitiesByCountry("USA", 5);
        assertNotNull(topUSACities, "The list of top populated cities should not be null.");
        assertEquals(5, topUSACities.size(), "The list should contain exactly 5 cities.");
    }

    @Test
    public void testRetrieveTopPopulatedCapitalCities() {
        List<City> topCapitalCities = databaseService.getTopPopulatedCapitalCities(3);
        assertNotNull(topCapitalCities, "The list of top populated capital cities should not be null.");
        assertEquals(3, topCapitalCities.size(), "The list should contain exactly 3 capital cities.");
    }

    @Test
    public void testPopulationDataByCountry() {
        List<PopulationReport> populationReports = databaseService.getPopulationByCountry();
        assertNotNull(populationReports, "The population report list should not be null.");
        assertTrue(populationReports.size() > 0, "The population report list should contain entries.");
    }

    @Test
    public void testRetrieveCitiesByCountry() {
        List<City> citiesInCountry = databaseService.getCitiesByCountry("FRA");
        assertNotNull(citiesInCountry, "The list of cities should not be null.");
        assertTrue(citiesInCountry.size() > 0, "The list of cities in the country should contain at least one entry.");
    }

    @Test
    public void testRetrieveCapitalCitiesByContinent() {
        List<City> capitalCitiesInContinent = databaseService.getCapitalCitiesByContinent("Europe");
        assertNotNull(capitalCitiesInContinent, "The list of capital cities should not be null.");
        assertTrue(capitalCitiesInContinent.size() > 0, "The list of capital cities in the continent should contain at least one entry.");
    }

    @Test
    public void testPopulationDataByContinent() {
        List<PopulationReport> continentPopulationData = databaseService.getPopulationByContinent();
        assertNotNull(continentPopulationData, "The continent population data list should not be null.");
        assertTrue(continentPopulationData.size() > 0, "The continent population data list should contain entries.");
    }
}
