package com.dreamteam.androidproject.api;

/**
 * Created by nap on 10/30/2014.
 */

import org.json.JSONObject;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    protected String strToMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(str.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashText = bigInt.toString(16);
        while(hashText.length() < 32 ) {
            hashText = "0" + hashText;
        }
        return hashText;
    }

    public User(String userName) {
        USERNAME = userName;
    }

    public User() {}
}
