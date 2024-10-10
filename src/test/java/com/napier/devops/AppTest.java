package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class AppTest {

    private DatabaseService mockDbService;
    private App app;

    /**
     * Sets up the test environment before each test.
     * Initializes mock DatabaseService and App instances.
     */
    @BeforeEach
    public void setUp() {
        mockDbService = Mockito.mock(DatabaseService.class);
        app = new App(mockDbService); // Injecting mock DatabaseService into App
    }

    /**
     * Tests that the App correctly displays all countries by population.
     * Verifies that displayCountries is called with the correct data.
     */
    @Test
    public void testRunDisplaysAllCountriesByPopulation() {
        List<Country> mockCountries = Collections.singletonList(new Country());
        when(mockDbService.getCountriesByPopulation()).thenReturn(mockCountries);

        app.run();

        verify(mockDbService, times(1)).displayCountries(mockCountries);
    }

    /**
     * Tests that the App correctly displays countries by continent.
     * Verifies that displayCountries is called with the correct continent data.
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
     * Tests that the App correctly displays countries by region.
     * Verifies that displayCountries is called with the correct region data.
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
     * Tests that the App correctly displays the top populated countries.
     * Verifies that displayCountries is called with the correct top countries data.
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
     * Tests that the App correctly displays the top populated countries by continent.
     * Verifies that displayCountries is called with the correct data for the continent.
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
     * Tests that the App correctly displays all cities by population.
     * Verifies that displayCities is called with the correct data.
     */
    @Test
    public void testRunDisplaysAllCitiesByPopulation() {
        List<City> mockCities = Collections.singletonList(new City());
        when(mockDbService.getAllCitiesByPopulation()).thenReturn(mockCities);

        app.run();

        verify(mockDbService, times(1)).displayCities(mockCities);
    }

    /**
     * Tests that the App correctly displays cities by continent.
     * Verifies that displayCities is called with the correct continent data.
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
     * Tests that the App correctly displays cities by region.
     * Verifies that displayCities is called with the correct region data.
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
     * Tests that the App correctly displays cities by country.
     * Verifies that displayCities is called with the correct country data.
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
     * Tests that the App correctly displays the top populated cities.
     * Verifies that displayCities is called with the correct top cities data.
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
     * Tests that the App correctly displays population data by continent.
     * Verifies that displayPopulationData is called with the correct data.
     */
    @Test
    public void testRunDisplaysPopulationByContinent() {
        List<PopulationReport> mockPopulationData = Collections.singletonList(new PopulationReport());
        when(mockDbService.getPopulationByContinent()).thenReturn(mockPopulationData);

        app.run();

        verify(mockDbService, times(1)).displayPopulationData(mockPopulationData);
    }

    /**
     * Tests that the App correctly displays population data by region.
     * Verifies that displayPopulationData is called with the correct region data.
     */
    @Test
    public void testRunDisplaysPopulationByRegion() {
        List<PopulationReport> mockPopulationData = Collections.singletonList(new PopulationReport());
        when(mockDbService.getPopulationByRegion()).thenReturn(mockPopulationData);

        app.run();

        verify(mockDbService, times(1)).displayPopulationData(mockPopulationData);
    }

    /**
     * Tests that the App correctly displays population data by country.
     * Verifies that displayPopulationData is called with the correct country data.
     */
    @Test
    public void testRunDisplaysPopulationByCountry() {
        List<PopulationReport> mockPopulationData = Collections.singletonList(new PopulationReport());
        when(mockDbService.getPopulationByCountry()).thenReturn(mockPopulationData);

        app.run();

        verify(mockDbService, times(1)).displayPopulationData(mockPopulationData);
    }
}
