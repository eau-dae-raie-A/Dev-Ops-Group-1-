package com.napier.devops;

/**
 * Represents a country with various attributes such as code, name, population,
 * and other demographic, economic, and political details.
 */
public class Country {

    /**
     * The unique country code (Code).
     */
    private String code;

    /**
     * The name of the country.
     */
    private String name;

    /**
     * The continent on which the country is located.
     */
    private String continent;

    /**
     * The capital city of the country, represented by a City object.
     */
    private City capitalCity;

    /**
     * The specific region of the continent where the country is located.
     */
    private String region;

    /**
     * The surface area of the country in square kilometers.
     */
    private double surfaceArea;

    /**
     * The year the country gained independence. This value can be null.
     */
    private Integer indepYear;

    /**
     * The total population of the country.
     */
    private int population;

    /**
     * The life expectancy in the country.
     */
    private Double lifeExpectancy;

    /**
     * The Gross National Product (GNP) of the country.
     */
    private Double gnp;

    /**
     * The old Gross National Product (GNP) of the country,
     */
    private Double gnpOld;

    /**
     * The local name of the country.
     */
    private String localName;

    /**
     * The form of government of the country (e.g., Republic, Monarchy).
     */
    private String governmentForm;

    /**
     * The head of state (e.g., President, King).
     */
    private String headOfState;

    /**
     * The ID of the capital city (matches with the City's ID).
     */
    private Integer capital;

    /**
     * The alternative country code (Code2).
     */
    private String code2;

    // Getters and Setters for all attributes

    /**
     * Gets the country code.
     *
     * @return The country code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the country code.
     *
     * @param code The country code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the country's name.
     *
     * @return The name of the country.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the country's name.
     *
     * @param name The name of the country to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the continent where the country is located.
     *
     * @return The continent.
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Sets the continent where the country is located.
     *
     * @param continent The continent to set.
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Gets the region within the continent where the country is located.
     *
     * @return The region.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region within the continent where the country is located.
     *
     * @param region The region to set.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets the surface area of the country.
     *
     * @return The surface area in square kilometers.
     */
    public double getSurfaceArea() {
        return surfaceArea;
    }

    /**
     * Sets the surface area of the country.
     *
     * @param surfaceArea The surface area to set.
     */
    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    /**
     * Gets the year the country gained independence.
     *
     * @return The year of independence, or null if not applicable.
     */
    public Integer getIndepYear() {
        return indepYear;
    }

    /**
     * Sets the year the country gained independence.
     *
     * @param indepYear The year of independence to set.
     */
    public void setIndepYear(Integer indepYear) {
        this.indepYear = indepYear;
    }

    /**
     * Gets the population of the country.
     *
     * @return The population.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Sets the population of the country.
     *
     * @param population The population to set.
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Gets the life expectancy in the country.
     *
     * @return The life expectancy, or null if not available.
     */
    public Double getLifeExpectancy() {
        return lifeExpectancy;
    }

    /**
     * Sets the life expectancy in the country.
     *
     * @param lifeExpectancy The life expectancy to set.
     */
    public void setLifeExpectancy(Double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    /**
     * Gets the Gross National Product (GNP) of the country.
     *
     * @return The GNP, or null if not available.
     */
    public Double getGnp() {
        return gnp;
    }

    /**
     * Sets the Gross National Product (GNP) of the country.
     *
     * @param gnp The GNP to set.
     */
    public void setGnp(Double gnp) {
        this.gnp = gnp;
    }

    /**
     * Gets the old Gross National Product (GNP) of the country.
     *
     * @return The old GNP, or null if not available.
     */
    public Double getGnpOld() {
        return gnpOld;
    }

    /**
     * Sets the old Gross National Product (GNP) of the country.
     *
     * @param gnpOld The old GNP to set.
     */
    public void setGnpOld(Double gnpOld) {
        this.gnpOld = gnpOld;
    }

    /**
     * Gets the local name of the country.
     *
     * @return The local name.
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Sets the local name of the country.
     *
     * @param localName The local name to set.
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * Gets the form of government in the country.
     *
     * @return The government form.
     */
    public String getGovernmentForm() {
        return governmentForm;
    }

    /**
     * Sets the form of government in the country.
     *
     * @param governmentForm The government form to set.
     */
    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    /**
     * Gets the head of state in the country.
     *
     * @return The head of state.
     */
    public String getHeadOfState() {
        return headOfState;
    }

    /**
     * Sets the head of state in the country.
     *
     * @param headOfState The head of state to set.
     */
    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    /**
     * Gets the ID of the capital city.
     *
     * @return The capital city ID.
     */
    public Integer getCapital() {
        return capital;
    }

    /**
     * Sets the ID of the capital city.
     *
     * @param capital The capital city ID to set.
     */
    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    /**
     * Gets the alternative country code (Code2).
     *
     * @return The alternative country code.
     */
    public String getCode2() {
        return code2;
    }

    /**
     * Sets the alternative country code (Code2).
     *
     * @param code2 The alternative country code to set.
     */
    public void setCode2(String code2) {
        this.code2 = code2;
    }

    /**
     * Gets the capital city as a City object.
     *
     * @return The capital city object.
     */
    public City getCapitalCity() {
        return capitalCity;
    }

    /**
     * Sets the capital city as a City object.
     *
     * @param capitalCity The capital city object to set.
     */
    public void setCapitalCity(City capitalCity) {
        this.capitalCity = capitalCity;
    }

    /**
     * Returns a string representation of the Country object.
     *
     * @return A string containing the country's details.
     */
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
