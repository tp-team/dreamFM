package com.dreamteam.androidproject.api;
/**
 * Created by nap on 10/30/2014.
 */
import org.json.JSONArray;
import org.json.JSONObject;

public class UserInfo extends User {
    @Override
    protected String parse(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        String status = getStatus(obj);
        if (!status.equals("ok")) {
            return status;
        }
        JSONObject user = obj.getJSONObject("user");
        User.REALNAME = user.getString("realname");
        JSONArray image = user.getJSONArray("image");
        JSONObject typeImage;
        typeImage = image.getJSONObject(0);
        User.IMAGESMALL = typeImage.getString("#text");
        typeImage = image.getJSONObject(1);
        User.IMAGEMEDIUM = typeImage.getString("#text");
        typeImage = image.getJSONObject(2);
        User.IMAGELARGE = typeImage.getString("#text");
        typeImage = image.getJSONObject(3);
        User.IMAGEEXTRALARGE = typeImage.getString("#text");
        User.URL = user.getString("url");
        User.ID = user.getString("id");
        User.COUNTRY = user.getString("country");
        User.AGE = user.getString("age");
        User.GENDER = user.getString("gender");
        User.SUBSCRIBER = user.getInt("subscriber");
        User.PLAYCOUNT = user.getInt("playcount");
        User.PLAYLISTS = user.getInt("playlists");
        User.BOOTSTRAP = user.getInt("bootstrap");
        JSONObject registered = user.getJSONObject("registered");
        User.REGISTERED = registered.getString("#text");
        return status;
    }

    public String info() throws Exception {
        URLConnector http = new URLConnector();
        if (User.USERNAME.length() == 0) {
            return "check data is correct!";
        }
        String response = http.sendPost(SecretData.ROOT, "method=user.getInfo&format=json" + "&api_key=" + SecretData.KEY + "&user=" + User.USERNAME);
        return parse(response);
    }
}
