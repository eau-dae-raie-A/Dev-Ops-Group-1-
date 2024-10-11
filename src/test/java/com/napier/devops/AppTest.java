package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the App class.
 * This class verifies that the App class interacts correctly with the DatabaseService
 * to retrieve and display various data sets (e.g., countries, cities, population reports).
 * It uses Mockito to mock DatabaseService, allowing isolated tests of the App class.
 */
public class AppTest {

    // Mock instance of DatabaseService for testing
    private DatabaseService mockDbService;

    // Instance of App with injected mock DatabaseService
    private App app;

    /**
     * Sets up the mock DatabaseService and initializes the App with the mock.
     * This method runs before each test to ensure a fresh instance of the mock and App.
     */
    @BeforeEach
    public void setUp() {
        mockDbService = Mockito.mock(DatabaseService.class);
        app = new App(mockDbService); // Injects the mock DatabaseService into App
    }

    /**
     * Test for displaying all countries by population.
     * Verifies that the App calls DatabaseService to retrieve and display the countries.
     */
    @Test
    public void testRunDisplaysAllCountriesByPopulation() {
        List<Country> mockCountries = Collections.singletonList(new Country());
        when(mockDbService.getCountriesByPopulation()).thenReturn(mockCountries);

        app.run();

        verify(mockDbService, times(1)).displayCountries(mockCountries);
    }

    /**
     * Test for displaying countries by continent.
     * Checks that the App retrieves and displays countries by continent from DatabaseService.
     */
    @Test
    public void testRunDisplaysCountriesByContinent() {
        String continent = "Asia";
        List<Country> mockAsianCountries = Collections.singletonList(new Country());
        when(mockDbService.getCountriesByContinent(continent)).thenReturn(mockAsianCountries);

        app.run();

        verify(mockDbService, times(1)).displayCountries(mockAsianCountries);
    }

    /**
     * Test for displaying countries by region.
     * Ensures that the App calls DatabaseService to retrieve and display countries by region.
     */
    @Test
    public void testRunDisplaysCountriesByRegion() {
        String region = "Middle East";
        List<Country> mockCountries = Collections.singletonList(new Country());
        when(mockDbService.getCountriesByRegion(region)).thenReturn(mockCountries);

        app.run();

        verify(mockDbService, times(1)).displayCountries(mockCountries);
    }

    /**
     * Test for displaying top populated countries.
     * Verifies that the App calls DatabaseService to retrieve and display the top populated countries.
     */
    @Test
    public void testRunDisplaysTopPopulatedCountries() {
        int topN = 8;
        List<Country> mockTopCountries = Collections.singletonList(new Country());
        when(mockDbService.getTopPopulatedCountries(topN)).thenReturn(mockTopCountries);

        app.run();

        verify(mockDbService, times(1)).displayCountries(mockTopCountries);
    }

    /**
     * Test for displaying top populated countries by continent.
     * Ensures that the App retrieves and displays top populated countries by continent.
     */
    @Test
    public void testRunDisplaysTopPopulatedCountriesByContinent() {
        String continent = "Europe";
        int topN = 8;
        List<Country> mockCountries = Collections.singletonList(new Country());
        when(mockDbService.getTopPopulatedCountriesByContinent(continent, topN)).thenReturn(mockCountries);

        app.run();

        verify(mockDbService, times(1)).displayCountries(mockCountries);
    }

    /**
     * Test for displaying all cities by population.
     * Confirms that the App calls DatabaseService to retrieve and display cities.
     */
    @Test
    public void testRunDisplaysAllCitiesByPopulation() {
        List<City> mockCities = Collections.singletonList(new City());
        when(mockDbService.getAllCitiesByPopulation()).thenReturn(mockCities);

        app.run();

        verify(mockDbService, times(1)).displayCities(mockCities);
    }

    /**
     * Test for displaying cities by continent.
     * Verifies that the App retrieves and displays cities by continent.
     */
    @Test
    public void testRunDisplaysCitiesByContinent() {
        String continent = "North America";
        List<City> mockCities = Collections.singletonList(new City());
        when(mockDbService.getCitiesByContinent(continent)).thenReturn(mockCities);

        app.run();

        verify(mockDbService, times(1)).displayCities(mockCities);
    }

    /**
     * Test for displaying cities by region.
     * Ensures that the App calls DatabaseService to retrieve and display cities by region.
     */
    @Test
    public void testRunDisplaysCitiesByRegion() {
        String region = "Polynesia";
        List<City> mockCities = Collections.singletonList(new City());
        when(mockDbService.getCitiesByRegion(region)).thenReturn(mockCities);

        app.run();

        verify(mockDbService, times(1)).displayCities(mockCities);
    }

    /**
     * Test for displaying cities by country code.
     * Verifies that the App retrieves and displays cities by country code.
     */
    @Test
    public void testRunDisplaysCitiesByCountry() {
        String countryCode = "AFG";
        List<City> mockCities = Collections.singletonList(new City());
        when(mockDbService.getCitiesByCountry(countryCode)).thenReturn(mockCities);

        app.run();

        verify(mockDbService, times(1)).displayCities(mockCities);
    }

    /**
     * Test for displaying top populated cities.
     * Confirms that the App calls DatabaseService to retrieve and display the top populated cities.
     */
    @Test
    public void testRunDisplaysTopPopulatedCities() {
        int topN = 8;
        List<City> mockCities = Collections.singletonList(new City());
        when(mockDbService.getTopPopulatedCities(topN)).thenReturn(mockCities);

        app.run();

        verify(mockDbService, times(1)).displayCities(mockCities);
    }

    /**
     * Test for displaying population data by continent.
     * Verifies that the App retrieves and displays population data grouped by continent.
     */
    @Test
    public void testRunDisplaysPopulationByContinent() {
        List<PopulationReport> mockPopulationData = Collections.singletonList(new PopulationReport());
        when(mockDbService.getPopulationByContinent()).thenReturn(mockPopulationData);

        app.run();

        verify(mockDbService, times(1)).displayPopulationData(mockPopulationData);
    }

    /**
     * Test for displaying population data by region.
     * Ensures that the App calls DatabaseService to retrieve and display population data by region.
     */
    @Test
    public void testRunDisplaysPopulationByRegion() {
        List<PopulationReport> mockPopulationData = Collections.singletonList(new PopulationReport());
        when(mockDbService.getPopulationByRegion()).thenReturn(mockPopulationData);

        app.run();

        verify(mockDbService, times(1)).displayPopulationData(mockPopulationData);
    }

    /**
     * Test for displaying population data by country.
     * Verifies that the App retrieves and displays population data grouped by country.
     */
    @Test
    public void testRunDisplaysPopulationByCountry() {
        List<PopulationReport> mockPopulationData = Collections.singletonList(new PopulationReport());
        when(mockDbService.getPopulationByCountry()).thenReturn(mockPopulationData);

        app.run();

        verify(mockDbService, times(1)).displayPopulationData(mockPopulationData);
    }
}
