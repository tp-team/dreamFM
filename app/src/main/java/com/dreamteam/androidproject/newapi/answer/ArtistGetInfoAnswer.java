package com.dreamteam.androidproject.newapi.answer;

import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/4/2014.
 */

public class ArtistGetInfoAnswer {
    private String status;
    private String name;
    private String mbid;
    private String url;
    private String imagesmall;
    private String imagemedium;
    private String imagelarge;
    private String streamable;
    private String listeners;
    private String playcount;
    private ObjectList<ArtistGetInfoAnswer> similar;
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

    public String getPlays() {
        return playcount;
    }

    public void setPlays(String playcount) {
        this.playcount = playcount;
    }

    public ObjectList<ArtistGetInfoAnswer> getSimilar() {
        return similar;
    }

    public void setSimilar(ObjectList<ArtistGetInfoAnswer> similar) {
        this.similar = similar;
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
}
