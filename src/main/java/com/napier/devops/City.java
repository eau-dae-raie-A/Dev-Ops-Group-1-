package com.napier.devops;

/**
 * Represents a country
 */
public class City {
    // Attributes corresponding to the columns in the City table

    // ID - Primary Key, Auto-incremented
    private int id;

    // Name - City name, non-null, character field with max length 35
    private String name;

    // CountryCode - Index, non-null, character field with max length 3
    private String countryCode;

    // District - Name of the district, non-null, character field with max length 20
    private String district;

    // Population - Non-null, integer field
    private int population;

    // Getters and Setters for all attributes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

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

