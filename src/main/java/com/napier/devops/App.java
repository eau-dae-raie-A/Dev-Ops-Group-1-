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

        // 12. Retrieve and display the top N populated cities in the world
        System.out.println("\nTop " + topN + " Populated Cities in the World:");
        List<City> topPopulatedCities = dbService.getTopPopulatedCities(topN);
        dbService.displayCities(topPopulatedCities);

        // 13. Top N Populated Cities in a Continent
        continent = "Asia";
        System.out.println("\nTop " + topN + " Populated Cities in " + continent + ":");
        List<City> topCitiesInContinent = dbService.getTopPopulatedCitiesByContinent(continent, topN);
        dbService.displayCities(topCitiesInContinent);

        // 14. Top N Populated Cities in a Region
        region = "Western Europe";
        System.out.println("\nTop " + topN + " Populated Cities in " + region + ":");
        List<City> topCitiesInRegion = dbService.getTopPopulatedCitiesByRegion(region, topN);
        dbService.displayCities(topCitiesInRegion);

        // 15. Top N Populated Cities in a Country
        countryCode = "USA";
        System.out.println("\nTop " + topN + " Populated Cities in Country " + countryCode + ":");
        List<City> topCitiesInCountry = dbService.getTopPopulatedCitiesByCountry(countryCode, topN);
        dbService.displayCities(topCitiesInCountry);

        // 16. Top N Populated Cities in a District
        district = "California";
        System.out.println("\nTop " + topN + " Populated Cities in District " + district + ":");
        List<City> topCitiesInDistrict = dbService.getTopPopulatedCitiesByDistrict(district, topN);
        dbService.displayCities(topCitiesInDistrict);

        // 17. Retrieve and display all capital cities in the world by population
        System.out.println("\nAll Capital Cities in the World by Population:");
        List<City> capitalCitiesWorld = dbService.getCapitalCitiesByPopulation();
        dbService.displayCities(capitalCitiesWorld);

        // 18. Retrieve and display capital cities in a continent by population
        continent = "Asia";
        System.out.println("\nCapital Cities in " + continent + " by Population:");
        List<City> capitalCitiesContinent = dbService.getCapitalCitiesByContinent(continent);
        dbService.displayCities(capitalCitiesContinent);

        // 19. Retrieve and display capital cities in a region by population
        region = "Middle East";
        System.out.println("\nCapital Cities in " + region + " by Population:");
        List<City> capitalCitiesRegion = dbService.getCapitalCitiesByRegion(region);
        dbService.displayCities(capitalCitiesRegion);

        // 20. Retrieve and display the top N populated capital cities in the world
        System.out.println("\nTop " + topN + " Populated Capital Cities in the World:");
        List<City> topCapitalCitiesWorld = dbService.getTopPopulatedCapitalCities(topN);
        dbService.displayCities(topCapitalCitiesWorld);

        // 21. Retrieve and display the top N populated capital cities in a continent
        continent = "Asia";
        System.out.println("\nTop " + topN + " Populated Capital Cities in " + continent + ":");
        List<City> topCapitalCitiesContinent = dbService.getTopPopulatedCapitalCitiesByContinent(continent, topN);
        dbService.displayCities(topCapitalCitiesContinent);

        // 22. Retrieve and display the top N populated capital cities in a region
        region = "Western Europe";
        System.out.println("\nTop " + topN + " Populated Capital Cities in " + region + ":");
        List<City> topCapitalCitiesRegion = dbService.getTopPopulatedCapitalCitiesByRegion(region, topN);
        dbService.displayCities(topCapitalCitiesRegion);

        // 23. Retrieve and display population data by continent
        System.out.println("\nPopulation Data by Continent:");
        List<PopulationData> continentPopulationData = dbService.getPopulationByContinent();
        dbService.displayPopulationData(continentPopulationData);

        // 24. Retrieve and display the population data by region
        System.out.println("\nPopulation Data by Region:");
        List<PopulationData> regionPopulationData = dbService.getPopulationByRegion();
        dbService.displayPopulationData(regionPopulationData);

        // 25. Retrieve and display the population data by country
        System.out.println("\nPopulation Data by Country:");
        List<PopulationData> countryPopulationData = dbService.getPopulationByCountry();
        dbService.displayPopulationData(countryPopulationData);

        // Disconnect from the database
        dbService.disconnect();
    }
}
