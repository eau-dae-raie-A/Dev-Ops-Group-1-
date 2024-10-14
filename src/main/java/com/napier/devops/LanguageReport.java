package com.napier.devops;

public class LanguageReport {
    private String language;
    private long speakerCount;
    private double worldPercentage;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getSpeakerCount() {
        return speakerCount;
    }

    public void setSpeakerCount(long speakerCount) {
        this.speakerCount = speakerCount;
    }

    public double getWorldPercentage() {
        return worldPercentage;
    }

    public void setWorldPercentage(double worldPercentage) {
        this.worldPercentage = worldPercentage;
    }

    public String getWorldPercentageString() {
        return String.format("%.2f%%", worldPercentage);
    }
}
