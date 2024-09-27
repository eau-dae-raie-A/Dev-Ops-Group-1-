package com.napier.devops;

public class City {
    // Attributes of the City class
    private String name;       // City's name
    private String country;    // Country where the city is located
    private String district;   // District of the city
    private int population;    // Population of the city

    // Getter and setter for city's name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for city's country
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Getter and setter for city's district
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    // Getter and setter for city's population
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
