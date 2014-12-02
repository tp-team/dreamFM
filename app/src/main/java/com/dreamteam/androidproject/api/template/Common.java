package com.dreamteam.androidproject.api.template;

/**
 * Created by nap on 10/30/2014.
 */

import org.json.JSONObject;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Common {
    protected final String CONNECTION_ERROR = "{\"error\":0,\"message\":\"No internet connection!\"}";
    protected final String EMPTY_STRING = "Empty string!";

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
}
