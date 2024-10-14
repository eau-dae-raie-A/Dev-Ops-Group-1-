package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the DatabaseService class.
 * This class uses Mockito to mock database operations, allowing testing of SQL-related methods
 * without needing a real database connection. It verifies data retrieval and exception handling.
 */
public class DatabaseServiceTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private DatabaseService databaseService;

    /**
     * Sets up the test environment by initializing mocks and common stubs.
     * Prepares the mocked database connection and statement for subsequent tests.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    /**
     * Tests the disconnect method when no connection is available.
     * Ensures that the method completes without errors and the connection remains null.
     */
    @Test
    public void testDisconnectWithoutConnection() {
        databaseService.disconnect();
        assertNull(databaseService.getConnection());
    }

    /**
     * Tests getCountriesByPopulation() for SQLException handling.
     * Mocks an SQL exception during execution and verifies that an empty list is returned.
     */
    @Test
    public void testExecuteCountryQueryException() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("SQL Error"));
        List<Country> countries = databaseService.getCountriesByPopulation();
        assertTrue(countries.isEmpty(), "Expected empty list on SQLException.");
    }

    /**
     * Tests getAllCitiesByPopulation() for SQLException handling.
     * Mocks an SQL exception and verifies that the method returns an empty list.
     */
    @Test
    public void testExecuteCityQueryException() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("SQL Error"));
        List<City> cities = databaseService.getAllCitiesByPopulation();
        assertTrue(cities.isEmpty(), "Expected empty list on SQLException.");
    }

    /**
     * Tests getPopulationByContinent() for SQLException handling.
     * Simulates an SQL exception and ensures the method returns an empty list.
     */
    @Test
    public void testExecutePopulationReportQueryException() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("SQL Error"));
        List<PopulationReport> reports = databaseService.getPopulationByContinent();
        assertTrue(reports.isEmpty(), "Expected empty list on SQLException.");
    }

    /**
     * Tests getCountriesByPopulation() under normal conditions.
     * Mocks a result set containing one country and verifies the method retrieves it correctly.
     */
    @Test
    public void testGetCountriesByPopulation() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getCountriesByPopulation();
        assertEquals(1, countries.size());
    }

    /**
     * Tests getCountriesByContinent() for a specific continent.
     * Mocks a result set with one country in Asia and verifies retrieval.
     */
    @Test
    public void testGetCountriesByContinent() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getCountriesByContinent("Asia");
        assertEquals(1, countries.size());
    }

    /**
     * Tests getCountriesByRegion() for a specific region.
     * Mocks a result set containing one country in Western Europe and verifies retrieval.
     */
    @Test
    public void testGetCountriesByRegion() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getCountriesByRegion("Western Europe");
        assertEquals(1, countries.size());
    }

    /**
     * Tests getTopPopulatedCountries() for a limited number of results.
     * Mocks a result set containing one country and verifies that only one is retrieved.
     */
    @Test
    public void testGetTopPopulatedCountries() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getTopPopulatedCountries(5);
        assertEquals(1, countries.size());
    }

    /**
     * Tests getTopPopulatedCountriesByContinent() for a specific continent with a limit.
     * Mocks a result set containing one country in Asia and verifies retrieval.
     */
    @Test
    public void testGetTopPopulatedCountriesByContinent() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getTopPopulatedCountriesByContinent("Asia", 5);
        assertEquals(1, countries.size());
    }

    /**
     * Tests getTopPopulatedCountriesByRegion() for a specific region with a limit.
     * Mocks a result set containing one country in Europe and verifies retrieval.
     */
    @Test
    public void testGetTopPopulatedCountriesByRegion() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getTopPopulatedCountriesByRegion("Europe", 5);
        assertEquals(1, countries.size());
    }

    /**
     * Tests getAllCitiesByPopulation() for normal retrieval.
     * Mocks a result set containing one city and verifies the method retrieves it.
     */
    @Test
    public void testGetAllCitiesByPopulation() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getAllCitiesByPopulation();
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCitiesByContinent() for cities within a specific continent.
     * Mocks a result set containing one city in Asia and verifies retrieval.
     */
    @Test
    public void testGetCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByContinent("Asia");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCitiesByRegion() for cities within a specific region.
     * Mocks a result set containing one city in Southern Asia and verifies retrieval.
     */
    @Test
    public void testGetCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByRegion("Southern Asia");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCitiesByCountry() for cities within a specific country.
     * Mocks a result set containing one city and verifies retrieval by country code.
     */
    @Test
    public void testGetCitiesByCountry() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByCountry("FRA");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCitiesByDistrict() for cities within a specific district.
     * Mocks a result set containing one city in a specific district and verifies retrieval.
     */
    @Test
    public void testGetCitiesByDistrict() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByDistrict("New York");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCities() with a limit on the number of results.
     * Mocks a result set containing one city and verifies that only one is retrieved.
     */
    @Test
    public void testGetTopPopulatedCities() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCities(5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCitiesByContinent() for a specific continent with a limit.
     * Mocks a result set containing one city in Europe and verifies retrieval.
     */
    @Test
    public void testGetTopPopulatedCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByContinent("Europe", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCitiesByRegion() for a specific region with a limit.
     * Mocks a result set containing one city in Western Europe and verifies retrieval.
     */
    @Test
    public void testGetTopPopulatedCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByRegion("Western Europe", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCitiesByCountry() for cities within a specific country with a limit.
     * Mocks a result set containing one city and verifies retrieval by country code.
     */
    @Test
    public void testGetTopPopulatedCitiesByCountry() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByCountry("ITA", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCitiesByDistrict() for cities within a specific district with a limit.
     * Mocks a result set containing one city and verifies retrieval by district name.
     */
    @Test
    public void testGetTopPopulatedCitiesByDistrict() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByDistrict("California", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCapitalCitiesByPopulation() for retrieval of all capital cities by population.
     * Mocks a result set containing one capital city and verifies the method retrieves it.
     */
    @Test
    public void testGetCapitalCitiesByPopulation() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCapitalCitiesByPopulation();
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCapitalCitiesByContinent() for capital cities within a specific continent.
     * Mocks a result set containing one capital city in Asia and verifies retrieval.
     */
    @Test
    public void testGetCapitalCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCapitalCitiesByContinent("Asia");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCapitalCitiesByRegion() for capital cities within a specific region.
     * Mocks a result set containing one capital city in Southern Asia and verifies retrieval.
     */
    @Test
    public void testGetCapitalCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCapitalCitiesByRegion("Southern Asia");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCapitalCities() with a limit on the number of results.
     * Mocks a result set containing one capital city and verifies retrieval.
     */
    @Test
    public void testGetTopPopulatedCapitalCities() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCapitalCities(5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCapitalCitiesByContinent() for capital cities within a continent with a limit.
     * Mocks a result set containing one capital city in Europe and verifies retrieval.
     */
    @Test
    public void testGetTopPopulatedCapitalCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCapitalCitiesByContinent("Europe", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCapitalCitiesByRegion() for capital cities within a region with a limit.
     * Mocks a result set containing one capital city in the Middle East and verifies retrieval.
     */
    @Test
    public void testGetTopPopulatedCapitalCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCapitalCitiesByRegion("Middle East", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getPopulationByContinent() for normal retrieval of population data.
     * Mocks a result set containing one population report and verifies retrieval.
     */
    @Test
    public void testGetPopulationByContinent() throws SQLException {
        mockPopulationReportResultSet();
        List<PopulationReport> reports = databaseService.getPopulationByContinent();
        assertEquals(1, reports.size());
    }

    /**
     * Tests getPopulationByRegion() for normal retrieval of population data by region.
     * Mocks a result set containing one population report and verifies retrieval.
     */
    @Test
    public void testGetPopulationByRegion() throws SQLException {
        mockPopulationReportResultSet();
        List<PopulationReport> reports = databaseService.getPopulationByRegion();
        assertEquals(1, reports.size());
    }

    /**
     * Tests getPopulationByCountry() for normal retrieval of population data by country.
     * Mocks a result set containing one population report and verifies retrieval.
     */
    @Test
    public void testGetPopulationByCountry() throws SQLException {
        mockPopulationReportResultSet();
        List<PopulationReport> reports = databaseService.getPopulationByCountry();
        assertEquals(1, reports.size());
    }



    // Mocks a result set for country-related queries.
    private void mockCountryResultSet() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Code")).thenReturn("USA");
        when(mockResultSet.getString("CountryName")).thenReturn("United States");
        when(mockResultSet.getString("Continent")).thenReturn("North America");
        when(mockResultSet.getString("Region")).thenReturn("Northern America");
        when(mockResultSet.getInt("Population")).thenReturn(331002651);
        when(mockResultSet.getInt("CityID")).thenReturn(1);
        when(mockResultSet.getString("CapitalCityName")).thenReturn("Washington D.C.");
        when(mockResultSet.getString("District")).thenReturn("District of Columbia");
        when(mockResultSet.getInt("CapitalPopulation")).thenReturn(692683);
    }

    // Mocks a result set for city-related queries.
    private void mockCityResultSet() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("ID")).thenReturn(1);
        when(mockResultSet.getString("Name")).thenReturn("New York");
        when(mockResultSet.getString("CountryName")).thenReturn("USA");
        when(mockResultSet.getString("District")).thenReturn("New York");
        when(mockResultSet.getInt("Population")).thenReturn(8419000);
    }

    // Mocks a result set for population report-related queries.
    private void mockPopulationReportResultSet() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Name")).thenReturn("Asia");
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(4601375000L);
        when(mockResultSet.getLong("CityPopulation")).thenReturn(2000000000L);
        when(mockResultSet.getDouble("CityPopulationPercentage")).thenReturn(43.5);
        when(mockResultSet.getLong("NonCityPopulation")).thenReturn(2601375000L);
        when(mockResultSet.getDouble("NonCityPopulationPercentage")).thenReturn(56.5);
    }

    @Test
    public void testGetWorldPopulation() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Population")).thenReturn(7800000000L);
        long worldPopulation = databaseService.getWorldPopulation();
        assertEquals(7800000000L, worldPopulation);
    }

    // Method to test continent population retrieval.
    @Test
    public void testGetContinentPopulation() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Population")).thenReturn(4600000000L);
        long continentPopulation = databaseService.getContinentPopulation("Asia");
        assertEquals(4600000000L, continentPopulation);
    }

    // Method to test region population retrieval.
    @Test
    public void testGetRegionPopulation() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Population")).thenReturn(123456789L);
        long regionPopulation = databaseService.getRegionPopulation("Southeast Asia");
        assertEquals(123456789L, regionPopulation);
    }

    // Method to test country population retrieval.
    @Test
    public void testGetCountryPopulation() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Population")).thenReturn(331002651L);
        long countryPopulation = databaseService.getCountryPopulation("United States");
        assertEquals(331002651L, countryPopulation);
    }

    // Method to test district population retrieval.
    @Test
    public void testGetDistrictPopulation() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Population")).thenReturn(8419000L);
        long districtPopulation = databaseService.getDistrictPopulation("New York");
        assertEquals(8419000L, districtPopulation);
    }

    // Method to test city population retrieval.
    @Test
    public void testGetCityPopulation() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong("Population")).thenReturn(8336817L);
        long cityPopulation = databaseService.getCityPopulation("New York");
        assertEquals(8336817L, cityPopulation);
    }

    // Method to test language statistics retrieval.
    @Test
    public void testGetLanguageStatistics() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Language")).thenReturn("English");
        when(mockResultSet.getLong("SpeakerCount")).thenReturn(1500000000L);
        when(mockResultSet.getDouble("WorldPercentage")).thenReturn(20.0);

        List<LanguageReport> languageReports = databaseService.getLanguageStatistics();
        assertEquals(1, languageReports.size());
        assertEquals("English", languageReports.get(0).getLanguage());
        assertEquals(1500000000L, languageReports.get(0).getSpeakerCount());
        assertEquals(20.0, languageReports.get(0).getWorldPercentage());
    }

    // Method to test language statistics display.
    @Test
    public void testDisplayLanguageStatistics() {
        LanguageReport report = new LanguageReport();
        report.setLanguage("English");
        report.setSpeakerCount(1500000000L);
        report.setWorldPercentage(20.0);

        List<LanguageReport> languageReports = List.of(report);

        // Capture the output of the display method
        databaseService.displayLanguageStatistics(languageReports);
    }
}

