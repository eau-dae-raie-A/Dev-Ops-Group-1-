package com.napier.devops;

import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class App {

    private final DatabaseService dbService;

    /**
     * Constructor for dependency injection.
     *
     * @param dbService The DatabaseService instance to be used by this App.
     */
    public App(DatabaseService dbService) {
        this.dbService = dbService;
    }

    /**
     * Main method to initialize and run the application.
     *
     * @param args Command-line arguments for database connection.
     */
    public static void main(String[] args) {
        // Initialize DatabaseService
        DatabaseService dbService = new DatabaseService();

        // Connect to the database
        if (args.length < 1) {
            dbService.connect("localhost:33060", 30000);
        } else {
            dbService.connect(args[0], Integer.parseInt(args[1]));
        }

        // Create App instance and run the program
        App app = new App(dbService);
        app.run();

        // Disconnect from the database
        dbService.disconnect();
    }

    /**
     * Runs the application logic.
     * Retrieves and displays various population reports and statistics.
     */
    public void run() {
        // Create a NumberFormat instance for formatting population numbers with commas
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        // 1. Retrieve and display all countries by population (Global)
        System.out.println("All Countries by Population:");
        List<Country> countries = dbService.getCountriesByPopulation();
        dbService.displayCountries(countries);

        // 2. Retrieve and display countries by continent
        String continent = "Asia";
        System.out.println("\nCountries in the " + continent + " by Population:");
        List<Country> asianCountries = dbService.getCountriesByContinent(continent);
        dbService.displayCountries(asianCountries);

        // 3. Retrieve and display countries by region
        String region = "Middle East";
        System.out.println("\nCountries in the " + region + " by Population:");
        List<Country> middleEastCountries = dbService.getCountriesByRegion(region);
        dbService.displayCountries(middleEastCountries);

        // 4. Retrieve and display top N populated countries in the world
        int topN = 8;
        System.out.println("\nTop " + topN + " Populated Countries in the World:");
        List<Country> topPopulatedCountries = dbService.getTopPopulatedCountries(topN);
        dbService.displayCountries(topPopulatedCountries);

        // 5. Retrieve and display the top N populated countries in a specific continent
        continent = "Europe";
        System.out.println("\nTop " + topN + " Populated Countries in " + continent + ":");
        List<Country> topCountriesInContinent = dbService.getTopPopulatedCountriesByContinent(continent, topN);
        dbService.displayCountries(topCountriesInContinent);

        // 6. Retrieve and display the top N populated countries in a specific region
        region = "Polynesia";
        System.out.println("\nTop " + topN + " Populated Countries in " + region + ":");
        List<Country> topCountriesInRegion = dbService.getTopPopulatedCountriesByRegion(region, topN);
        dbService.displayCountries(topCountriesInRegion);

        // 7. Retrieve and display all cities by population (Global)
        System.out.println("\nAll Cities by Population:");
        List<City> allCities = dbService.getAllCitiesByPopulation();
        dbService.displayCities(allCities);

        // 8. Retrieve and display cities by continent
        continent = "North America";
        System.out.println("\nCities in " + continent + " by Population:");
        List<City> citiesInContinent = dbService.getCitiesByContinent(continent);
        dbService.displayCities(citiesInContinent);

        // 9. Retrieve and display cities by region
        region = "Polynesia";
        System.out.println("\nCities in " + region + " by Population:");
        List<City> citiesInRegion = dbService.getCitiesByRegion(region);
        dbService.displayCities(citiesInRegion);

        // 10. Retrieve and display cities by country
        String countryCode = "AFG";
        System.out.println("\nCities in Country (" + countryCode + ") by Population:");
        List<City> citiesInCountry = dbService.getCitiesByCountry(countryCode);
        dbService.displayCities(citiesInCountry);

        // 11. Retrieve and display cities by district
        String district = "Noord-Holland";
        System.out.println("\nCities in District (" + district + ") by Population:");
        List<City> citiesInDistrict = dbService.getCitiesByDistrict(district);
        dbService.displayCities(citiesInDistrict);

        // 12. Retrieve and display the top N populated cities in the world
        System.out.println("\nTop " + topN + " Populated Cities in the World:");
        List<City> topPopulatedCities = dbService.getTopPopulatedCities(topN);
        dbService.displayCities(topPopulatedCities);

        // 13. Retrieve and display the top N populated cities in a continent
        continent = "Asia";
        System.out.println("\nTop " + topN + " Populated Cities in " + continent + ":");
        List<City> topCitiesInContinent = dbService.getTopPopulatedCitiesByContinent(continent, topN);
        dbService.displayCities(topCitiesInContinent);

        // 14. Retrieve and display the top N populated cities in a region
        region = "Western Europe";
        System.out.println("\nTop " + topN + " Populated Cities in " + region + ":");
        List<City> topCitiesInRegion = dbService.getTopPopulatedCitiesByRegion(region, topN);
        dbService.displayCities(topCitiesInRegion);

        // 15. Retrieve and display the top N populated cities in a country
        countryCode = "USA";
        System.out.println("\nTop " + topN + " Populated Cities in Country " + countryCode + ":");
        List<City> topCitiesInCountry = dbService.getTopPopulatedCitiesByCountry(countryCode, topN);
        dbService.displayCities(topCitiesInCountry);

        // 16. Retrieve and display the top N populated cities in a district
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
    }
}
