package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

public class App {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load MySQL database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1); // Exit the program if the driver is not found
        }

        // Retry the connection in case the database is not immediately available
        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for the database to be ready
                Thread.sleep(30000);
                // Establish connection to the database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=True&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break; // Exit loop when the connection is successful
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database, attempt " + i);
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
                // Close the database connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Retrieve a list of countries ordered by population, including their capital city name.
     *
     * @return A list of Country objects
     */
    public List<Country> getCountriesByPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                    "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                    "city.District, city.Population AS CapitalPopulation " +
                    "FROM country JOIN city ON country.Capital = city.ID " +
                    "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // List to hold all countries
            List<Country> countries = new ArrayList<>();

            // Loop through the result set and add countries to the list
            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code")); // Populate country code
                country.setName(rset.getString("CountryName")); // Populate country name
                country.setContinent(rset.getString("Continent")); // Populate continent
                country.setRegion(rset.getString("Region")); // Populate region
                country.setPopulation(rset.getInt("Population")); // Populate population

                // Create and populate a City object for the capital city
                City capitalCity = new City();
                capitalCity.setId(rset.getInt("CityID"));
                capitalCity.setName(rset.getString("CapitalCityName"));
                capitalCity.setDistrict(rset.getString("District"));
                capitalCity.setPopulation(rset.getInt("CapitalPopulation"));

                // Set the capital city in the Country object
                country.setCapitalCity(capitalCity);

                // Add the country to the list
                countries.add(country);
            }
            return countries;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population");
            return null;
        }
    }

    /**
     * Retrieve a list of countries from a specific continent, ordered by population.
     *
     * @param continent The continent to filter by.
     * @return A list of Country objects from the specified continent.
     */
    public List<Country> getCountriesByContinent(String continent) {
        try {
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                    "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                    "city.District, city.Population AS CapitalPopulation " +
                    "FROM country JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Continent = ? " +  // Filter by continent
                    "ORDER BY country.Population DESC";

            // Use a prepared statement to avoid SQL injection
            PreparedStatement pstmt = con.prepareStatement(strSelect);
            pstmt.setString(1, continent); // Bind continent value

            // Execute SQL statement
            ResultSet rset = pstmt.executeQuery();
            List<Country> countries = new ArrayList<>();

            // Loop through the result set and add countries to the list
            while (rset.next()) {
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
                countries.add(country);
            }
            return countries;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by continent");
            return null;
        }
    }

    /**
     * Retrieve a list of countries from a specific region, ordered by population.
     *
     * @param region The region to filter by.
     * @return A list of Country objects from the specified region.
     */
    public List<Country> getCountriesByRegion(String region) {
        try {
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                    "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                    "city.District, city.Population AS CapitalPopulation " +
                    "FROM country JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Region = ? " +  // Filter by region
                    "ORDER BY country.Population DESC";

            // Use a prepared statement to avoid SQL injection
            PreparedStatement pstmt = con.prepareStatement(strSelect);
            pstmt.setString(1, region); // Bind region value

            // Execute SQL statement
            ResultSet rset = pstmt.executeQuery();
            List<Country> countries = new ArrayList<>();

            // Loop through the result set and add countries to the list
            while (rset.next()) {
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
                countries.add(country);
            }
            return countries;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by region");
            return null;
        }
    }

    /**
     * Retrieve the top N populated countries in the world.
     *
     * @param n The number of top populated countries to retrieve.
     * @return A list of the top N populated Country objects.
     */
    public List<Country> getTopPopulatedCountries(int n) {
        try {
            // SQL query to get the top N populated countries
            String strSelect = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                    "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                    "city.District, city.Population AS CapitalPopulation " +
                    "FROM country JOIN city ON country.Capital = city.ID " +
                    "ORDER BY country.Population DESC LIMIT " + n;
            // Execute SQL statement
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);
            List<Country> countries = new ArrayList<>();

            // Loop through the result set and add countries to the list
            while (rset.next()) {
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
                countries.add(country);
            }
            return countries;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated countries");
            return null;
        }
    }

    /**
     * Display the list of countries, their populations, and capital cities in a table format.
     *
     * @param countries A list of Country objects to display
     */
    public void displayCountriesByPopulation(List<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            // Print table headers
            System.out.println(String.format("%-20s %-15s %-20s %-15s %-20s %-20s", "Country", "Continent", "Region", "Population", "Capital City", "Capital Population"));
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            // Loop through each country and display the details
            for (Country country : countries) {
                System.out.println(String.format("%-20s %-15s %-20s %-15d %-20s %-20d",
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalCity().getName(),
                        country.getCapitalCity().getPopulation()
                ));
            }
        } else {
            System.out.println("No countries found.");
        }
    }

    public static void main(String[] args) {
        // Create new Application instance
        App a = new App();

        // Connect to the database
        a.connect();

        // Retrieve the list of countries ordered by population (Global)
        List<Country> countries = a.getCountriesByPopulation();
        System.out.println("All Countries by Population:");
        a.displayCountriesByPopulation(countries);

        // Retrieve the list of countries in a specific continent (e.g., Asia)
        List<Country> asianCountries = a.getCountriesByContinent("Asia");
        System.out.println("\nList of Countries in Asia by Population:");
        a.displayCountriesByPopulation(asianCountries);

        // Retrieve the list of countries in a specific region (e.g., Middle East)
        List<Country> middleEastCountries = a.getCountriesByRegion("Middle East");
        System.out.println("\nList of Countries in the Middle East by Population:");
        a.displayCountriesByPopulation(middleEastCountries);

        // Retrieve the top N populated countries in the world (e.g., N = 8)
        int topN = 8;
        List<Country> topPopulatedCountries = a.getTopPopulatedCountries(topN);
        System.out.println("\nTop " + topN + " Populated Countries in the World:");
        a.displayCountriesByPopulation(topPopulatedCountries);

        // Disconnect from the database
        a.disconnect();
    }
}
