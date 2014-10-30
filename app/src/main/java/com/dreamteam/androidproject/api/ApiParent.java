package com.dreamteam.androidproject.api;

/**
 * Created by nap on 10/30/2014.
 */
import org.json.JSONObject;
import java.sql.Timestamp;

public abstract class ApiParent {
    protected static String USERNAME;
    protected static String USERKEY;
    protected static String IMAGESMALL;
    protected static String IMAGEMEDIUM;
    protected static String IMAGELARGE;
    protected static String IMAGEEXTRALARGE;
    protected static String REALNAME;
    protected static String URL;
    protected static String ID;
    protected static String AGE;
    protected static String GENDER;
    protected static String SUBSCRIBER;
    protected static String PLAYCOUNT;
    protected static String PLAYLISTS;
    protected static String BOOTSTRAP;
    protected static Timestamp REGISTERED;

    protected abstract String parse(String str) throws Exception;

    protected String getError(JSONObject obj) throws Exception {
        if (!obj.has("error")) {
            return "ok";
        }
        else {
            return (String) obj.get("message");
        }
    }
}
