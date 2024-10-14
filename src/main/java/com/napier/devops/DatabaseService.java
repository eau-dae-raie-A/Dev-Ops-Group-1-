package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class DatabaseService {

    // Connection object to connect to the database
    private Connection con = null;

    /**
     * Connect to the MySQL database with retry logic.
     *
     * @param location The database location (e.g., "localhost:33060").
     * @param delay    The delay in milliseconds between retries.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                con = null;  // Set the connection to null after closing
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Getter for the connection, used for testing purposes.
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * Execute a query related to countries, with optional parameters.
     *
     * @param query  The SQL query to execute.
     * @param params Optional query parameters.
     * @return A list of Country objects from the query results.
     */

    private List<Country> executeCountryQuery(String query, Object... params) {
        List<Country> countries = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof String) {
                    pstmt.setString(i + 1, (String) params[i]);
                } else if (params[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) params[i]);
                }
            }

            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    countries.add(mapCountryResultSet(rset));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    /**
     * Execute a query related to cities, with optional parameters.
     *
     * @param query  The SQL query to execute.
     * @param params Optional query parameters.
     * @return A list of City objects from the query results.
     */
    private List<City> executeCityQuery(String query, Object... params) {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof String) {
                    pstmt.setString(i + 1, (String) params[i]);
                } else if (params[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) params[i]);
                }
            }

            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    cities.add(mapCityResultSet(rset));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * Map a ResultSet row to a Country object.
     *
     * @param rset The ResultSet containing country data.
     * @return A Country object.
     * @throws SQLException if there is an error accessing the ResultSet.
     */
    private Country mapCountryResultSet(ResultSet rset) throws SQLException {
        Country country = new Country();
        country.setCode(rset.getString("Code"));
        country.setName(rset.getString("CountryName"));
        country.setContinent(rset.getString("Continent"));
        country.setRegion(rset.getString("Region"));
        country.setPopulation(rset.getInt("Population"));

        City capitalCity = new City();
        capitalCity.setId(rset.getInt("CityID"));
        capitalCity.setName(rset.getString("CapitalCityName"));
        capitalCity.setDistrict(rset.getString("District"));
        capitalCity.setPopulation(rset.getInt("CapitalPopulation"));

        country.setCapitalCity(capitalCity);
        return country;
    }

    /**
     * Map a ResultSet row to a City object.
     *
     * @param rset The ResultSet containing city data.
     * @return A City object.
     * @throws SQLException if there is an error accessing the ResultSet.
     */
    private City mapCityResultSet(ResultSet rset) throws SQLException {
        City city = new City();
        city.setId(rset.getInt("ID"));
        city.setName(rset.getString("Name"));
        city.setCountryCode(rset.getString("CountryName"));  // Assuming you want the country name
        city.setDistrict(rset.getString("District"));
        city.setPopulation(rset.getInt("Population"));
        return city;
    }

    // Query to get all countries by population
    public List<Country> getCountriesByPopulation() {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC";
        return executeCountryQuery(query);
    }

    // Query to get countries by continent
    public List<Country> getCountriesByContinent(String continent) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = ? " +
                "ORDER BY country.Population DESC";
        return executeCountryQuery(query, continent);
    }

    // Query to get countries by the region
    public List<Country> getCountriesByRegion(String region) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = ? " +
                "ORDER BY country.Population DESC";
        return executeCountryQuery(query, region);
    }

    // Query to get top N populated countries
    public List<Country> getTopPopulatedCountries(int n) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC LIMIT ?";
        return executeCountryQuery(query, n);
    }

    // Retrieves the top N populated countries in a specific continent
    public List<Country> getTopPopulatedCountriesByContinent(String continent, int N) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, country.Region, " +
                "country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = ? " +
                "ORDER BY country.Population DESC LIMIT ?";
        return executeCountryQuery(query, continent, N);
    }

    // Retrieves the top N populated countries in a specific region
    public List<Country> getTopPopulatedCountriesByRegion(String region, int N) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, country.Region, " +
                "country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = ? " +
                "ORDER BY country.Population DESC LIMIT ?";

        return executeCountryQuery(query, region, N);
    }

    // Query to get all cities by the population
    public List<City> getAllCitiesByPopulation() {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query);
    }

    // Query to get cities by the continent
    public List<City> getCitiesByContinent(String continent) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, continent);
    }

    // Query to get cities by region
    public List<City> getCitiesByRegion(String region) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, region);
    }

    // Query to get cities by country
    public List<City> getCitiesByCountry(String countryCode) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Code = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, countryCode);
    }

    // Query to get cities by district
    public List<City> getCitiesByDistrict(String district) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, district);
    }

    // Method to retrieve top N populated cities globally
    public List<City> getTopPopulatedCities(int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, n);
    }

    // Method to retrieve top N populated cities in a continent
    public List<City> getTopPopulatedCitiesByContinent(String continent, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, continent, n);
    }

    // Method to retrieve top N populated cities in a region
    public List<City> getTopPopulatedCitiesByRegion(String region, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, region, n);
    }

    // Method to retrieve top N populated cities in a country
    public List<City> getTopPopulatedCitiesByCountry(String countryCode, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Code = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, countryCode, n);
    }

    // Method to retrieve top N populated cities in a district
    public List<City> getTopPopulatedCitiesByDistrict(String district, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, district, n);
    }

    // Method to retrieve all capital cities in the world by population
    public List<City> getCapitalCitiesByPopulation() {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query);
    }

    // Method to retrieve capital cities in a continent by population
    public List<City> getCapitalCitiesByContinent(String continent) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, continent);
    }

    // Method to retrieve capital cities in a region by population
    public List<City> getCapitalCitiesByRegion(String region) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, region);
    }

    // Method to retrieve the top N populated capital cities in the world
    public List<City> getTopPopulatedCapitalCities(int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, n);
    }

    // Method to retrieve the top N populated capital cities in a continent
    public List<City> getTopPopulatedCapitalCitiesByContinent(String continent, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, continent, n);
    }

    // Method to retrieve the top N populated capital cities in a region
    public List<City> getTopPopulatedCapitalCitiesByRegion(String region, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, region, n);
    }

    // Method to retrieve population by continent with percentages
    public List<PopulationReport> getPopulationByContinent() {
        String query = "SELECT country.Continent AS Name, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS CityPopulation, " +
                "(1.0 * SUM(city.Population) / SUM(country.Population)) * 100 AS CityPopulationPercentage, " +
                "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, " +
                "(1.0 * (SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100 AS NonCityPopulationPercentage " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Continent";
        return executePopulationReportQuery(query);
    }

    // Method to retrieve population by region with percentages
    public List<PopulationReport> getPopulationByRegion() {
        String query = "SELECT country.Region AS Name, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS CityPopulation, " +
                "(1.0 * SUM(city.Population) / SUM(country.Population)) * 100 AS CityPopulationPercentage, " +
                "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, " +
                "(1.0 * (SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100 AS NonCityPopulationPercentage " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Region";
        return executePopulationReportQuery(query);
    }

    // Method to retrieve population by country with percentages
    public List<PopulationReport> getPopulationByCountry() {
        String query = "SELECT country.Name AS Name, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS CityPopulation, " +
                "(1.0 * SUM(city.Population) / SUM(country.Population)) * 100 AS CityPopulationPercentage, " +
                "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, " +
                "(1.0 * (SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100 AS NonCityPopulationPercentage " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Name";
        return executePopulationReportQuery(query);
    }

    // Generic method to execute population-related queries with percentage calculations
    private List<PopulationReport> executePopulationReportQuery(String query) {
        List<PopulationReport> reportDataList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rset = pstmt.executeQuery()) {

            while (rset.next()) {
                PopulationReport report = new PopulationReport();
                report.setName(rset.getString("Name"));
                report.setTotalPopulation(rset.getLong("TotalPopulation"));
                report.setCityPopulation(rset.getLong("CityPopulation"));
                report.setCityPopulationPercentage(rset.getDouble("CityPopulationPercentage"));
                report.setNonCityPopulation(rset.getLong("NonCityPopulation"));
                report.setNonCityPopulationPercentage(rset.getDouble("NonCityPopulationPercentage"));
                reportDataList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportDataList;
    }

    // Method to get the population of the world
    public long getWorldPopulation() {
        String query = "SELECT SUM(Population) AS Population FROM country";
        return executePopulationQuery(query);
    }

    // Method to get the population of a continent
    public long getContinentPopulation(String continent) {
        String query = "SELECT SUM(Population) AS Population FROM country WHERE Continent = ?";
        return executePopulationQuery(query, continent);
    }

    // Method to get the population of a region
    public long getRegionPopulation(String region) {
        String query = "SELECT SUM(Population) AS Population FROM country WHERE Region = ?";
        return executePopulationQuery(query, region);
    }

    // Method to get the population of a country
    public long getCountryPopulation(String country) {
        String query = "SELECT Population FROM country WHERE Name = ?";
        return executePopulationQuery(query, country);
    }

    // Method to get the population of a district
    public long getDistrictPopulation(String district) {
        String query = "SELECT SUM(Population) AS Population FROM city WHERE District = ?";
        return executePopulationQuery(query, district);
    }

    // Method to get the population of a city
    public long getCityPopulation(String cityName) {
        String query = "SELECT Population FROM city WHERE Name = ?";
        return executePopulationQuery(query, cityName);
    }

    // Helper method to execute population queries and return a long value
    private long executePopulationQuery(String query, Object... params) {
        long population = 0;
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    population = rset.getLong("Population");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return population;
    }

    // Method to get the number of speakers for specific languages and their percentage of the world population
    public List<LanguageReport> getLanguageStatistics() {
        String query = "SELECT language.Language, " +
                "SUM(country.Population * (language.Percentage / 100)) AS SpeakerCount, " +
                "(SUM(country.Population * (language.Percentage / 100)) / " +
                " (SELECT SUM(Population) FROM country)) * 100 AS WorldPercentage " +
                "FROM countrylanguage language " +
                "JOIN country ON language.CountryCode = country.Code " +
                "WHERE language.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY language.Language " +
                "ORDER BY SpeakerCount DESC";
        return executeLanguageReportQuery(query);
    }

    // Helper method to execute the language statistics query and return results
    private List<LanguageReport> executeLanguageReportQuery(String query) {
        List<LanguageReport> languageReports = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rset = pstmt.executeQuery()) {

            while (rset.next()) {
                LanguageReport report = new LanguageReport();
                report.setLanguage(rset.getString("Language"));
                report.setSpeakerCount(rset.getLong("SpeakerCount"));
                report.setWorldPercentage(rset.getDouble("WorldPercentage"));
                languageReports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return languageReports;
    }


    /**
     * Display a list of Country objects in a formatted table.
     *
     * @param countries The list of countries to display.
     */
    public void displayCountries(List<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            // Formatter for comma-separated numbers
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

            // Column headers with clear formatting
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-15s | %-35s | %-20s | %-25s | %-30s | %-15s |%n",
                    "Country Code", "Country", "Continent", "Region", "Capital City", "Population");
            // Separator line for clarity
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Country country : countries) {
                // Print each country’s details with formatted population
                System.out.printf("%-15s | %-35s | %-20s | %-25s | %-30s | %-15s |%n",
                        country.getCode(),
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getCapitalCity().getName(),
                        numberFormat.format(country.getPopulation()));
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

        } else {
            System.out.println("No countries found.");
        }
    }

    /**
     * Display a list of City objects in a formatted table.
     *
     * @param cities The list of cities to display.
     */
    public void displayCities(List<City> cities) {
        if (cities != null && !cities.isEmpty()) {
            // Formatter for comma-separated numbers
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

            System.out.println("------------------------------------------------------------------------------------------------------");
            // Column headers with clear formatting
            System.out.printf("%-30s | %-30s | %-20s | %-12s |%n", "City", "Country", "District", "Population");
            // Separator line for clarity
            System.out.println("------------------------------------------------------------------------------------------------------");

            for (City city : cities) {
                // Print each city’s details with formatted population
                System.out.printf("%-30s | %-30s | %-20s | %-12s |%n",
                        city.getName(),
                        city.getCountryCode(),
                        city.getDistrict(),
                        numberFormat.format(city.getPopulation()));
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No cities found.");
        }
    }

    /**
     * Display a list of PopulationReport objects in a formatted table.
     *
     * @param dataList The list of population reports to display.
     */
    public void displayPopulationData(List<PopulationReport> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-40s | %-20s | %-20s | %-10s | %-20s | %-10s | %n",
                    "Name", "Total Population", "City Population", "City %", "Non-City Population", "Non-City %");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

            for (PopulationReport report : dataList) {
                System.out.printf("%-40s | %-20s | %-20s | %-10s | %-20s | %-10s | %n",
                        report.getName(),
                        numberFormat.format(report.getTotalPopulation()),
                        numberFormat.format(report.getCityPopulation()),
                        report.getCityPopulationPercentageString(), // Display formatted city % with symbol
                        numberFormat.format(report.getNonCityPopulation()),
                        report.getNonCityPopulationPercentageString()); // Display formatted non-city % with symbol
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No data found.");
        }
    }

    /**
     * Displays a list of LanguageReport objects in a formatted table.
     *
     * @param languageReports a list of LanguageReport objects to display.
     * If the list is null or empty, a message will be printed instead.
     */
    public void displayLanguageStatistics(List<LanguageReport> languageReports) {
        // Check if the list of language reports is not null and contains elements
        if (languageReports != null && !languageReports.isEmpty()) {
            // Print the table header
            System.out.println("\nLanguage Statistics");
            System.out.println("================================================================================");
            System.out.printf("| %-15s | %-25s | %-30s |\n", "Language", "Speaker Count", "Percentage of World Population");
            System.out.println("================================================================================");

            // Create a NumberFormat instance to format the speaker count with commas
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

            // Iterate through each LanguageReport object in the list
            for (LanguageReport report : languageReports) {
                // Print each report's details in a formatted row
                System.out.printf("| %-15s | %-25s | %-30s |\n",
                        report.getLanguage(),
                        numberFormat.format(report.getSpeakerCount()),  // Format speaker count with commas for readability
                        report.getWorldPercentageString());             // Format percentage as a string with two decimals and a percent sign
            }
            // Print the table footer
            System.out.println("================================================================================");
        } else {
            // Print a message if no language statistics are available
            System.out.println("No language statistics found.");
        }
    }



}