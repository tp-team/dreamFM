package com.dreamteam.androidproject.api.answer;

import android.os.Bundle;


public class TagGetInfoAnswer {
    public static String STATUS_TAG_INFO = "STATUS_TAG_INFO";
    public static String TEXT_STATUS     = "TEXT_STATUS";

    private String status;
    private String textStatus;
    private String name;
    private String url;
    private String reach;
    private String taggins;
    private String streamable;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public String getTaggins() {
        return taggins;
    }

    public void setTaggins(String taggins) {
        this.taggins = taggins;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
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
        bun.putString(STATUS_TAG_INFO, status);
        bun.putString(TEXT_STATUS, textStatus);
        return bun;
    }


}
