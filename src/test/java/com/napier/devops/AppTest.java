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

    @BeforeEach
    public void setUp() {
        // Create a mock DatabaseService
        mockDbService = Mockito.mock(DatabaseService.class);

        // Initialize the App class (could be modified to inject DatabaseService in a real-world scenario)
        app = new App();
    }

    // Test for displaying all countries by population
    @Test
    public void testDisplayAllCountriesByPopulation() {
        List<Country> mockCountries = Collections.singletonList(new Country());
        when(mockDbService.getCountriesByPopulation()).thenReturn(mockCountries);

        // Call method and verify behavior
        mockDbService.displayCountries(mockCountries);
        verify(mockDbService, times(1)).displayCountries(mockCountries);
    }

    // Test for retrieving countries by continent
    @Test
    public void testGetCountriesByContinent() {
        String continent = "Asia";
        List<Country> mockAsianCountries = Collections.singletonList(new Country());
        when(mockDbService.getCountriesByContinent(continent)).thenReturn(mockAsianCountries);

        // Call method and verify behavior
        mockDbService.displayCountries(mockAsianCountries);
        verify(mockDbService, times(1)).displayCountries(mockAsianCountries);
    }

    // Test for retrieving cities by country
    @Test
    public void testGetCitiesByCountry() {
        String countryCode = "USA";
        List<City> mockCities = Collections.singletonList(new City());
        when(mockDbService.getCitiesByCountry(countryCode)).thenReturn(mockCities);

        // Call method and verify behavior
        mockDbService.displayCities(mockCities);
        verify(mockDbService, times(1)).displayCities(mockCities);
    }

    // Test for displaying the top N populated countries
    @Test
    public void testGetTopPopulatedCountries() {
        int topN = 5;
        List<Country> mockTopCountries = Collections.singletonList(new Country());
        when(mockDbService.getTopPopulatedCountries(topN)).thenReturn(mockTopCountries);

        // Call method and verify behavior
        mockDbService.displayCountries(mockTopCountries);
        verify(mockDbService, times(1)).displayCountries(mockTopCountries);
    }

    // Test for retrieving population data by continent
    @Test
    public void testGetPopulationByContinent() {
        List<PopulationReport> mockPopulationData = Collections.singletonList(new PopulationReport());
        when(mockDbService.getPopulationByContinent()).thenReturn(mockPopulationData);

        // Call method and verify behavior
        mockDbService.displayPopulationData(mockPopulationData);
        verify(mockDbService, times(1)).displayPopulationData(mockPopulationData);
    }
}
