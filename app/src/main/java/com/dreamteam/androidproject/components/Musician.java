package com.dreamteam.androidproject.components;

import java.io.Serializable;
import java.util.ArrayList;

public class Musician implements Serializable{

    private final String musicianName;
    private int musicianImageRes;
    private ArrayList<Musician> similarMusicians;

    public Musician(String musicianName, int musicianImageRes, ArrayList<Musician> similarMusicians) {
        this.musicianName = musicianName;
        this.similarMusicians = similarMusicians;
        this.musicianImageRes = musicianImageRes;
    }

    public String getMusicianName() {
        return this.musicianName;
    }

    public ArrayList<Musician> getSimilarMusicians() {
        return this.similarMusicians;
    }

    public int getMusicianImageRes() {
        return this.musicianImageRes;
    }

}
