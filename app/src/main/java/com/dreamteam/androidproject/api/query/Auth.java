package com.dreamteam.androidproject.api.query;

import android.util.Log;

import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.connection.SecretData;
import org.json.JSONObject;
import com.dreamteam.androidproject.api.template.Common;

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
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AuthAnswer answer = new AuthAnswer();
        answer.setTextStatus(status);
        answer.setStatus(errorToCode(status));
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject session = obj.getJSONObject("session");
        answer.setName(session.getString("name"));
        answer.setKey(session.getString("key"));
        answer.setSubscriber(session.getString("subscriber"));
        return answer;
    }

    public AuthAnswer auth() {
        if (this.username.length() == 0 || this.password.length() == 0) {
            AuthAnswer answer = new AuthAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }

        String query = "method=auth.getMobileSession&format=json" + "&api_key=" + SecretData.KEY + "&username=" + this.username + "&password=" + this.password + "&api_sig=" + this.sign;

        try {
            AuthAnswer answer = parse(sendQuery(query));
            answer.setPassword(this.password);
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
