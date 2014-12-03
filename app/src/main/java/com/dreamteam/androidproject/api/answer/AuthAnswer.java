package com.dreamteam.androidproject.api.answer;


import android.os.Bundle;

public class AuthAnswer {
    public static String STATUS = "STATUS";
    public static String KEY    = "KEY";
    private String status;
    private String name;
    private String key;
    private String subscriber;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSubscriber() {
        return this.subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public Bundle getBundelObject() {
        Bundle bun = new Bundle();
        bun.putString(STATUS, status);
        bun.putString(KEY, key);
        return bun;
    }

}
