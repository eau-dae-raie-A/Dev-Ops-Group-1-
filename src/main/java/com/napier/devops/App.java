package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    private Connection con = null;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            try {
                System.out.println("Connecting to database...");
                Thread.sleep(30000);
                con = DriverManager.getConnection(
                        "jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=True&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database, attempt " + i);
                sqle.printStackTrace();
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted. Exiting.");
            }
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * General method to execute any country-related query.
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
     * General method to execute any city-related query.
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

    // Query to get countries by region
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

    // Query to get all cities by population
    public List<City> getAllCitiesByPopulation() {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query);
    }

    // Query to get cities by continent
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

    // Display country results in a tabular format
    public void displayCountries(List<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            System.out.printf("%-20s %-15s %-20s %-15s %-20s %-20s%n", "Country", "Continent", "Region", "Population", "Capital City", "Capital Population");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            for (Country country : countries) {
                System.out.printf("%-20s %-15s %-20s %-15d %-20s %-20d%n",
                        country.getName(),
                        country.getContinent(),
                        country.getRegion(),
                        country.getPopulation(),
                        country.getCapitalCity().getName(),
                        country.getCapitalCity().getPopulation());
            }
        } else {
            System.out.println("No countries found.");
        }
    }

    // Display city results in a tabular format
    public void displayCities(List<City> cities) {
        if (cities != null && !cities.isEmpty()) {
            System.out.printf("%-20s %-20s %-20s %-15s%n", "City", "Country", "District", "Population");
            System.out.println("-------------------------------------------------------------------------------");

            for (City city : cities) {
                System.out.printf("%-20s %-20s %-20s %-15d%n",
                        city.getName(),
                        city.getCountryCode(),
                        city.getDistrict(),
                        city.getPopulation());
            }
        } else {
            System.out.println("No cities found.");
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.connect();

        // Retrieve and display all countries by population (Global)
        System.out.println("All Countries by Population:");
        List<Country> countries = app.getCountriesByPopulation();
        app.displayCountries(countries);

        // Retrieve and display countries by continent (e.g., Asia)
        String continent = "Asia";
        System.out.println("\nCountries in Asia by Population:");
        List<Country> asianCountries = app.getCountriesByContinent(continent);
        app.displayCountries(asianCountries);

        // Retrieve and display countries by region (e.g., Middle East)
        String region = "Middle East";
        System.out.println("\nCountries in the Middle East by Population:");
        List<Country> middleEastCountries = app.getCountriesByRegion(region);
        app.displayCountries(middleEastCountries);

        // Retrieve and display top N populated countries in the world
        int topN = 8;
        System.out.println("\nTop " + topN + " Populated Countries in the World:");
        List<Country> topPopulatedCountries = app.getTopPopulatedCountries(topN);
        app.displayCountries(topPopulatedCountries);

        // Retrieve and display all cities by population (Global)
        System.out.println("\nAll Cities by Population:");
        List<City> allCities = app.getAllCitiesByPopulation();
        app.displayCities(allCities);

        // Retrieve and display cities by continent (e.g., Asia)
        System.out.println("\nCities in Asia by Population:");
        List<City> citiesInAsia = app.getCitiesByContinent(continent);
        app.displayCities(citiesInAsia);

        // Retrieve and display cities by region (e.g., Western Europe)
        region = "Western Europe";
        System.out.println("\nCities in Western Europe by Population:");
        List<City> citiesInRegion = app.getCitiesByRegion(region);
        app.displayCities(citiesInRegion);

        app.disconnect();
    }
}
