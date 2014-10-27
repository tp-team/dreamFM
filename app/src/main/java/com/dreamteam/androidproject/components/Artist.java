package com.dreamteam.androidproject.components;

import java.util.ArrayList;

public class Artist {

    public final String artistName;
    public int artistImageRes;
    public ArrayList<Artist> similarArtists;

    public Artist(String artistName, int artistImageRes, ArrayList<Artist> similarArtists) {
        this.artistName = artistName;
        this.similarArtists = similarArtists;
        this.artistImageRes = artistImageRes;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public ArrayList<Artist> getSimilarArtists() {
        return this.similarArtists;
    }

    public int getArtistImageRes() {
        return this.artistImageRes;
    }

}
