package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class DatabaseService {

    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
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

    /**
     * Disconnect from the MySQL database.
     */
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

    // Method to retrieve the population of people, people living in cities, and people not living in cities in each continent
    public List<PopulationData> getPopulationByContinent() {
        String query = "SELECT country.Continent, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS CityPopulation, " +
                "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation " +
                "FROM country LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Continent";
        return executePopulationQuery(query, "Continent");
    }

    // Method to retrieve the population of people, people living in cities, and people not living in cities in each region
    public List<PopulationData> getPopulationByRegion() {
        String query = "SELECT country.Region, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS CityPopulation, " +
                "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation " +
                "FROM country LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Region";
        return executePopulationQuery(query, "Region");
    }

    // Method to retrieve the population of people, people living in cities, and people not living in cities in each country
    public List<PopulationData> getPopulationByCountry() {
        String query = "SELECT country.Name AS Country, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS CityPopulation, " +
                "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation " +
                "FROM country LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Name";
        return executePopulationQuery(query, "Country");
    }

    // Generic method to execute population-related queries
    private List<PopulationData> executePopulationQuery(String query, String fieldName) {
        List<PopulationData> populationDataList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rset = pstmt.executeQuery()) {

            while (rset.next()) {
                PopulationData data = new PopulationData();
                data.setName(rset.getString(fieldName));
                data.setTotalPopulation(rset.getLong("TotalPopulation"));
                data.setCityPopulation(rset.getLong("CityPopulation"));
                data.setNonCityPopulation(rset.getLong("NonCityPopulation"));
                populationDataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return populationDataList;
    }


    // Display country results in a tabular format with clearer borders
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

    // Display city results in a tabular format with clearer borders
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

    public void displayPopulationData(List<PopulationData> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.printf("%-20s | %-20s | %-20s | %-20s |%n", "Continent", "Total Population", "City Population", "Non-City Population");
            System.out.println("----------------------------------------------------------------------------------------");

            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            for (PopulationData data : dataList) {
                System.out.printf("%-20s | %-20s | %-20s | %-20s |%n",
                        data.getName(),
                        numberFormat.format(data.getTotalPopulation()),
                        numberFormat.format(data.getCityPopulation()),
                        numberFormat.format(data.getNonCityPopulation()));
            }
            System.out.println("----------------------------------------------------------------------------------------");
        } else {
            System.out.println("No data found.");
        }
    }

}
