package com.napier.devops;

/**
 * Represents a country language
 */
public class CountryLanguage {
    /**
     * Country code
     */
    private String countryCode;

    /**
     * Language
     */
    private String language;

    /**
     * Indicates if the language is official (T for True, F for False)
     */
    private String isOfficial;

    /**
     * Percentage of the population speaking this language
     */
    private double percentage;

    // Getters and Setters for all attributes

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "countryCode='" + countryCode + '\'' +
                ", language='" + language + '\'' +
                ", isOfficial='" + isOfficial + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
