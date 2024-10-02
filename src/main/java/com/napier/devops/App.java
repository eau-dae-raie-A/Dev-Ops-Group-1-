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

    /**
     * Retrieves the top N populated countries in a specific continent.
     *
     * @param continent The name of the continent to filter the countries.
     * @param N The number of top populated countries to return.
     * @return A list of Country objects representing the top N populated countries in the specified continent.
     */
    public List<Country> getTopPopulatedCountriesByContinent(String continent, int N) {
        try {
            // SQL query to select countries by continent, ordered by population, and limit the results to N.
            String query = "SELECT country.Code, country.Name, country.Continent, country.Region, " +
                    "country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                    "city.District, city.Population AS CapitalPopulation " +
                    "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Continent = ? " +
                    "ORDER BY country.Population DESC LIMIT ?";

            // Prepared statement to avoid SQL injection, setting the continent and limit (N).
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, continent);  // Bind the continent parameter.
            pstmt.setInt(2, N);  // Bind the N parameter.

            // Execute the query and get the result set.
            ResultSet rset = pstmt.executeQuery();
            List<Country> countries = new ArrayList<>();

            // Loop through the result set and map each row to a Country object.
            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("Name"));
                country.setContinent(rset.getString("Continent"));
                country.setRegion(rset.getString("Region"));
                country.setPopulation(rset.getInt("Population"));

                // Create and populate the City object for the capital city.
                City capitalCity = new City();
                capitalCity.setId(rset.getInt("CityID"));
                capitalCity.setName(rset.getString("CapitalCityName"));
                capitalCity.setDistrict(rset.getString("District"));
                capitalCity.setPopulation(rset.getInt("CapitalPopulation"));

                // Set the capital city for the country.
                country.setCapitalCity(capitalCity);

                // Add the country to the list of countries.
                countries.add(country);
            }
            return countries;  // Return the list of top N populated countries.
        } catch (SQLException e) {
            System.out.println("Error fetching top populated countries by continent: " + e.getMessage());
            return null;
        }
    }

    public void displayCountriesByContinent(List<Country> countries, String continent) {
        if (countries != null && !countries.isEmpty()) {
            // Print table headers
            System.out.println("\nTop Countries by Population in Continent: " + continent);
            System.out.println(String.format("%-20s %-15s %-20s %-15s %-20s %-20s", "Country", "Continent", "Region", "Population", "Capital City", "Capital Population"));
            System.out.println("---------------------------------------------------------------------------------------------------------------------");

            // Loop through each country and display its details
            for (Country country : countries) {
                System.out.println(String.format("%-20s %-15s %-20s %-15d %-20s %-20d",
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalCity() != null ? country.getCapitalCity().getName() : "N/A",
                        country.getCapitalCity() != null ? country.getCapitalCity().getPopulation() : 0
                ));
            }
        } else {
            System.out.println("No countries found for the continent: " + continent);
        }
    }
    /**
     * Retrieves the top N populated countries in a specific region.
     *
     * @param region The name of the region to filter the countries.
     * @param N The number of top populated countries to return.
     * @return A list of Country objects representing the top N populated countries in the specified region.
     */
    public List<Country> getTopPopulatedCountriesByRegion(String region, int N) {
        try {
            // SQL query to select countries by region, ordered by population, and limit the results to N.
            String query = "SELECT country.Code, country.Name, country.Continent, country.Region, " +
                    "country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                    "city.District, city.Population AS CapitalPopulation " +
                    "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                    "WHERE country.Region = ? " +
                    "ORDER BY country.Population DESC LIMIT ?";

            // Prepared statement to avoid SQL injection, setting the region and limit (N).
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, region);  // Bind the region parameter.
            pstmt.setInt(2, N);  // Bind the N parameter.

            // Execute the query and get the result set.
            ResultSet rset = pstmt.executeQuery();
            List<Country> countries = new ArrayList<>();

            // Loop through the result set and map each row to a Country object.
            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("Name"));
                country.setContinent(rset.getString("Continent"));
                country.setRegion(rset.getString("Region"));
                country.setPopulation(rset.getInt("Population"));

                // Create and populate the City object for the capital city.
                City capitalCity = new City();
                capitalCity.setId(rset.getInt("CityID"));
                capitalCity.setName(rset.getString("CapitalCityName"));
                capitalCity.setDistrict(rset.getString("District"));
                capitalCity.setPopulation(rset.getInt("CapitalPopulation"));

                // Set the capital city for the country.
                country.setCapitalCity(capitalCity);

                // Add the country to the list of countries.
                countries.add(country);
            }
            return countries;  // Return the list of top N populated countries.
        } catch (SQLException e) {
            System.out.println("Error fetching top populated countries by region: " + e.getMessage());
            return null;
        }
    }
    public void displayCountriesByRegion(List<Country> countries, String region) {
        if (countries != null && !countries.isEmpty()) {
            // Print table headers
            System.out.println("\nTop Countries by Population in Region: " + region);
            System.out.println(String.format("%-20s %-15s %-20s %-15s %-20s %-20s", "Country", "Continent", "Region", "Population", "Capital City", "Capital Population"));
            System.out.println("---------------------------------------------------------------------------------------------------------------------");

            // Loop through each country and display its details
            for (Country country : countries) {
                System.out.println(String.format("%-20s %-15s %-20s %-15d %-20s %-20d",
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalCity() != null ? country.getCapitalCity().getName() : "N/A",
                        country.getCapitalCity() != null ? country.getCapitalCity().getPopulation() : 0
                ));
            }
        } else {
            System.out.println("No countries found for the region: " + region);
        }
    }

    /**
     * Retrieves all cities in the world ordered by population from largest to smallest.
     *
     * @return A list of City objects representing all cities, ordered by population.
     */
    public List<City> getAllCitiesByPopulation() {
        try {
            // SQL query to select all cities, ordered by population.
            String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                    "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "ORDER BY city.Population DESC";

            // Create a statement to execute the query.
            Statement stmt = con.createStatement();
            // Execute the query and get the result set.
            ResultSet rset = stmt.executeQuery(query);
            List<City> cities = new ArrayList<>();

            // Loop through the result set and map each row to a City object.
            while (rset.next()) {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("Name"));
                city.setCountryCode(rset.getString("CountryName"));  // The country name is mapped here.
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("Population"));

                // Add the city to the list of cities.
                cities.add(city);
            }
            return cities;  // Return the list of cities ordered by population.
        } catch (SQLException e) {
            System.out.println("Error fetching cities by population: " + e.getMessage());
            return null;
        }
    }
    public void displayAllCitiesByPopulation(List<City> cities) {
        if (cities != null && !cities.isEmpty()) {
            // Print table headers
            System.out.println("\nAll Cities by Population:");
            System.out.println(String.format("%-20s %-20s %-20s %-15s", "City", "Country", "District", "Population"));
            System.out.println("-------------------------------------------------------------------------------");

            // Loop through each city and display its details
            for (City city : cities) {
                System.out.println(String.format("%-20s %-20s %-20s %-15d",
                        city.getName(),
                        city.getCountryCode(),
                        city.getDistrict(),
                        city.getPopulation()
                ));
            }
        } else {
            System.out.println("No cities found.");
        }
    }

    /**
     * Retrieves all cities in a specific continent ordered by population from largest to smallest.
     *
     * @param continent The name of the continent to filter the cities.
     * @return A list of City objects representing all cities in the specified continent, ordered by population.
     */
    public List<City> getCitiesByContinent(String continent) {
        try {
            // SQL query to select all cities in a continent, ordered by population.
            String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                    "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Continent = ? " +
                    "ORDER BY city.Population DESC";

            // Prepared statement to avoid SQL injection, setting the continent.
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, continent);  // Bind the continent parameter.

            // Execute the query and get the result set.
            ResultSet rset = pstmt.executeQuery();
            List<City> cities = new ArrayList<>();

            // Loop through the result set and map each row to a City object.
            while (rset.next()) {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("Name"));
                city.setCountryCode(rset.getString("CountryName"));  // The country name is mapped here.
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("Population"));

                // Add the city to the list of cities.
                cities.add(city);
            }
            return cities;  // Return the list of cities in the specified continent.
        } catch (SQLException e) {
            System.out.println("Error fetching cities by continent: " + e.getMessage());
            return null;
        }
    }
    public void displayCitiesByContinent(List<City> cities, String continent) {
        if (cities != null && !cities.isEmpty()) {
            // Print table headers
            System.out.println("\nCities in Continent: " + continent);
            System.out.println(String.format("%-20s %-20s %-20s %-15s", "City", "Country", "District", "Population"));
            System.out.println("-------------------------------------------------------------------------------");

            // Loop through each city and display its details
            for (City city : cities) {
                System.out.println(String.format("%-20s %-20s %-20s %-15d",
                        city.getName(),
                        city.getCountryCode(),
                        city.getDistrict(),
                        city.getPopulation()
                ));
            }
        } else {
            System.out.println("No cities found for the continent: " + continent);
        }
    }

    /**
     * Retrieves all cities in a specific region ordered by population from largest to smallest.
     *
     * @param region The name of the region to filter the cities.
     * @return A list of City objects representing all cities in the specified region, ordered by population.
     */
    public List<City> getCitiesByRegion(String region) {
        try {
            // SQL query to select all cities in a region, ordered by population.
            String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                    "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Region = ? " +
                    "ORDER BY city.Population DESC";

            // Prepared statement to avoid SQL injection, setting the region.
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, region);  // Bind the region parameter.

            // Execute the query and get the result set.
            ResultSet rset = pstmt.executeQuery();
            List<City> cities = new ArrayList<>();

            // Loop through the result set and map each row to a City object.
            while (rset.next()) {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("Name"));
                city.setCountryCode(rset.getString("CountryName"));  // The country name is mapped here.
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("Population"));

                // Add the city to the list of cities.
                cities.add(city);
            }
            return cities;  // Return the list of cities in the specified region.
        } catch (SQLException e) {
            System.out.println("Error fetching cities by region: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieve all cities in a specific country ordered by population from largest to smallest.
     *
     * @param countryCode The code of the country to filter the cities.
     * @return A list of City objects representing all cities in the specified country, ordered by population.
     */
    public List<City> getCitiesByCountry(String countryCode) {
        try {
            // SQL query to select all cities in a country, ordered by population.
            String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                    "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Code = ? " +  // Filter by country code
                    "ORDER BY city.Population DESC";

            // Prepared statement to avoid SQL injection, setting the country code.
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, countryCode);  // Bind the country code parameter.

            // Execute the query and get the result set.
            ResultSet rset = pstmt.executeQuery();
            List<City> cities = new ArrayList<>();

            // Loop through the result set and map each row to a City object.
            while (rset.next()) {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("Name"));
                city.setCountryCode(rset.getString("CountryName"));  // The country name is mapped here.
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("Population"));

                // Add the city to the list of cities.
                cities.add(city);
            }
            return cities;  // Return the list of cities in the specified country.
        } catch (SQLException e) {
            System.out.println("Error fetching cities by country: " + e.getMessage());
            return null;
        }
    }

    /**
     * Display all cities in a specific country ordered by population.
     *
     * @param cities A list of City objects to display.
     * @param countryName The name of the country for display purposes.
     */
    public void displayCitiesByCountry(List<City> cities, String countryName) {
        if (cities != null && !cities.isEmpty()) {
            // Print table headers
            System.out.println("\nCities in Country: " + countryName);
            System.out.println(String.format("%-20s %-20s %-20s %-15s", "City", "Country", "District", "Population"));
            System.out.println("-------------------------------------------------------------------------------");

            // Loop through each city and display its details
            for (City city : cities) {
                System.out.println(String.format("%-20s %-20s %-20s %-15d",
                        city.getName(),
                        city.getCountryCode(),
                        city.getDistrict(),
                        city.getPopulation()
                ));
            }
        } else {
            System.out.println("No cities found for the country: " + countryName);
        }
    }

    public void displayCitiesByRegion(List<City> cities, String region) {
        if (cities != null && !cities.isEmpty()) {
            // Print table headers
            System.out.println("\nCities in Region: " + region);
            System.out.println(String.format("%-20s %-20s %-20s %-15s", "City", "Country", "District", "Population"));
            System.out.println("-------------------------------------------------------------------------------");

            // Loop through each city and display its details
            for (City city : cities) {
                System.out.println(String.format("%-20s %-20s %-20s %-15d",
                        city.getName(),
                        city.getCountryCode(),
                        city.getDistrict(),
                        city.getPopulation()
                ));
            }
        } else {
            System.out.println("No cities found for the region: " + region);
        }
    }

    /**
     * Retrieve all cities in a specific district ordered by population from largest to smallest.
     *
     * @param district The name of the district to filter the cities.
     * @return A list of City objects representing all cities in the specified district, ordered by population.
     */
    public List<City> getCitiesByDistrict(String district) {
        try {
            // SQL query to select all cities in a district, ordered by population.
            String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                    "FROM city JOIN country ON city.CountryCode = country.Code " +
                    "WHERE city.District = ? " +  // Filter by district
                    "ORDER BY city.Population DESC";

            // Prepared statement to avoid SQL injection, setting the district name.
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, district);  // Bind the district parameter.

            // Execute the query and get the result set.
            ResultSet rset = pstmt.executeQuery();
            List<City> cities = new ArrayList<>();

            // Loop through the result set and map each row to a City object.
            while (rset.next()) {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("Name"));
                city.setCountryCode(rset.getString("CountryName"));  // The country name is mapped here.
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("Population"));

                // Add the city to the list of cities.
                cities.add(city);
            }
            return cities;  // Return the list of cities in the specified district.
        } catch (SQLException e) {
            System.out.println("Error fetching cities by district: " + e.getMessage());
            return null;
        }
    }

    /**
            * Display all cities in a specific district ordered by population.
            *
            * @param cities A list of City objects to display.
            * @param district The name of the district for display purposes.
            */
    public void displayCitiesByDistrict(List<City> cities, String district) {
        if (cities != null && !cities.isEmpty()) {
            // Print table headers
            System.out.println("\nCities in District: " + district);
            System.out.println(String.format("%-20s %-20s %-20s %-15s", "City", "Country", "District", "Population"));
            System.out.println("-------------------------------------------------------------------------------");

            // Loop through each city and display its details
            for (City city : cities) {
                System.out.println(String.format("%-20s %-20s %-20s %-15d",
                        city.getName(),
                        city.getCountryCode(),
                        city.getDistrict(),
                        city.getPopulation()
                ));
            }
        } else {
            System.out.println("No cities found for the district: " + district);
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

        // Retrieve and display top populated countries by continent
        String continent = "Asia";
        List<Country> topCountriesInContinent = a.getTopPopulatedCountriesByContinent(continent, 8);
        a.displayCountriesByContinent(topCountriesInContinent, continent);

        // Retrieve and display top populated countries by region
        String region = "Western Europe";
        List<Country> topCountriesInRegion = a.getTopPopulatedCountriesByRegion(region, 8);
        a.displayCountriesByRegion(topCountriesInRegion, region);

        // Retrieve and display all cities in the world by population
        List<City> allCitiesByPopulation = a.getAllCitiesByPopulation();
        a.displayAllCitiesByPopulation(allCitiesByPopulation);

        // Retrieve and display cities in a specific continent by population
        List<City> citiesInContinent = a.getCitiesByContinent(continent);
        a.displayCitiesByContinent(citiesInContinent, continent);

        // Retrieve and display cities in a specific region by population
        List<City> citiesInRegion = a.getCitiesByRegion(region);
        a.displayCitiesByRegion(citiesInRegion, region);

        // Example: Retrieve and display cities in a specific country (e.g., "USA")
        String countryCode = "USA";  // Use the appropriate country code for the query
        List<City> citiesInCountry = a.getCitiesByCountry(countryCode);
        a.displayCitiesByCountry(citiesInCountry, "United States");

        // Example: Retrieve and display cities in a specific district (e.g., "New York")
        String district = "New York";  // Use the appropriate district name for the query
        List<City> citiesInDistrict = a.getCitiesByDistrict(district);
        a.displayCitiesByDistrict(citiesInDistrict, district);

        // Disconnect from the database
        a.disconnect();
    }
}
