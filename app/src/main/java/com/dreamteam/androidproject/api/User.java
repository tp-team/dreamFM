package com.dreamteam.androidproject.api;
/**
 * Created by nap on 10/30/2014.
 */
import org.json.JSONObject;

public abstract class User {
    protected static String USERNAME;
    protected static String USERKEY;
    protected static String IMAGESMALL;
    protected static String IMAGEMEDIUM;
    protected static String IMAGELARGE;
    protected static String IMAGEEXTRALARGE;
    protected static String REALNAME;
    protected static String URL;
    protected static String ID;
    protected static String COUNTRY;
    protected static String AGE;
    protected static String GENDER;
    protected static int SUBSCRIBER;
    protected static int PLAYCOUNT;
    protected static int PLAYLISTS;
    protected static int BOOTSTRAP;
    protected static String REGISTERED;

    protected abstract String parse(String str) throws Exception;

    protected String getStatus(JSONObject obj) throws Exception {
        if (!obj.has("error")) {
            return "ok";
        }
        else {
            return (String) obj.get("message");
        }
    }
}
