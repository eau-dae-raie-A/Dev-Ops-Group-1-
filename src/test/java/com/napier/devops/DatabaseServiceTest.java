package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DatabaseServiceTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private DatabaseService databaseService;


    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }


    // Test disconnect with no connection available
    @Test
    public void testDisconnectWithoutConnection() {
        databaseService.disconnect();
        assertNull(databaseService.getConnection());
    }

    // Test exception in executeCountryQuery
    @Test
    public void testExecuteCountryQueryException() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("SQL Error"));
        List<Country> countries = databaseService.getCountriesByPopulation();
        assertTrue(countries.isEmpty(), "Expected empty list on SQLException");
    }

    // Test exception in executeCityQuery
    @Test
    public void testExecuteCityQueryException() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("SQL Error"));
        List<City> cities = databaseService.getAllCitiesByPopulation();
        assertTrue(cities.isEmpty(), "Expected empty list on SQLException");
    }

    // Test population query exceptions
    @Test
    public void testExecutePopulationReportQueryException() throws SQLException {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("SQL Error"));
        List<PopulationReport> reports = databaseService.getPopulationByContinent();
        assertTrue(reports.isEmpty(), "Expected empty list on SQLException");
    }

    // Tests for normal cases with mock result sets for coverage
    @Test
    public void testGetCountriesByPopulation() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getCountriesByPopulation();
        assertEquals(1, countries.size());
    }

    @Test
    public void testGetCountriesByContinent() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getCountriesByContinent("Asia");
        assertEquals(1, countries.size());
    }

    @Test
    public void testGetCountriesByRegion() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getCountriesByRegion("Western Europe");
        assertEquals(1, countries.size());
    }

    @Test
    public void testGetTopPopulatedCountries() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getTopPopulatedCountries(5);
        assertEquals(1, countries.size());
    }

    @Test
    public void testGetTopPopulatedCountriesByContinent() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getTopPopulatedCountriesByContinent("Asia", 5);
        assertEquals(1, countries.size());
    }

    @Test
    public void testGetTopPopulatedCountriesByRegion() throws SQLException {
        mockCountryResultSet();
        List<Country> countries = databaseService.getTopPopulatedCountriesByRegion("Europe", 5);
        assertEquals(1, countries.size());
    }

    @Test
    public void testGetAllCitiesByPopulation() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getAllCitiesByPopulation();
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByContinent("Asia");
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByRegion("Southern Asia");
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetCitiesByCountry() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByCountry("FRA");
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetCitiesByDistrict() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCitiesByDistrict("New York");
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetTopPopulatedCities() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCities(5);
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetTopPopulatedCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByContinent("Europe", 5);
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetTopPopulatedCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByRegion("Western Europe", 5);
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetTopPopulatedCitiesByCountry() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByCountry("ITA", 5);
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetTopPopulatedCitiesByDistrict() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCitiesByDistrict("California", 5);
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetCapitalCitiesByPopulation() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCapitalCitiesByPopulation();
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetCapitalCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCapitalCitiesByContinent("Asia");
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetCapitalCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getCapitalCitiesByRegion("Southern Asia");
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetTopPopulatedCapitalCities() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCapitalCities(5);
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetTopPopulatedCapitalCitiesByContinent() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCapitalCitiesByContinent("Europe", 5);
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetTopPopulatedCapitalCitiesByRegion() throws SQLException {
        mockCityResultSet();
        List<City> cities = databaseService.getTopPopulatedCapitalCitiesByRegion("Middle East", 5);
        assertEquals(1, cities.size());
    }

    @Test
    public void testGetPopulationByContinent() throws SQLException {
        mockPopulationReportResultSet();
        List<PopulationReport> reports = databaseService.getPopulationByContinent();
        assertEquals(1, reports.size());
    }

    @Test
    public void testGetPopulationByRegion() throws SQLException {
        mockPopulationReportResultSet();
        List<PopulationReport> reports = databaseService.getPopulationByRegion();
        assertEquals(1, reports.size());
    }

    @Test
    public void testGetPopulationByCountry() throws SQLException {
        mockPopulationReportResultSet();
        List<PopulationReport> reports = databaseService.getPopulationByCountry();
        assertEquals(1, reports.size());
    }

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

    private void mockCityResultSet() throws SQLException {
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("ID")).thenReturn(1);
        when(mockResultSet.getString("Name")).thenReturn("New York");
        when(mockResultSet.getString("CountryName")).thenReturn("USA");
        when(mockResultSet.getString("District")).thenReturn("New York");
        when(mockResultSet.getInt("Population")).thenReturn(8419000);
    }

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
