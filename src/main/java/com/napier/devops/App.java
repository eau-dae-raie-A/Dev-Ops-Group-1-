package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=True&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
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
                // Close connection
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
     * Display the list of countries, their populations, and capital cities.
     *
     * @param countries A list of Country objects to display
     */
    public void displayCountriesByPopulation(List<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            for (Country country : countries) {
                System.out.println(
                        "Country: " + country.getName() + "\n" +
                                "Continent: " + country.getContinent() + "\n" +
                                "Region: " + country.getRegion() + "\n" +
                                "Population: " + country.getPopulation() + "\n" +
                                "Capital City: " + country.getCapitalCity().getName() + "\n" +
                                "Capital Population: " + country.getCapitalCity().getPopulation() + "\n"
                );
            }
        } else {
            System.out.println("No countries found.");
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get the list of countries ordered by population
        List<Country> countries = a.getCountriesByPopulation();

        // Display the countries
        a.displayCountriesByPopulation(countries);

        // Disconnect from database
        a.disconnect();
    }
}
