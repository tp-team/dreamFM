package com.dreamteam.androidproject.components;

import java.io.Serializable;


public class Track implements Serializable {
    private String trackName;
    private String playDate;
    private Musician musician;

    public Track(String trackName, String playDate, Musician musician) {
        this.trackName = trackName;
        this.playDate = playDate;
        this.musician = musician;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getPlayDate() {
        return playDate;
    }

    public Musician getMusician() {
        return musician;
    }
}
