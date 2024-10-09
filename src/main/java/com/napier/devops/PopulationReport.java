package com.napier.devops;

/**
 * Represents a report on the population distribution in a specific geographical area,
 * including the total population, city population, non-city population, and their percentages.
 */
public class PopulationReport {

    /**
     * The name of the geographical area (e.g., continent, region, country).
     */
    private String name;

    /**
     * The total population of the area.
     */
    private long totalPopulation;

    /**
     * The population living in cities within the area.
     */
    private long cityPopulation;

    /**
     * The percentage of the total population living in cities.
     */
    private double cityPopulationPercentage;

    /**
     * The population not living in cities (rural population) within the area.
     */
    private long nonCityPopulation;

    /**
     * The percentage of the total population not living in cities.
     */
    private double nonCityPopulationPercentage;

    // Getters and Setters for all attributes

    /**
     * Gets the name of the geographical area.
     *
     * @return The name of the area.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the geographical area.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the total population of the area.
     *
     * @return The total population.
     */
    public long getTotalPopulation() {
        return totalPopulation;
    }

    /**
     * Sets the total population of the area.
     *
     * @param totalPopulation The total population to set.
     */
    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    /**
     * Gets the population living in cities within the area.
     *
     * @return The city population.
     */
    public long getCityPopulation() {
        return cityPopulation;
    }

    /**
     * Sets the population living in cities within the area.
     *
     * @param cityPopulation The city population to set.
     */
    public void setCityPopulation(long cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    /**
     * Gets the percentage of the total population living in cities.
     *
     * @return The percentage of the city population.
     */
    public double getCityPopulationPercentage() {
        return cityPopulationPercentage;
    }

    /**
     * Sets the percentage of the total population living in cities.
     *
     * @param cityPopulationPercentage The percentage of the city population to set.
     */
    public void setCityPopulationPercentage(double cityPopulationPercentage) {
        this.cityPopulationPercentage = cityPopulationPercentage;
    }

    /**
     * Gets the population not living in cities (rural population) within the area.
     *
     * @return The non-city population.
     */
    public long getNonCityPopulation() {
        return nonCityPopulation;
    }

    /**
     * Sets the population not living in cities within the area.
     *
     * @param nonCityPopulation The non-city population to set.
     */
    public void setNonCityPopulation(long nonCityPopulation) {
        this.nonCityPopulation = nonCityPopulation;
    }

    /**
     * Gets the percentage of the total population not living in cities.
     *
     * @return The percentage of the non-city population.
     */
    public double getNonCityPopulationPercentage() {
        return nonCityPopulationPercentage;
    }

    /**
     * Sets the percentage of the total population not living in cities.
     *
     * @param nonCityPopulationPercentage The percentage of the non-city population to set.
     */
    public void setNonCityPopulationPercentage(double nonCityPopulationPercentage) {
        this.nonCityPopulationPercentage = nonCityPopulationPercentage;
    }
}
