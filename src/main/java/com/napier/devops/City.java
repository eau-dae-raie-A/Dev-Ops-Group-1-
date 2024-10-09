package com.napier.devops;

/**
 * Represents a city with various attributes such as name, country code, district, and population.
 */
public class City {

    /**
     * The unique ID of the city (Primary Key, Auto-incremented).
     */
    private int id;

    /**
     * The name of the city.
     */
    private String name;

    /**
     * The code of the country to which the city belongs.
     */
    private String countryCode;

    /**
     * The district or administrative division within the country where the city is located.
     */
    private String district;

    /**
     * The population of the city.
     */
    private int population;

    /**
     * Gets the unique ID of the city.
     *
     * @return The city's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the city.
     *
     * @param id The city's ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the city.
     *
     * @return The name of the city.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the city.
     *
     * @param name The name of the city to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the country code associated with the city.
     *
     * @return The country code of the city.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the country code associated with the city.
     *
     * @param countryCode The country code of the city to set.
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Gets the district or administrative division where the city is located.
     *
     * @return The district of the city.
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the district or administrative division where the city is located.
     *
     * @param district The district of the city to set.
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Gets the population of the city.
     *
     * @return The population of the city.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Sets the population of the city.
     *
     * @param population The population of the city to set.
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Returns a string representation of the City object.
     *
     * @return A string containing the city's details.
     */
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
