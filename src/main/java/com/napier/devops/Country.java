package com.napier.devops;

/**
 * Represents a country
 */
public class Country {
    /**
     * Country code (Primary key)
     */
    private String code;

    /**
     * Country's name
     */
    private String name;

    /**
     * Country's continent
     */
    private String continent;

    /**
     * Country's region
     */
    private String region;

    /**
     * Country's surface area
     */
    private double surfaceArea;

    /**
     * Year the country gained independence (nullable)
     */
    private Integer indepYear;

    /**
     * Country's population
     */
    private int population;

    /**
     * Country's life expectancy (nullable)
     */
    private Double lifeExpectancy;

    /**
     * Country's Gross National Product (GNP) (nullable)
     */
    private Double gnp;

    /**
     * Country's old Gross National Product (GNPOld) (nullable)
     */
    private Double gnpOld;

    /**
     * Country's local name
     */
    private String localName;

    /**
     * Form of government in the country
     */
    private String governmentForm;

    /**
     * Head of state (nullable)
     */
    private String headOfState;

    /**
     * Capital city ID (nullable)
     */
    private Integer capital;

    /**
     * Country code (2-letter)
     */
    private String code2;

    // Getters and Setters for all attributes

    // Adding a City object to represent the capital city
    private City capitalCity;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Integer getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(Integer indepYear) {
        this.indepYear = indepYear;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(Double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public Double getGnp() {
        return gnp;
    }

    public void setGnp(Double gnp) {
        this.gnp = gnp;
    }

    public Double getGnpOld() {
        return gnpOld;
    }

    public void setGnpOld(Double gnpOld) {
        this.gnpOld = gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    // Getters and setters for the capitalCity
    public City getCapitalCity() {
        return capitalCity;
    }

    public void setCapitalCity(City capitalCity) {
        this.capitalCity = capitalCity;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", surfaceArea=" + surfaceArea +
                ", indepYear=" + indepYear +
                ", population=" + population +
                ", lifeExpectancy=" + lifeExpectancy +
                ", gnp=" + gnp +
                ", gnpOld=" + gnpOld +
                ", localName='" + localName + '\'' +
                ", governmentForm='" + governmentForm + '\'' +
                ", headOfState='" + headOfState + '\'' +
                ", capital=" + capital +
                ", code2='" + code2 + '\'' +
                '}';
    }
}
