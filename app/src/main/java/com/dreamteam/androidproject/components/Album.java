package com.dreamteam.androidproject.components;

import java.io.Serializable;

public class Album implements Serializable{

    private final String title;
    private final int releaseYear;
    private int albumImageRes;
    private Musician creator;

    public Album(String title, int releaseYear, int albumImageRes, Musician creator) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.albumImageRes = albumImageRes;
        this.creator = creator;
    }

    public String getTitle() {
        return this.title;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public Musician getCreator() {
        return this.creator;
    }

    public int getAlbumImageRes() {
        return this.albumImageRes;
    }

}
