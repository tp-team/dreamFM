package com.dreamteam.androidproject.api.template;

/**
 * Created by nap on 10/30/2014.
 */

import com.dreamteam.androidproject.api.connection.SecretData;
import com.dreamteam.androidproject.api.connection.URLConnector;

import org.json.JSONException;
import org.json.JSONObject;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Common {

    public static final String STATUS_OK                  = "100";
    public static final String STATUS_USER_NOT_EXISTS     = "101";
    public static final String STATUS_WRONG_PASSWORD      = "102";
    public static final String STATUS_NO_INTERNET         = "103";
    public static final String STATUS_EMPTY_STRING        = "104";
    public static final String STATUS_INVALID_SESSION_KEY = "105";
    public static final String STATUS_EMPTY_DATA_ERROR    = "106";
    public static final String STATUS_FAIL                = "0";

    protected final String JSON_CONNECTION_ERROR = "{\"error\":0,\"message\":\"No internet connection!\"}";

    protected final String EMPTY_STRING = "Empty string!";
    protected final String JSON_EMPTY_DATA_ERROR = "{\"error\":0,\"message\":\"Empty data error.\"}";
    protected final String EMPTY_DATA_ERROR = "Empty data error.";
    protected final String OK = "ok";
    protected final String USER_NOT_EXISTS = "Invalid username. No last.fm account associated with that name.";
    protected final String WRONG_PASSWORD = "Invalid password. Please check username/password supplied";
    protected final String NO_INTERNET = "No internet connection!";
    protected final String INVALID_SESSION_KEY = "";

    protected abstract Object parse(String str) throws Exception;

    protected String getStatus(JSONObject obj) {
        if (!obj.has("error")) {
            return "ok";
        }
        else {
            try {
                return (String) obj.get("message");
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    protected String strToMD5(String str) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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

    protected String sendQuery(String query) {
        URLConnector http = new URLConnector();
        String response;
        try {
            response = http.sendPost(SecretData.ROOT, query);
        } catch (UnknownHostException e) {
            response = JSON_CONNECTION_ERROR;
        } catch (Exception e) {
            response = JSON_EMPTY_DATA_ERROR;
            e.printStackTrace();
        }
        return response;
    }

    protected String errorToCode(String error) {
        if (error.equals(OK)) {
            return STATUS_OK;
        }
        if (error.equals(USER_NOT_EXISTS)) {
            return STATUS_USER_NOT_EXISTS;
        }
        if (error.equals(WRONG_PASSWORD)) {
            return STATUS_WRONG_PASSWORD;
        }
        if (error.equals(NO_INTERNET)) {
            return STATUS_NO_INTERNET;
        }
        if (error.equals(EMPTY_STRING)) {
            return STATUS_EMPTY_STRING;
        }
        if (error.equals(INVALID_SESSION_KEY)) {
            return STATUS_INVALID_SESSION_KEY;
        }
        if (error.equals(EMPTY_DATA_ERROR)) {
                return STATUS_EMPTY_DATA_ERROR;
        }

        return STATUS_FAIL;
    }
}
