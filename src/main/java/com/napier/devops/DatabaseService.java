package com.napier.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Provides database services for interacting with country, city, and population data.
 */
public class DatabaseService {

    // Database connection
    private Connection con = null;

    /**
     * Connects to the MySQL database with the specified location and delay.
     *
     * @param location The database location URL.
     * @param delay    The delay time to wait before connecting.
     */
    public void connect(String location, int delay) {
        try {
            // Load MySQL database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(delay);
                // Attempt to connect to the database
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
     * Disconnects from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Getter for the database connection, used primarily for testing purposes.
     *
     * @return The current database connection.
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * Executes a country-related SQL query with optional parameters.
     *
     * @param query  The SQL query to execute.
     * @param params Parameters for the SQL query.
     * @return A list of Country objects matching the query.
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
     * Executes a city-related SQL query with optional parameters.
     *
     * @param query  The SQL query to execute.
     * @param params Parameters for the SQL query.
     * @return A list of City objects matching the query.
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
     * Maps a ResultSet row to a Country object.
     *
     * @param rset The ResultSet containing the row data.
     * @return A Country object populated with the row data.
     * @throws SQLException If there is an error accessing the ResultSet.
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
     * Maps a ResultSet row to a City object.
     *
     * @param rset The ResultSet containing the row data.
     * @return A City object populated with the row data.
     * @throws SQLException If there is an error accessing the ResultSet.
     */
    private City mapCityResultSet(ResultSet rset) throws SQLException {
        City city = new City();
        city.setId(rset.getInt("ID"));
        city.setName(rset.getString("Name"));
        city.setCountryCode(rset.getString("CountryName"));
        city.setDistrict(rset.getString("District"));
        city.setPopulation(rset.getInt("Population"));
        return city;
    }

    /**
     * Retrieves a list of all countries ordered by population, descending.
     *
     * @return A list of Country objects.
     */
    public List<Country> getCountriesByPopulation() {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC";
        return executeCountryQuery(query);
    }

    /**
     * Retrieves countries in a specified continent ordered by population.
     *
     * @param continent The name of the continent.
     * @return A list of Country objects in the specified continent.
     */
    public List<Country> getCountriesByContinent(String continent) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = ? " +
                "ORDER BY country.Population DESC";
        return executeCountryQuery(query, continent);
    }

    /**
     * Retrieves countries in a specified region ordered by population.
     *
     * @param region The name of the region.
     * @return A list of Country objects in the specified region.
     */
    public List<Country> getCountriesByRegion(String region) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = ? " +
                "ORDER BY country.Population DESC";
        return executeCountryQuery(query, region);
    }

    /**
     * Retrieves the top N populated countries in the world.
     *
     * @param n The number of countries to retrieve.
     * @return A list of the top N populated Country objects.
     */
    public List<Country> getTopPopulatedCountries(int n) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, " +
                "country.Region, country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country JOIN city ON country.Capital = city.ID " +
                "ORDER BY country.Population DESC LIMIT ?";
        return executeCountryQuery(query, n);
    }

    /**
     * Retrieves the top N populated countries in a specific continent.
     *
     * @param continent The name of the continent.
     * @param N         The number of countries to retrieve.
     * @return A list of the top N populated Country objects in the specified continent.
     */
    public List<Country> getTopPopulatedCountriesByContinent(String continent, int N) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, country.Region, " +
                "country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                "WHERE country.Continent = ? " +
                "ORDER BY country.Population DESC LIMIT ?";
        return executeCountryQuery(query, continent, N);
    }

    /**
     * Retrieves the top N populated countries in a specific region.
     *
     * @param region The name of the region.
     * @param N      The number of countries to retrieve.
     * @return A list of the top N populated Country objects in the specified region.
     */
    public List<Country> getTopPopulatedCountriesByRegion(String region, int N) {
        String query = "SELECT country.Code, country.Name AS CountryName, country.Continent, country.Region, " +
                "country.Population, city.ID AS CityID, city.Name AS CapitalCityName, " +
                "city.District, city.Population AS CapitalPopulation " +
                "FROM country LEFT JOIN city ON country.Capital = city.ID " +
                "WHERE country.Region = ? " +
                "ORDER BY country.Population DESC LIMIT ?";

        return executeCountryQuery(query, region, N);
    }

    /**
     * Retrieves a list of all cities ordered by population, descending.
     *
     * @return A list of City objects.
     */
    public List<City> getAllCitiesByPopulation() {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query);
    }

    /**
     * Retrieves cities in a specified continent ordered by population.
     *
     * @param continent The name of the continent.
     * @return A list of City objects in the specified continent.
     */
    public List<City> getCitiesByContinent(String continent) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, continent);
    }

    /**
     * Retrieves cities in a specified region ordered by population.
     *
     * @param region The name of the region.
     * @return A list of City objects in the specified region.
     */
    public List<City> getCitiesByRegion(String region) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, region);
    }

    /**
     * Retrieves cities in a specified country ordered by population.
     *
     * @param countryCode The code of the country.
     * @return A list of City objects in the specified country.
     */
    public List<City> getCitiesByCountry(String countryCode) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Code = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, countryCode);
    }

    /**
     * Retrieves cities in a specified district ordered by population.
     *
     * @param district The name of the district.
     * @return A list of City objects in the specified district.
     */
    public List<City> getCitiesByDistrict(String district) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, district);
    }

    /**
     * Retrieves the top N populated cities globally.
     *
     * @param n The number of cities to retrieve.
     * @return A list of the top N populated City objects.
     */
    public List<City> getTopPopulatedCities(int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, n);
    }

    /**
     * Retrieves the top N populated cities in a specified continent.
     *
     * @param continent The name of the continent.
     * @param n         The number of cities to retrieve.
     * @return A list of the top N populated City objects in the specified continent.
     */
    public List<City> getTopPopulatedCitiesByContinent(String continent, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, continent, n);
    }

    /**
     * Retrieves the top N populated cities in a specified region.
     *
     * @param region The name of the region.
     * @param n      The number of cities to retrieve.
     * @return A list of the top N populated City objects in the specified region.
     */
    public List<City> getTopPopulatedCitiesByRegion(String region, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, region, n);
    }

    /**
     * Retrieves the top N populated cities in a specified country.
     *
     * @param countryCode The code of the country.
     * @param n           The number of cities to retrieve.
     * @return A list of the top N populated City objects in the specified country.
     */
    public List<City> getTopPopulatedCitiesByCountry(String countryCode, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Code = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, countryCode, n);
    }

    /**
     * Retrieves the top N populated cities in a specified district.
     *
     * @param district The name of the district.
     * @param n        The number of cities to retrieve.
     * @return A list of the top N populated City objects in the specified district.
     */
    public List<City> getTopPopulatedCitiesByDistrict(String district, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.CountryCode = country.Code " +
                "WHERE city.District = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, district, n);
    }

    /**
     * Retrieves all capital cities globally ordered by population, descending.
     *
     * @return A list of capital City objects.
     */
    public List<City> getCapitalCitiesByPopulation() {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query);
    }

    /**
     * Retrieves capital cities in a specified continent ordered by population.
     *
     * @param continent The name of the continent.
     * @return A list of capital City objects in the specified continent.
     */
    public List<City> getCapitalCitiesByContinent(String continent) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, continent);
    }

    /**
     * Retrieves capital cities in a specified region ordered by population.
     *
     * @param region The name of the region.
     * @return A list of capital City objects in the specified region.
     */
    public List<City> getCapitalCitiesByRegion(String region) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        return executeCityQuery(query, region);
    }

    /**
     * Retrieves the top N populated capital cities globally.
     *
     * @param n The number of capital cities to retrieve.
     * @return A list of the top N populated capital City objects.
     */
    public List<City> getTopPopulatedCapitalCities(int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, n);
    }

    /**
     * Retrieves the top N populated capital cities in a specified continent.
     *
     * @param continent The name of the continent.
     * @param n         The number of capital cities to retrieve.
     * @return A list of the top N populated capital City objects in the specified continent.
     */
    public List<City> getTopPopulatedCapitalCitiesByContinent(String continent, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, continent, n);
    }

    /**
     * Retrieves the top N populated capital cities in a specified region.
     *
     * @param region The name of the region.
     * @param n      The number of capital cities to retrieve.
     * @return A list of the top N populated capital City objects in the specified region.
     */
    public List<City> getTopPopulatedCapitalCitiesByRegion(String region, int n) {
        String query = "SELECT city.ID, city.Name, country.Name AS CountryName, city.District, city.Population " +
                "FROM city JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT ?";
        return executeCityQuery(query, region, n);
    }

    /**
     * Retrieves population data for each continent including city and non-city populations and their percentages.
     *
     * @return A list of PopulationReport objects for each continent.
     */
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

    /**
     * Retrieves population data for each region including city and non-city populations and their percentages.
     *
     * @return A list of PopulationReport objects for each region.
     */
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

    /**
     * Retrieves population data for each country including city and non-city populations and their percentages.
     *
     * @return A list of PopulationReport objects for each country.
     */
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

    /**
     * Executes a population report query and returns a list of PopulationReport objects.
     *
     * @param query The SQL query to execute.
     * @return A list of PopulationReport objects containing population data.
     */
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

    /**
     * Displays a formatted table of country data with clearer borders.
     *
     * @param countries The list of Country objects to display.
     */
    public void displayCountries(List<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-15s | %-35s | %-20s | %-25s | %-30s | %-15s |%n",
                    "Country Code", "Country", "Continent", "Region", "Capital City", "Population");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Country country : countries) {
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
     * Displays a formatted table of city data with clearer borders.
     *
     * @param cities The list of City objects to display.
     */
    public void displayCities(List<City> cities) {
        if (cities != null && !cities.isEmpty()) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.printf("%-30s | %-30s | %-20s | %-12s |%n", "City", "Country", "District", "Population");
            System.out.println("------------------------------------------------------------------------------------------------------");

            for (City city : cities) {
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
     * Displays a formatted table of population data with percentages for city and non-city populations.
     *
     * @param dataList The list of PopulationReport objects to display.
     */
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
