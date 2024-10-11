package com.napier.devops;

import java.text.DecimalFormat;

/**
 * Represents a report on the population distribution in a specific geographical area,
 * including the total population, city population, non-city population, and their percentages.
 */
public class PopulationReport {
    // Fields to store population data and percentage strings for a geographical area

    private String name; // Name of the geographical area (e.g., continent, region, or country)
    private long totalPopulation; // Total population of the area
    private long cityPopulation; // Population living in cities within the area
    private long nonCityPopulation; // Population not living in cities (non-city or rural population)

    private String cityPopulationPercentageString; // City population as a formatted percentage string
    private String nonCityPopulationPercentageString; // Non-city population as a formatted percentage string

    // Getter and setter methods for each attribute

    /**
     * Gets the name of the geographical area.
     * @return The name of the area.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the geographical area.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the total population of the area.
     * @return The total population.
     */
    public long getTotalPopulation() {
        return totalPopulation;
    }

    /**
     * Sets the total population of the area.
     * @param totalPopulation The total population to set.
     */
    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    /**
     * Gets the population living in cities within the area.
     * @return The city population.
     */
    public long getCityPopulation() {
        return cityPopulation;
    }

    /**
     * Sets the population living in cities within the area.
     * @param cityPopulation The city population to set.
     */
    public void setCityPopulation(long cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    /**
     * Gets the city population percentage as a formatted string (e.g., "46.05%").
     * @return The city population percentage string.
     */
    public String getCityPopulationPercentageString() {
        return cityPopulationPercentageString;
    }

    /**
     * Sets the city population percentage as a formatted string.
     * Accepts a double value and formats it as a percentage string.
     * @param cityPopulationPercentage The city population percentage to format and set.
     */
    public void setCityPopulationPercentage(double cityPopulationPercentage) {
        // Format the percentage value to a string with two decimal places and a '%' symbol
        DecimalFormat df = new DecimalFormat("#.##%");
        this.cityPopulationPercentageString = df.format(cityPopulationPercentage / 100); // Dividing by 100 to convert to percentage format
    }

    /**
     * Gets the population not living in cities (non-city or rural population).
     * @return The non-city population.
     */
    public long getNonCityPopulation() {
        return nonCityPopulation;
    }

    /**
     * Sets the non-city population (population not living in cities).
     * @param nonCityPopulation The non-city population to set.
     */
    public void setNonCityPopulation(long nonCityPopulation) {
        this.nonCityPopulation = nonCityPopulation;
    }

    /**
     * Gets the non-city population percentage as a formatted string (e.g., "53.95%").
     * @return The non-city population percentage string.
     */
    public String getNonCityPopulationPercentageString() {
        return nonCityPopulationPercentageString;
    }

    /**
     * Sets the non-city population percentage as a formatted string.
     * Accepts a double value and formats it as a percentage string.
     * @param nonCityPopulationPercentage The non-city population percentage to format and set.
     */
    public void setNonCityPopulationPercentage(double nonCityPopulationPercentage) {
        // Format the percentage value to a string with two decimal places and a '%' symbol
        DecimalFormat df = new DecimalFormat("#.##%");
        this.nonCityPopulationPercentageString = df.format(nonCityPopulationPercentage / 100); // Dividing by 100 to convert to percentage format
    }
}
