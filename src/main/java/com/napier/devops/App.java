package com.napier.devops;

import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;


public class App {

    public static void main(String[] args) {
        // Create a new instance of the DatabaseService class
        DatabaseService dbService = new DatabaseService();

        // Connect to the database
        dbService.connect();

        // Create a NumberFormat instance for formatting population numbers with commas
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

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
        List<PopulationReport> continentPopulationData = dbService.getPopulationByContinent();
        dbService.displayPopulationData(continentPopulationData);

        // 24. Retrieve and display population data by region
        System.out.println("\nPopulation Data by Region:");
        List<PopulationReport> regionPopulationData = dbService.getPopulationByRegion();
        dbService.displayPopulationData(regionPopulationData);

        // 25. Retrieve and display population data by country
        System.out.println("\nPopulation Data by Country:");
        List<PopulationReport> countryPopulationData = dbService.getPopulationByCountry();
        dbService.displayPopulationData(countryPopulationData);

        // 26. Display world population
        System.out.println("\nWorld Population: " + numberFormat.format(dbService.getWorldPopulation()));

        // 27. Display population of a specific continent
        continent = "Asia";
        System.out.println("Population of " + continent + ": " + numberFormat.format(dbService.getContinentPopulation(continent)));

        // 28. Display population of a specific region
        region = "Western Europe";
        System.out.println("Population of " + region + ": " + numberFormat.format(dbService.getRegionPopulation(region)));

        // 29. Display population of a specific country
        countryCode = "USA";
        System.out.println("Population of " + countryCode + ": " + numberFormat.format(dbService.getCountryPopulation(countryCode)));

        // 30. Display population of a specific district
        district = "California";
        System.out.println("Population of " + district + ": " + numberFormat.format(dbService.getDistrictPopulation(district)));

        // 31. Display population of a specific city
        String cityName = "Los Angeles";
        System.out.println("Population of " + cityName + ": " + numberFormat.format(dbService.getCityPopulation(cityName)));

        // 32. Display language report
        List<LanguageReport> languageReports = dbService.getLanguageSpeakers();
        dbService.displayLanguageReports(languageReports);

        // Disconnect from the database
        dbService.disconnect();
    }
}
