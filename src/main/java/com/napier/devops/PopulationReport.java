package com.napier.devops;

public class PopulationReport {
    private String name;
    private long totalPopulation;
    private long cityPopulation;
    private double cityPopulationPercentage;
    private long nonCityPopulation;
    private double nonCityPopulationPercentage;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getTotalPopulation() { return totalPopulation; }
    public void setTotalPopulation(long totalPopulation) { this.totalPopulation = totalPopulation; }

    public long getCityPopulation() { return cityPopulation; }
    public void setCityPopulation(long cityPopulation) { this.cityPopulation = cityPopulation; }

    public double getCityPopulationPercentage() { return cityPopulationPercentage; }
    public void setCityPopulationPercentage(double cityPopulationPercentage) { this.cityPopulationPercentage = cityPopulationPercentage; }

    public long getNonCityPopulation() { return nonCityPopulation; }
    public void setNonCityPopulation(long nonCityPopulation) { this.nonCityPopulation = nonCityPopulation; }

    public double getNonCityPopulationPercentage() { return nonCityPopulationPercentage; }
    public void setNonCityPopulationPercentage(double nonCityPopulationPercentage) { this.nonCityPopulationPercentage = nonCityPopulationPercentage; }
}
