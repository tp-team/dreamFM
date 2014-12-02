package com.dreamteam.androidproject.api.query;

/**
 * Created by nap on 10/30/2014.
 */

import com.dreamteam.androidproject.api.answer.UserInfoAnswer;
import com.dreamteam.androidproject.api.connection.SecretData;
import com.dreamteam.androidproject.api.connection.URLConnector;
import org.json.JSONArray;
import org.json.JSONObject;
import com.dreamteam.androidproject.api.template.Common;

import java.net.UnknownHostException;

public class UserInfo extends Common {
    private String username;

    public UserInfo(String username) {
        this.username = username;
    }

    @Override
    protected UserInfoAnswer parse(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        String status = getStatus(obj);
        UserInfoAnswer answer = new UserInfoAnswer();
        answer.setStatus(status);
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject user = obj.getJSONObject("user");
        answer.setUsername(user.getString("name"));
        answer.setRealname(user.getString("realname"));
        JSONArray image = user.getJSONArray("image");
        JSONObject typeImage;
        typeImage = image.getJSONObject(0);
        answer.setImagesmall(typeImage.getString("#text"));
        typeImage = image.getJSONObject(1);
        answer.setImagemedium(typeImage.getString("#text"));
        typeImage = image.getJSONObject(2);
        answer.setImagelarge(typeImage.getString("#text"));
        typeImage = image.getJSONObject(3);
        answer.setImageextralarge(typeImage.getString("#text"));
        answer.setUrl(user.getString("url"));
        answer.setId(user.getString("id"));
        answer.setCountry(user.getString("country"));
        answer.setAge(user.getString("age"));
        answer.setGender(user.getString("gender"));
        answer.setSubscriber(user.getString("subscriber"));
        answer.setPlaycount(user.getString("playcount"));
        answer.setPlaylists(user.getString("playlists"));
        answer.setBootstrap(user.getString("bootstrap"));
        JSONObject registered = user.getJSONObject("registered");
        answer.setRegistered(registered.getString("#text"));
        return answer;
    }

    public UserInfoAnswer info() throws Exception {
        URLConnector http = new URLConnector();
        if (this.username.length() == 0) {
            UserInfoAnswer answer = new UserInfoAnswer();
            answer.setStatus(EMPTY_STRING);
            return answer;
        }
        String response;
        try {
            response = http.sendPost(SecretData.ROOT, "method=user.getInfo&format=json" + "&api_key=" + SecretData.KEY + "&user=" + this.username);
        } catch (UnknownHostException e) {
            response = CONNECTION_ERROR;
        }
        return parse(response);
    }
}
