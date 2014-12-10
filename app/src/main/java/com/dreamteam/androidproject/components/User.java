package com.dreamteam.androidproject.components;


import java.io.Serializable;

public class User implements Serializable{
    private final String userName;
    private final String nickName;
    private String userPhotoRes;
    private String playsCount;
    private int userBgImageRes;
    private String registered;

    public User(String userName, String nickName, String userPhotoRes, int userBgImageRes, String playsCount, String registered) {
        this.userName = userName;
        this.nickName = nickName;
        this.userPhotoRes = userPhotoRes;
        this.playsCount = playsCount;
        this.userBgImageRes = userBgImageRes;
        this.registered = registered;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhotoRes() {
        return userPhotoRes;
    }

    public String getPlaysCount() {
        return playsCount;
    }

    public int getUserBgImageRes() {
        return userBgImageRes;
    }

    public String getRegistered() {
        return registered;
    }

    public String getPlaysCountDesc() {
        return playsCount + " plays since " + registered;
    }

    public String getNickName() {
        return nickName;
    }
}
