package com.napier.devops;

public class PopulationData {
    private String name; // This will store the continent name
    private long totalPopulation;
    private long cityPopulation;
    private long nonCityPopulation;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public long getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(long cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public long getNonCityPopulation() {
        return nonCityPopulation;
    }

    public void setNonCityPopulation(long nonCityPopulation) {
        this.nonCityPopulation = nonCityPopulation;
    }
}

