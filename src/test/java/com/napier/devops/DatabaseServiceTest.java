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
 * Unit test class for the DatabaseService class, using Mockito to mock database interactions.
 * This test suite verifies the functionality of DatabaseService methods under various scenarios,
 * including successful queries and exception handling.
 */
public class DatabaseServiceTest {

    // Mock objects for simulating database components
    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    // Injecting the mocks into an instance of DatabaseService for testing
    @InjectMocks
    private DatabaseService databaseService;

    /**
     * Sets up the mock objects before each test case.
     * Configures behavior for Connection, PreparedStatement, and ResultSet.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    /**
     * Tests the disconnect method when no connection is available.
     * Ensures that no exception occurs and the connection remains null.
     */
    @Test
    public void testDisconnectWithoutConnection() {
        databaseService.disconnect();
        assertNull(databaseService.getConnection());
    }

    /**
     * Tests exception handling in executeCountryQuery by throwing an SQLException.
     * Verifies that an empty list is returned when an exception occurs.
     */
    @Test
    public void testExecuteCountryQueryException() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("SQL Error"));
        List<Country> countries = databaseService.getCountriesByPopulation();
        assertTrue(countries.isEmpty(), "Expected empty list on SQLException");
    }

    /**
     * Tests exception handling in executeCityQuery by throwing an SQLException.
     * Verifies that an empty list is returned when an exception occurs.
     */
    @Test
    public void testExecuteCityQueryException() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("SQL Error"));
        List<City> cities = databaseService.getAllCitiesByPopulation();
        assertTrue(cities.isEmpty(), "Expected empty list on SQLException");
    }

    /**
     * Tests exception handling in executePopulationReportQuery by throwing an SQLException.
     * Verifies that an empty list is returned when an exception occurs.
     */
    @Test
    public void testExecutePopulationReportQueryException() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("SQL Error"));
        List<PopulationReport> reports = databaseService.getPopulationByContinent();
        assertTrue(reports.isEmpty(), "Expected empty list on SQLException");
    }

    /**
     * Tests normal execution of getCountriesByPopulation with mocked result set.
     */
    @Test
    public void testGetCountriesByPopulation() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getCountriesByPopulation();
        assertEquals(1, countries.size());
    }

    /**
     * Tests getCountriesByContinent with a mocked result set for a specific continent.
     */
    @Test
    public void testGetCountriesByContinent() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getCountriesByContinent("Asia");
        assertEquals(1, countries.size());
    }

    /**
     * Tests getCountriesByRegion with a mocked result set for a specific region.
     */
    @Test
    public void testGetCountriesByRegion() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getCountriesByRegion("Western Europe");
        assertEquals(1, countries.size());
    }

    /**
     * Tests getTopPopulatedCountries with a mocked result set for top N countries.
     */
    @Test
    public void testGetTopPopulatedCountries() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getTopPopulatedCountries(5);
        assertEquals(1, countries.size());
    }

    /**
     * Tests getTopPopulatedCountriesByContinent with a mocked result set for top N countries in a continent.
     */
    @Test
    public void testGetTopPopulatedCountriesByContinent() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getTopPopulatedCountriesByContinent("Asia", 5);
        assertEquals(1, countries.size());
    }

    /**
     * Tests getTopPopulatedCountriesByRegion with a mocked result set for top N countries in a region.
     */
    @Test
    public void testGetTopPopulatedCountriesByRegion() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getTopPopulatedCountriesByRegion("Europe", 5);
        assertEquals(1, countries.size());
    }

    /**
     * Tests getAllCitiesByPopulation with a mocked result set.
     */
    @Test
    public void testGetAllCitiesByPopulation() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getAllCitiesByPopulation();
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCitiesByContinent with a mocked result set for a specific continent.
     */
    @Test
    public void testGetCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByContinent("Asia");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCitiesByRegion with a mocked result set for a specific region.
     */
    @Test
    public void testGetCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByRegion("Southern Asia");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCitiesByCountry with a mocked result set for a specific country code.
     */
    @Test
    public void testGetCitiesByCountry() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByCountry("FRA");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCitiesByDistrict with a mocked result set for a specific district.
     */
    @Test
    public void testGetCitiesByDistrict() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByDistrict("New York");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCities with a mocked result set for top N cities globally.
     */
    @Test
    public void testGetTopPopulatedCities() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCities(5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCitiesByContinent with a mocked result set for top N cities in a continent.
     */
    @Test
    public void testGetTopPopulatedCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByContinent("Europe", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCitiesByRegion with a mocked result set for top N cities in a region.
     */
    @Test
    public void testGetTopPopulatedCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByRegion("Western Europe", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCitiesByCountry with a mocked result set for top N cities in a country.
     */
    @Test
    public void testGetTopPopulatedCitiesByCountry() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByCountry("ITA", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCitiesByDistrict with a mocked result set for top N cities in a district.
     */
    @Test
    public void testGetTopPopulatedCitiesByDistrict() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByDistrict("California", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCapitalCitiesByPopulation with a mocked result set.
     */
    @Test
    public void testGetCapitalCitiesByPopulation() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCapitalCitiesByPopulation();
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCapitalCitiesByContinent with a mocked result set for a specific continent.
     */
    @Test
    public void testGetCapitalCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCapitalCitiesByContinent("Asia");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getCapitalCitiesByRegion with a mocked result set for a specific region.
     */
    @Test
    public void testGetCapitalCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCapitalCitiesByRegion("Southern Asia");
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCapitalCities with a mocked result set for top N capital cities globally.
     */
    @Test
    public void testGetTopPopulatedCapitalCities() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCapitalCities(5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCapitalCitiesByContinent with a mocked result set for top N capital cities in a continent.
     */
    @Test
    public void testGetTopPopulatedCapitalCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCapitalCitiesByContinent("Europe", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getTopPopulatedCapitalCitiesByRegion with a mocked result set for top N capital cities in a region.
     */
    @Test
    public void testGetTopPopulatedCapitalCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCapitalCitiesByRegion("Middle East", 5);
        assertEquals(1, cities.size());
    }

    /**
     * Tests getPopulationByContinent with a mocked result set for population data by continent.
     */
    @Test
    public void testGetPopulationByContinent() throws SQLException {
        mockPopulationReportResultSet();
        List<PopulationReport> reports = databaseService.getPopulationByContinent();
        assertEquals(1, reports.size());
    }

    /**
     * Tests getPopulationByRegion with a mocked result set for population data by region.
     */
    @Test
    public void testGetPopulationByRegion() throws SQLException {
        mockPopulationReportResultSet();
        List<PopulationReport> reports = databaseService.getPopulationByRegion();
        assertEquals(1, reports.size());
    }

    /**
     * Tests getPopulationByCountry with a mocked result set for population data by country.
     */
    @Test
    public void testGetPopulationByCountry() throws SQLException {
        mockPopulationReportResultSet();
        List<PopulationReport> reports = databaseService.getPopulationByCountry();
        assertEquals(1, reports.size());
    }

    /**
     * Mocks the ResultSet for country queries with predefined values.
     */
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

    /**
     * Mocks the ResultSet for city queries with predefined values.
     */
    private void mockCityResultSet() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("ID")).thenReturn(1);
        when(mockResultSet.getString("Name")).thenReturn("New York");
        when(mockResultSet.getString("CountryName")).thenReturn("USA");
        when(mockResultSet.getString("District")).thenReturn("New York");
        when(mockResultSet.getInt("Population")).thenReturn(8419000);
    }

    /**
     * Mocks the ResultSet for population report queries with predefined values.
     */
    private void mockPopulationReportResultSet() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("Name")).thenReturn("Asia");
        when(mockResultSet.getLong("TotalPopulation")).thenReturn(4601375000L);
        when(mockResultSet.getLong("CityPopulation")).thenReturn(2000000000L);
        when(mockResultSet.getDouble("CityPopulationPercentage")).thenReturn(43.5);
        when(mockResultSet.getLong("NonCityPopulation")).thenReturn(2601375000L);
        when(mockResultSet.getDouble("NonCityPopulationPercentage")).thenReturn(56.5);
    }
}