package com.dreamteam.androidproject.newapi.answer;

/**
 * Created by nap on 12/2/2014.
 */

public class AuthAnswer {
    private String status;
    private String name;
    private String key;
    private String sign;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSubscriber() {
        return this.subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }
}
