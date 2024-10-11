package com.napier.devops;

/**
 * Represents a language spoken in a country, including details about its official status
 * and the percentage of the population that speaks it.
 */
public class CountryLanguage {

    /**
     * The country code corresponding to the country where the language is spoken.
     */
    private String countryCode;

    /**
     * The language spoken in the country.
     */
    private String language;

    /**
     * Indicates whether the language is official in the country (T for True, F for False).
     */
    private String isOfficial;

    /**
     * The percentage of the country's population that speaks this language.
     */
    private double percentage;

    /**
     * Gets the country code.
     *
     * @return The country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the country code.
     *
     * @param countryCode The country code to set.
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Gets the language.
     *
     * @return The language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language.
     *
     * @param language The language to set.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets the official status of the language in the country.
     *
     * @return "T" if the language is official, "F" otherwise.
     */
    public String getIsOfficial() {
        return isOfficial;
    }

    /**
     * Sets the official status of the language in the country.
     *
     * @param isOfficial "T" for True (official), "F" for False (not official).
     */
    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    /**
     * Gets the percentage of the population that speaks this language.
     *
     * @return The percentage of speakers.
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * Sets the percentage of the population that speaks this language.
     *
     * @param percentage The percentage of speakers to set.
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    /**
     * Returns a string representation of the CountryLanguage object.
     *
     * @return A string containing the country code, language, official status, and percentage of speakers.
     */
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
