package com.dreamteam.androidproject.api.query;

/**
 * Created by nap on 10/27/2014.
 */

import android.util.Log;

import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.connection.SecretData;
import com.dreamteam.androidproject.api.connection.URLConnector;
import org.json.JSONObject;
import com.dreamteam.androidproject.api.template.Common;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

public class Auth extends Common {
    private String username;
    private String password;
    private String sign;

    public Auth(String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.password = password;
        this.sign = strToMD5("api_key" + SecretData.KEY + "methodauth.getMobileSession" + "password" + this.password + "username" + this.username + SecretData.SECRET);
    }

    @Override
    protected AuthAnswer parse(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        String status = getStatus(obj);
        AuthAnswer answer = new AuthAnswer();
        answer.setStatus(status);
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject session = obj.getJSONObject("session");
        answer.setName(session.getString("name"));
        answer.setKey(session.getString("key"));
        answer.setSubscriber(session.getString("subscriber"));
        return answer;
    }

    public AuthAnswer auth() throws Exception {
        URLConnector http = new URLConnector();
        if (this.username.length() == 0 || this.password.length() == 0) {
            AuthAnswer answer = new AuthAnswer();
            answer.setStatus(EMPTY_STRING);
            return answer;
        }
        Log.d("TAG AUTH", "!!!!!!!!!!1111111");
        String response;
        try {
            response = http.sendPost(SecretData.ROOT, "method=auth.getMobileSession&format=json" + "&api_key=" + SecretData.KEY + "&username=" + this.username + "&password=" + this.password + "&api_sig=" + this.sign);
        } catch (UnknownHostException e) {
            response = CONNECTION_ERROR;
        }
        Log.d("TAG AUTH", "!!!!!!!!!!222222222");
        return parse(response);
    }
}
