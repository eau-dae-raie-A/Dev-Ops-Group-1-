package com.napier.devops;

import java.util.List;

public class App {

    public static void main(String[] args) {
        // Create a new instance of the DatabaseService class
        DatabaseService dbService = new DatabaseService();

        // Connect to the database
        dbService.connect();

        // 1. Retrieve and display all countries by population (Global)
        System.out.println("All Countries by Population:");
        List<Country> countries = dbService.getCountriesByPopulation();
        dbService.displayCountries(countries);

        // 2. Retrieve and display countries by continent (e.g., Asia)
        String continent = "Asia";
        System.out.println("\nCountries in Asia by Population:");
        List<Country> asianCountries = dbService.getCountriesByContinent(continent);
        dbService.displayCountries(asianCountries);

        // 3. Retrieve and display countries by region (e.g., Middle East)
        String region = "Middle East";
        System.out.println("\nCountries in the Middle East by Population:");
        List<Country> middleEastCountries = dbService.getCountriesByRegion(region);
        dbService.displayCountries(middleEastCountries);

        // 4. Retrieve and display top N populated countries in the world
        int topN = 8;
        System.out.println("\nTop " + topN + " Populated Countries in the World:");
        List<Country> topPopulatedCountries = dbService.getTopPopulatedCountries(topN);
        dbService.displayCountries(topPopulatedCountries);

        // 5. Retrieve and display the top N populated countries in a specific continent (e.g., Asia)
        System.out.println("\nTop " + topN + " Populated Countries in Asia:");
        List<Country> topCountriesInContinent = dbService.getTopPopulatedCountriesByContinent("Asia", topN);
        dbService.displayCountries(topCountriesInContinent);

        // 6. Retrieve and display the top N populated countries in a specific region (e.g., Western Europe)
        System.out.println("\nTop " + topN + " Populated Countries in Western Europe:");
        List<Country> topCountriesInRegion = dbService.getTopPopulatedCountriesByRegion("Western Europe", topN);
        dbService.displayCountries(topCountriesInRegion);

        // 7. Retrieve and display all cities by population (Global)
        System.out.println("\nAll Cities by Population:");
        List<City> allCities = dbService.getAllCitiesByPopulation();
        dbService.displayCities(allCities);

        // 8. Retrieve and display cities by continent (e.g., Asia)
        System.out.println("\nCities in Asia by Population:");
        List<City> citiesInAsia = dbService.getCitiesByContinent(continent);
        dbService.displayCities(citiesInAsia);

        // 9. Retrieve and display cities by region (e.g., Western Europe)
        region = "Western Europe";
        System.out.println("\nCities in Western Europe by Population:");
        List<City> citiesInRegion = dbService.getCitiesByRegion(region);
        dbService.displayCities(citiesInRegion);

        // Disconnect from the database
        dbService.disconnect();
    }
}
