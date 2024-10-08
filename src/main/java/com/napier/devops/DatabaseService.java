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

    // Method to get the world population
    public long getWorldPopulation() {
        String query = "SELECT SUM(Population) AS WorldPopulation FROM country";
        return executeSinglePopulationQuery(query);
    }

    // Method to get population by continent
    public long getContinentPopulation(String continent) {
        String query = "SELECT SUM(Population) AS ContinentPopulation FROM country WHERE Continent = ?";
        return executeSinglePopulationQuery(query, continent);
    }

    // Method to get population by region
    public long getRegionPopulation(String region) {
        String query = "SELECT SUM(Population) AS RegionPopulation FROM country WHERE Region = ?";
        return executeSinglePopulationQuery(query, region);
    }

    // Method to get population by country
    public long getCountryPopulation(String countryCode) {
        String query = "SELECT Population AS CountryPopulation FROM country WHERE Code = ?";
        return executeSinglePopulationQuery(query, countryCode);
    }

    // Method to get population by district
    public long getDistrictPopulation(String district) {
        String query = "SELECT SUM(Population) AS DistrictPopulation FROM city WHERE District = ?";
        return executeSinglePopulationQuery(query, district);
    }

    // Method to get population by city
    public long getCityPopulation(String cityName) {
        String query = "SELECT Population AS CityPopulation FROM city WHERE Name = ?";
        return executeSinglePopulationQuery(query, cityName);
    }

    // Helper method to execute a single population query and return a long
    private long executeSinglePopulationQuery(String query, Object... params) {
        long population = 0;
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, (String) params[i]);
            }
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    population = rset.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return population;
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

    public void displayPopulationData(List<PopulationReport> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-36s | %-20s | %-20s | %-10s | %-20s | %-10s%n",
                    "Name", "Total Population", "City Population", "City %", "Non-City Population", "Non-City %");
            System.out.println("---------------------------------------------------------------------------------------------------------------------");

            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            for (PopulationReport report : dataList) {
                System.out.printf("%-36s | %-20s | %-20s | %-6.2f%% | %-20s | %-6.2f%%%n",
                        report.getName(),
                        numberFormat.format(report.getTotalPopulation()),
                        numberFormat.format(report.getCityPopulation()),
                        report.getCityPopulationPercentage(),
                        numberFormat.format(report.getNonCityPopulation()),
                        report.getNonCityPopulationPercentage());
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No data found.");
        }
    }



}
