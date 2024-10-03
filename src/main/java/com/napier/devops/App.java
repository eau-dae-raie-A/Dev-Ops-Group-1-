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
        System.out.println("\nCountries in the " + continent + " by Population:");
        List<Country> asianCountries = dbService.getCountriesByContinent(continent);
        dbService.displayCountries(asianCountries);

        // 3. Retrieve and display countries by region (e.g., Middle East)
        String region = "Middle East";
        System.out.println("\nCountries in the " + region + " by Population:");
        List<Country> middleEastCountries = dbService.getCountriesByRegion(region);
        dbService.displayCountries(middleEastCountries);

        // 4. Retrieve and display top N populated countries in the world
        int topN = 8;
        System.out.println("\nTop " + topN + " Populated Countries in the World:");
        List<Country> topPopulatedCountries = dbService.getTopPopulatedCountries(topN);
        dbService.displayCountries(topPopulatedCountries);

        // 5. Retrieve and display the top N populated countries in a specific continent (e.g., Asia)
        continent="Europe";
        System.out.println("\nTop " + topN + " Populated Countries in " +continent+ ":");
        List<Country> topCountriesInContinent = dbService.getTopPopulatedCountriesByContinent(continent, topN);
        dbService.displayCountries(topCountriesInContinent);

        // 6. Retrieve and display the top N populated countries in a specific region (e.g., Western Europe)
        region="Polynesia";
        System.out.println("\nTop " + topN + " Populated Countries in " +region+ ":");
        List<Country> topCountriesInRegion = dbService.getTopPopulatedCountriesByRegion(region, topN);
        dbService.displayCountries(topCountriesInRegion);

        // 7. Retrieve and display all cities by population (Global)
        System.out.println("\nAll Cities by Population:");
        List<City> allCities = dbService.getAllCitiesByPopulation();
        dbService.displayCities(allCities);

        // 8. Retrieve and display cities by continent (e.g., Asia)
        continent="North America";
        System.out.println("\nCities in "+continent+" by Population:");
        List<City> citiesInContinent = dbService.getCitiesByContinent(continent);
        dbService.displayCities(citiesInContinent);

        // 9. Retrieve and display cities by region (e.g., Western Europe)
        region = "Polynesia";
        System.out.println("\nCities in "+region+" by Population:");
        List<City> citiesInRegion = dbService.getCitiesByRegion(region);
        dbService.displayCities(citiesInRegion);

        // 10. Retrieve and display cities by country (e.g., USA)
        String countryCode = "AFG";
        System.out.println("\nCities in Country ("+ countryCode + ") by Population:");
        List<City> citiesInCountry = dbService.getCitiesByCountry(countryCode);
        dbService.displayCities(citiesInCountry);

        // 11. Retrieve and display cities by district (e.g., California)
        String district = "Noord-Holland";
        System.out.println("\nCities in District ("+ district + ") by Population:");
        List<City> citiesInDistrict = dbService.getCitiesByDistrict(district);
        dbService.displayCities(citiesInDistrict);

        // Disconnect from the database
        dbService.disconnect();
    }
}
