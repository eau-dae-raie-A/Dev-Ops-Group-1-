package com.napier.devops;

import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * The App class is the main entry point for the application.
 * It utilizes a DatabaseService to connect to a database and retrieves
 * various population reports and statistics.
 * The application can be run with or without command-line arguments for the database connection.
 */

public class App {

    private final DatabaseService dbService;

    /**
     * Constructor for App class, which allows for dependency injection of DatabaseService.
     *
     * @param dbService The DatabaseService instance to be used by this App.
     */
    public App(DatabaseService dbService) {
        this.dbService = dbService;
    }

    /**
     * The main method initializes the DatabaseService and runs the application.
     * Command-line arguments can be provided to specify database connection details.
     *
     * @param args Command-line arguments: [0] Database host and port
     */
    public static void main(String[] args) {
        // Initialize DatabaseService
        DatabaseService dbService = new DatabaseService();

        // Connect to the database using command-line arguments or default parameters
        if (args.length < 1) {
            dbService.connect("localhost:33060", 30000);
        } else {
            dbService.connect(args[0], Integer.parseInt(args[1]));
        }

        // Create App instance and run the application logic
        App app = new App(dbService);
        app.run();

        // Disconnect from the database
        dbService.disconnect();
    }

    /**
     * Runs the core application logic, which retrieves and displays various population reports.
     * Population data includes countries, cities, and capital cities by global, continent, region,
     * country, district, and top N populated entities.
     */
    public void run() {
        // Create a NumberFormat instance for formatting population numbers with commas
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        // 1. Retrieve and display all countries by population (Global)
        System.out.println("All Countries by Population:");
        List<Country> countries = dbService.getCountriesByPopulation();
        dbService.displayCountries(countries);

        // 2. Retrieve and display countries by continent (example: Asia)
        String continent = "Asia";
        System.out.println("\nCountries in the " + continent + " by Population:");
        List<Country> asianCountries = dbService.getCountriesByContinent(continent);
        dbService.displayCountries(asianCountries);

        // 3. Retrieve and display countries by region (example: Middle East)
        String region = "Middle East";
        System.out.println("\nCountries in the " + region + " by Population:");
        List<Country> middleEastCountries = dbService.getCountriesByRegion(region);
        dbService.displayCountries(middleEastCountries);

        // 4. Retrieve and display the top N populated countries globally
        int topN = 8;
        System.out.println("\nTop " + topN + " Populated Countries in the World:");
        List<Country> topPopulatedCountries = dbService.getTopPopulatedCountries(topN);
        dbService.displayCountries(topPopulatedCountries);

        // 5. Retrieve and display the top N populated countries in a continent (example: Europe)
        continent = "Europe";
        System.out.println("\nTop " + topN + " Populated Countries in " + continent + ":");
        List<Country> topCountriesInContinent = dbService.getTopPopulatedCountriesByContinent(continent, topN);
        dbService.displayCountries(topCountriesInContinent);

        // 6. Retrieve and display the top N populated countries in a region (example: Polynesia)
        region = "Polynesia";
        System.out.println("\nTop " + topN + " Populated Countries in " + region + ":");
        List<Country> topCountriesInRegion = dbService.getTopPopulatedCountriesByRegion(region, topN);
        dbService.displayCountries(topCountriesInRegion);

        // 7. Retrieve and display all cities by population (Global)
        System.out.println("\nAll Cities by Population:");
        List<City> allCities = dbService.getAllCitiesByPopulation();
        dbService.displayCities(allCities);

        // 8. Retrieve and display cities by continent (example: North America)
        continent = "North America";
        System.out.println("\nCities in " + continent + " by Population:");
        List<City> citiesInContinent = dbService.getCitiesByContinent(continent);
        dbService.displayCities(citiesInContinent);

        // 9. Retrieve and display cities by region (example: Polynesia)
        region = "Polynesia";
        System.out.println("\nCities in " + region + " by Population:");
        List<City> citiesInRegion = dbService.getCitiesByRegion(region);
        dbService.displayCities(citiesInRegion);

        // 10. Retrieve and display cities by country code (example: AFG for Afghanistan)
        String countryCode = "AFG";
        System.out.println("\nCities in Country (" + countryCode + ") by Population:");
        List<City> citiesInCountry = dbService.getCitiesByCountry(countryCode);
        dbService.displayCities(citiesInCountry);

        // 11. Retrieve and display cities by district name (example: Noord-Holland)
        String district = "Noord-Holland";
        System.out.println("\nCities in District (" + district + ") by Population:");
        List<City> citiesInDistrict = dbService.getCitiesByDistrict(district);
        dbService.displayCities(citiesInDistrict);

        // 12. Retrieve and display the top N populated cities globally
        System.out.println("\nTop " + topN + " Populated Cities in the World:");
        List<City> topPopulatedCities = dbService.getTopPopulatedCities(topN);
        dbService.displayCities(topPopulatedCities);

        // 13. Retrieve and display the top N populated cities in a continent (example: Asia)
        continent = "Asia";
        System.out.println("\nTop " + topN + " Populated Cities in " + continent + ":");
        List<City> topCitiesInContinent = dbService.getTopPopulatedCitiesByContinent(continent, topN);
        dbService.displayCities(topCitiesInContinent);

        // 14. Retrieve and display the top N populated cities in a region (example: Western Europe)
        region = "Western Europe";
        System.out.println("\nTop " + topN + " Populated Cities in " + region + ":");
        List<City> topCitiesInRegion = dbService.getTopPopulatedCitiesByRegion(region, topN);
        dbService.displayCities(topCitiesInRegion);

        // 15. Retrieve and display the top N populated cities in a country (example: USA)
        countryCode = "USA";
        System.out.println("\nTop " + topN + " Populated Cities in Country " + countryCode + ":");
        List<City> topCitiesInCountry = dbService.getTopPopulatedCitiesByCountry(countryCode, topN);
        dbService.displayCities(topCitiesInCountry);

        // 16. Retrieve and display the top N populated cities in a district (example: California)
        district = "California";
        System.out.println("\nTop " + topN + " Populated Cities in District " + district + ":");
        List<City> topCitiesInDistrict = dbService.getTopPopulatedCitiesByDistrict(district, topN);
        dbService.displayCities(topCitiesInDistrict);

        // 17. Retrieve and display all capital cities globally by population
        System.out.println("\nAll Capital Cities in the World by Population:");
        List<City> capitalCitiesWorld = dbService.getCapitalCitiesByPopulation();
        dbService.displayCities(capitalCitiesWorld);

        // 18. Retrieve and display capital cities by continent (example: Asia)
        continent = "Asia";
        System.out.println("\nCapital Cities in " + continent + " by Population:");
        List<City> capitalCitiesContinent = dbService.getCapitalCitiesByContinent(continent);
        dbService.displayCities(capitalCitiesContinent);

        // 19. Retrieve and display capital cities by region (example: Middle East)
        region = "Middle East";
        System.out.println("\nCapital Cities in " + region + " by Population:");
        List<City> capitalCitiesRegion = dbService.getCapitalCitiesByRegion(region);
        dbService.displayCities(capitalCitiesRegion);

        // 20. Retrieve and display the top N populated capital cities globally
        System.out.println("\nTop " + topN + " Populated Capital Cities in the World:");
        List<City> topCapitalCitiesWorld = dbService.getTopPopulatedCapitalCities(topN);
        dbService.displayCities(topCapitalCitiesWorld);

        // 21. Retrieve and display the top N populated capital cities in a continent (example: Asia)
        continent = "Asia";
        System.out.println("\nTop " + topN + " Populated Capital Cities in " + continent + ":");
        List<City> topCapitalCitiesContinent = dbService.getTopPopulatedCapitalCitiesByContinent(continent, topN);
        dbService.displayCities(topCapitalCitiesContinent);

        // 22. Retrieve and display the top N populated capital cities in a region (example: Western Europe)
        region = "Western Europe";
        System.out.println("\nTop " + topN + " Populated Capital Cities in " + region + ":");
        List<City> topCapitalCitiesRegion = dbService.getTopPopulatedCapitalCitiesByRegion(region, topN);
        dbService.displayCities(topCapitalCitiesRegion);

        // 23. Retrieve and display population data by continent
        System.out.println("\nPopulation Data by Continent:");
        String fileName = "PopulationByContinent.md";
        List<PopulationReport> continentPopulationData = dbService.getPopulationByContinent();
        dbService.displayPopulationData(continentPopulationData);
        dbService.generatePopulationReportMarkdown(continentPopulationData, fileName);

        // 24. Retrieve and display population data by region
        System.out.println("\nPopulation Data by Region:");
        List<PopulationReport> regionPopulationData = dbService.getPopulationByRegion();
        dbService.displayPopulationData(regionPopulationData);

        // 25. Retrieve and display population data by country
        System.out.println("\nPopulation Data by Country:");
        List<PopulationReport> countryPopulationData = dbService.getPopulationByCountry();
        dbService.displayPopulationData(countryPopulationData);
    }
}
