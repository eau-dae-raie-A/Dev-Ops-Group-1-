package com.napier.devops;

/**
 * The LanguageReport class represents a report on languages spoken around the world.
 * It includes details about the language, the number of speakers, and the percentage
 * of the world's population that speaks the language.
 */
public class LanguageReport {

    // The name of the language
    private String language;

    // The number of people who speak this language
    private long speakers;

    // The percentage of the world's population that speaks this language
    private double worldPercentage;

    // Getters and Setters for the private fields

    /**
     * Gets the name of the language.
     *
     * @return The language name.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the name of the language.
     *
     * @param language The language name to set.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets the number of speakers of the language.
     *
     * @return The number of speakers.
     */
    public long getSpeakers() {
        return speakers;
    }

    /**
     * Sets the number of speakers of the language.
     *
     * @param speakers The number of speakers to set.
     */
    public void setSpeakers(long speakers) {
        this.speakers = speakers;
    }

    /**
     * Gets the percentage of the world's population that speaks the language.
     *
     * @return The world population percentage for this language.
     */
    public double getWorldPercentage() {
        return worldPercentage;
    }

    /**
     * Sets the percentage of the world's population that speaks the language.
     *
     * @param worldPercentage The world population percentage to set.
     */
    public void setWorldPercentage(double worldPercentage) {
        this.worldPercentage = worldPercentage;
    }
}
