package com.dreamteam.androidproject.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Event {

    private final String name;
    private String place;
    private int imageRes;
    private Calendar date = Calendar.getInstance();
    private Musician musician;
    private ArrayList<Integer> fansImages;

    public Event(String name, String place, int imageRes, Date date, Musician musician, ArrayList<Integer> fansImages) {
        this.name = name;
        this.place = place;
        this.imageRes = imageRes;
        this.date.setTime(date);
        this.musician = musician;
        this.fansImages = fansImages;
    }


    public String getName() {
        return name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public Date getDate() {
        return date.getTime();
    }

    public Musician getMusician() {
        return musician;
    }

    public ArrayList<Integer> getFansImages() {
        return fansImages;
    }

    public String getAttendance() {
        return "" + fansImages.size();
    }

    public String getDay() {
        return "" + date.get(Calendar.DAY_OF_MONTH);
    }

    public String getMonth() {
        return new SimpleDateFormat("MMM", Locale.ENGLISH).format(date.getTime()).toUpperCase();
    }

    public String getPlace() {
        return place;
    }
}
