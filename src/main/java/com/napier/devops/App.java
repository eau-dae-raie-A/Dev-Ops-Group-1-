package com.napier.devops;

import java.util.List;

public class App {

    public static void main(String[] args) {
        // Create a new  DatabaseService class
        DatabaseService dbService = new DatabaseService();

        // Connect to the database
        dbService.connect();

        // 1. Retrieve and display all countries by population
        System.out.println("All Countries by Population:");
        List<Country> countries = dbService.getCountriesByPopulation();
        dbService.displayCountries(countries);

        // 2. Retrieve and display countries by continent (Asia)
        String continent = "Asia";
        System.out.println("\nCountries in the Continent ( " + continent + " ) by Population:");
        List<Country> asianCountries = dbService.getCountriesByContinent(continent);
        dbService.displayCountries(asianCountries);

        // 3. Retrieve and display countries by region (Middle East)
        String region = "Middle East";
        System.out.println("\nCountries in the Region ( " + region + " ) by Population:");
        List<Country> middleEastCountries = dbService.getCountriesByRegion(region);
        dbService.displayCountries(middleEastCountries);

        // 4. Retrieve and display top N populated countries in the world
        int topN = 8;
        System.out.println("\nTop " + topN + " Populated Countries in the World:");
        List<Country> topPopulatedCountries = dbService.getTopPopulatedCountries(topN);
        dbService.displayCountries(topPopulatedCountries);

        // 5. Retrieve and display the top N populated countries in a specific continent (e.g., Europe)
        continent="Europe";
        System.out.println("\nTop " + topN + " Populated Countries in the Continent ( " +continent+ " ):");
        List<Country> topCountriesInContinent = dbService.getTopPopulatedCountriesByContinent(continent, topN);
        dbService.displayCountries(topCountriesInContinent);

        // 6. Retrieve and display the top N populated countries in a specific region (e.g., Polynesia)
        region="Polynesia";
        System.out.println("\nTop " + topN + " Populated Countries in the Region ( " +region+ ") :");
        List<Country> topCountriesInRegion = dbService.getTopPopulatedCountriesByRegion(region, topN);
        dbService.displayCountries(topCountriesInRegion);

        // 7. Retrieve and display all cities by population
        System.out.println("\nAll Cities by Population:");
        List<City> allCities = dbService.getAllCitiesByPopulation();
        dbService.displayCities(allCities);

        // 8. Retrieve and display cities by continent (e.g., North America)
        continent="North America";
        System.out.println("\nCities in the Continent ( "+continent+" ) by Population:");
        List<City> citiesInContinent = dbService.getCitiesByContinent(continent);
        dbService.displayCities(citiesInContinent);

        // 9. Retrieve and display cities by region (e.g., Caribbean)
        region = "Caribbean";
        System.out.println("\nCities in the Region ( "+region+" ) by Population:");
        List<City> citiesInRegion = dbService.getCitiesByRegion(region);
        dbService.displayCities(citiesInRegion);

        // 10. Retrieve and display cities by country (e.g., AFG)
        String countryCode = "AFG";
        System.out.println("\nCities in Country ("+ countryCode + ") by Population:");
        List<City> citiesInCountry = dbService.getCitiesByCountry(countryCode);
        dbService.displayCities(citiesInCountry);

        // 11. Retrieve and display cities by district (e.g., Noord-Holland)
        String district = "Noord-Holland";
        System.out.println("\nCities in District ("+ district + ") by Population:");
        List<City> citiesInDistrict = dbService.getCitiesByDistrict(district);
        dbService.displayCities(citiesInDistrict);

        // Disconnect from the database
        dbService.disconnect();
    }
}
