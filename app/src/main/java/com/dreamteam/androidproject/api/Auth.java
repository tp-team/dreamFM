package com.dreamteam.androidproject.api;

/**
 * Created by nap on 10/27/2014.
 */
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

public class Auth {
    private String username;
    private String password;
    private String sign;
    private String userKey;

    public Auth(String username, String password) {
        this.username = username;
        this.password = password;
        setSign();
    }

    private void setSign() {
       this.sign = DigestUtils.md5Hex("api_key" + SecretData.KEY + "methodauth.getMobileSession" + "password" + this.password + "username" + this.username + SecretData.SECRET);
    }

    private String parseAuth(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        String error = getError(obj);
        if (error == "ok") {
            JSONObject session = obj.getJSONObject("session");
            this.userKey = (String) session.get("key");
        }
        return error;
    }

    public String auth() throws Exception {
        URLConnector http = new URLConnector();
        if (this.username.length() == 0 || this.password.length() == 0) {
            return "check data is correct!";
        }
        String response = http.sendPost(SecretData.ROOT, "method=auth.getMobileSession&format=json" + "&api_key=" + SecretData.KEY + "&username=" + this.username + "&password=" + this.password + "&api_sig=" + this.sign);
        return parseAuth(response);
    }

    private String getError(JSONObject obj) throws Exception {
        if (!obj.has("error")) {
            return "ok";
        }
        else {
            return (String) obj.get("message");
        }
    }
}
