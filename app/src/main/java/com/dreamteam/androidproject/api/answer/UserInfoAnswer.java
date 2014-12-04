package com.dreamteam.androidproject.api.answer;

import android.os.Bundle;
import android.util.Log;

import com.dreamteam.androidproject.api.query.Auth;

public class UserInfoAnswer {
    public static String USERNAME           = "USERNAME";
    public static String PLAYS_COUNT        = "PLAY_COUNT";
    public static String USER_PHOTO_RES     = "USER_RHOTO_RES";
    public static String USER_BIG_IMAGE_RES = "USER_RHOTO_RES";
    public static String STATUS_USER_INFO   = "STATUS_USER_INFO";

    private String status;
    private String username;
    private String imagesmall;
    private String imagemedium;
    private String imagelarge;
    private String imageextralarge;
    private String realname;
    private String url;
    private String id;
    private String country;
    private String age;
    private String gender;
    private String subscriber;
    private String playcount;
    private String playlists;
    private String bootstrap;
    private String registered;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String playlists) {
        this.playlists = playlists;
    }

    public String getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(String bootstrap) {
        this.bootstrap = bootstrap;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public Bundle getBundleObject() {
        Bundle bun = new Bundle();
        bun.putString(USERNAME, username);
        bun.putString(PLAYS_COUNT, playcount);
        Log.d("TAG_USER_INFO", imagesmall);
        Log.d("TAG_USER_INFO", imagelarge);
        Log.d("TAG_USER_INFO", imagemedium);
        bun.putString(USER_PHOTO_RES, imagelarge);
        bun.putString(STATUS_USER_INFO, status);
        return bun;
    }

}
