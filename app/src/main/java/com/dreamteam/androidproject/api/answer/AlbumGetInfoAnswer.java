package com.dreamteam.androidproject.api.answer;

import android.os.Bundle;

import com.dreamteam.androidproject.api.answer.TagGetInfoAnswer;
import com.dreamteam.androidproject.api.answer.TrackGetInfoAnswer;
import com.dreamteam.androidproject.api.template.ObjectList;


public class AlbumGetInfoAnswer {
    public static String STATUS_ALBUM_INFO = "STATUS_ALBUM_INFO";
    public static String TEXT_STATUS       = "TEXT_STATUS";

    private String status;
    private String textStatus;
    private String name;
    private String artist;
    private String id;
    private String mbid;
    private String url;
    private String releasedate;
    private String imagesmall;
    private String imagemedium;
    private String imagelarge;
    private String listeners;
    private String playcount;
    private ObjectList<TagGetInfoAnswer> toptags;
    private ObjectList<TrackGetInfoAnswer> tracks;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
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

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public ObjectList<TagGetInfoAnswer> getToptags() {
        return toptags;
    }

    public void setToptags(ObjectList<TagGetInfoAnswer> toptags) {
        this.toptags = toptags;
    }

    public ObjectList<TrackGetInfoAnswer> getTracks() {
        return tracks;
    }

    public void setTracks(ObjectList<TrackGetInfoAnswer> tracks) {
        this.tracks = tracks;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }

    public Bundle getBundleObject() {
        Bundle bun = new Bundle();
        bun.putString(STATUS_ALBUM_INFO, status);
        bun.putString(TEXT_STATUS, textStatus);
        return bun;
    }
}
