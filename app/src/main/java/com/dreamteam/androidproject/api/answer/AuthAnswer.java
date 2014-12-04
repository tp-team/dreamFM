package com.dreamteam.androidproject.api.answer;


import android.os.Bundle;

public class AuthAnswer {
    public static String STATUS      = "STATUS";
    public static String KEY         = "KEY";
    public static String TEXT_STATUS = "TEXT_STATUS";
    public static String NAME        = "NAME";
    public static String PASSWORD    = "PASSWORD";

    private String status;
    private String textStatus;
    private String name;
    private String key;
    private String subscriber;
    private String password;

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

    public void setTextStatus(String text) {
        this.textStatus = text;
    }

    public String getTextStatus() {
        return this.textStatus;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public Bundle getBundleObject() {
        Bundle bun = new Bundle();
        bun.putString(STATUS, status);
        bun.putString(KEY, key);
        bun.putString(TEXT_STATUS, textStatus);
        bun.putString(NAME, name);
        bun.putString(PASSWORD, password);
        return bun;
    }

}
