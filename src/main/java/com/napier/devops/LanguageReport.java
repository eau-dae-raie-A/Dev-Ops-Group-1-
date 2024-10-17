package com.napier.devops;

/**
 * The LanguageReport class represents a report on a language, including the
 * language name, the number of speakers, and the percentage of the world's
 * population that speaks the language.
 */
public class LanguageReport {
    /**
     * The name of the language.
     */
    private String language;

    /**
     * The number of people who speak the language.
     */
    private long speakerCount;

    /**
     * The percentage of the world's population that speaks the language.
     */
    private double worldPercentage;

    /**
     * Gets the name of the language.
     *
     * @return the name of the language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the name of the language.
     *
     * @param language the name of the language to set.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets the number of speakers for the language.
     *
     * @return the number of speakers.
     */
    public long getSpeakerCount() {
        return speakerCount;
    }

    /**
     * Sets the number of speakers for the language.
     *
     * @param speakerCount the number of speakers to set.
     */
    public void setSpeakerCount(long speakerCount) {
        this.speakerCount = speakerCount;
    }

    /**
     * Gets the percentage of the world's population that speaks the language.
     *
     * @return the percentage of the world population that speaks the language.
     */
    public double getWorldPercentage() {
        return worldPercentage;
    }

    /**
     * Sets the percentage of the world's population that speaks the language.
     *
     * @param worldPercentage the percentage of the world population to set.
     */
    public void setWorldPercentage(double worldPercentage) {
        this.worldPercentage = worldPercentage;
    }

    /**
     * Gets the world percentage as a formatted string, rounded to two decimal places.
     *
     * @return the world percentage formatted as a string with two decimal places and a percent sign.
     */
    public String getWorldPercentageString() {
        return String.format("%.2f%%", worldPercentage);
    }
}
