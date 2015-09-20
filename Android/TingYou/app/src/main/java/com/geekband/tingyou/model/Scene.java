package com.geekband.tingyou.model;

/**
 *
 * Parts of the objects are NOT correctly implement, this is a simplified version of the object.
 */
public class Scene {
    private String name;
    private int voice;
    private int message;
    private int boughtTimes;
    private int starLevel;
    private boolean hasActivity;
    private boolean newOnline;

    public Scene(String name, int voice, int message, int boughtTimes, int starLevel, boolean hasActivity, boolean newOnline) {
        this.name = name;
        this.voice = voice;
        this.message = message;
        this.boughtTimes = boughtTimes;
        this.starLevel = starLevel;
        this.hasActivity = hasActivity;
        this.newOnline = newOnline;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public String getName() {
        return name;
    }

    public int getVoice() {
        return voice;
    }

    public int getMessage() {
        return message;
    }

    public int getBoughtTimes() {
        return boughtTimes;
    }

    public boolean isHasActivity() {
        return hasActivity;
    }

    public boolean isNewOnline() {
        return newOnline;
    }
}
