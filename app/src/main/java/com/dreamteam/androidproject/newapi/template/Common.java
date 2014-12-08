package com.dreamteam.androidproject.newapi.template;

/**
 * Created by nap on 10/30/2014.
 */

import com.dreamteam.androidproject.newapi.connection.SecretData;
import com.dreamteam.androidproject.newapi.connection.URLConnector;
import org.json.JSONObject;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Common {
    protected final String JSON_CONNECTION_ERROR = "{\"error\":0,\"message\":\"No internet connection!\"}";
    protected final String JSON_UNKNOWN_ERROR = "{\"error\":0,\"message\":\"Unknown error.\"}";
    protected final String EMPTY_STRING = "Empty string!";
    protected final String UNKNOWN_ERROR = "Unknown error.";
    protected final String OK = "ok";
    protected final String USER_NOT_EXISTS = "Invalid username. No last.fm account associated with that name.";
    protected final String WRONG_PASSWORD = "Invalid password. Please check username/password supplied";
    protected final String NO_INTERNET = "No internet connection!";
    protected final String INVALID_SESSION_KEY = "";

    protected abstract Object parse(String str) throws Exception;

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

    protected String sendQuery(String query) {
        URLConnector http = new URLConnector();
        String response;
        try {
            response = http.sendPost(SecretData.ROOT, query);
        } catch (UnknownHostException e) {
            response = JSON_CONNECTION_ERROR;
        } catch (Exception e) {
            response = JSON_UNKNOWN_ERROR;
            e.printStackTrace();
        }
        return response;
    }

    protected String errorToCode(String error) {
        if (error.equals(OK)) {
            return "100";
        }
        if (error.equals(USER_NOT_EXISTS)) {
            return "101";
        }
        if (error.equals(WRONG_PASSWORD)) {
            return "102";
        }
        if (error.equals(NO_INTERNET)) {
            return "103";
        }
        if (error.equals(EMPTY_STRING)) {
            return "104";
        }
        if (error.equals(INVALID_SESSION_KEY)) {
            return "105";
        }
        return UNKNOWN_ERROR;
    }
}
