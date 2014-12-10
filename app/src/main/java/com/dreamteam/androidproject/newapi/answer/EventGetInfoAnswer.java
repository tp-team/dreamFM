package com.dreamteam.androidproject.newapi.answer;

import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/11/2014.
 */

public class EventGetInfoAnswer {
    private String status;
    private String id;
    private String title;
    private ObjectList<String> artists;
    private String headliner;
    private VenueAnswer venue;
    private String startdate;
    private String description;
    private String imagesmall;
    private String imagemedium;
    private String imagelarge;
    private String imageextralarge;
    private String attendance;
    private String reviews;
    private String tag;
    private String url;
    private String website;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObjectList<String> getArtists() {
        return artists;
    }

    public void setArtists(ObjectList<String> artists) {
        this.artists = artists;
    }

    public String getHeadliner() {
        return headliner;
    }

    public void setHeadliner(String headliner) {
        this.headliner = headliner;
    }

    public VenueAnswer getVenue() {
        return venue;
    }

    public void setVenue(VenueAnswer venue) {
        this.venue = venue;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagesmall() {
        return imagesmall;
    }

    public void setImagesmall(String imagesmall) {
        this.imagesmall = imagesmall;
    }

    public String getImagemedium() {
        return imagemedium;
    }

    public void setImagemedium(String imagemedium) {
        this.imagemedium = imagemedium;
    }

    public String getImagelarge() {
        return imagelarge;
    }

    public void setImagelarge(String imagelarge) {
        this.imagelarge = imagelarge;
    }

    public String getImageextralarge() {
        return imageextralarge;
    }

    public void setImageextralarge(String imageextralarge) {
        this.imageextralarge = imageextralarge;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
