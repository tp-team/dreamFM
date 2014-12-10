package com.dreamteam.androidproject.api.answer;

import android.os.Bundle;

import com.dreamteam.androidproject.api.template.ObjectList;

/**
 * Created by nap on 12/5/2014.
 */
public class TrackGetInfoAnswer {
    public static String STATUS_TRACK_INFO = "STATUS_TRACK_INFO";
    public static String TEXT_STATUS       = "TEXT_STATUS";

    private String status;
    private String textStatus;
    private String id;
    private String name;
    private String mbid;
    private String url;
    private String duration;
    private String streamable;
    private String listeners;
    private String playcount;
    private ArtistGetInfoAnswer artist;
    private AlbumGetInfoAnswer album;
    private ObjectList<TagGetInfoAnswer> tags;
    private String published;
    private String summary;
    private String content;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
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

    public ArtistGetInfoAnswer getArtist() {
        return artist;
    }

    public void setArtist(ArtistGetInfoAnswer artist) {
        this.artist = artist;
    }

    public AlbumGetInfoAnswer getAlbum() {
        return album;
    }

    public void setAlbum(AlbumGetInfoAnswer album) {
        this.album = album;
    }

    public ObjectList<TagGetInfoAnswer> getTags() {
        return tags;
    }

    public void setTags(ObjectList<TagGetInfoAnswer> tags) {
        this.tags = tags;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }

    public Bundle getBundleObject() {
        Bundle bun = new Bundle();
        bun.putString(STATUS_TRACK_INFO, status);
        bun.putString(TEXT_STATUS, textStatus);
        return bun;
    }


}
