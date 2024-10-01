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
     * Retrieve a list of countries ordered by population.
     *
     * @return A list of Country objects
     */
    public List<Country> getCountriesByPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Name, Population FROM country ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // List to hold all countries
            List<Country> countries = new ArrayList<>();

            // Loop through the result set and add countries to the list
            while (rset.next()) {
                Country country = new Country();
                country.setName(rset.getString("Name")); // Populate name
                country.setPopulation(rset.getInt("Population")); // Populate population
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
     * Display the list of countries and their populations.
     *
     * @param countries A list of Country objects to display
     */
    public void displayCountriesByPopulation(List<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            for (Country country : countries) {
                System.out.println(
                        "Country: " + country.getName() + "\n" +
                                "Population: " + country.getPopulation() + "\n"
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
