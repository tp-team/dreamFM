package com.dreamteam.androidproject.api;

/**
 * Created by nap on 10/30/2014.
 */
import org.json.JSONArray;
import org.json.JSONObject;

public class UserInfo extends ApiParent {
    @Override
    protected String parse(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        String error = getError(obj);
        if (error != "ok") {
            return error;
        }
        JSONObject user = obj.getJSONObject("user");
        ApiParent.REALNAME = (String) user.get("realname");
        JSONArray image = obj.getJSONArray("image");
//        JSONObject small = (String) image.get(0);
        ApiParent.IMAGEMEDIUM = (String) image.get(1);
        ApiParent.IMAGELARGE = (String) image.get(2);
        ApiParent.IMAGEEXTRALARGE = (String) image.get(3);
        return error;
    }

    public String info() throws Exception {
        URLConnector http = new URLConnector();
        if (ApiParent.USERNAME.length() == 0) {
            return "check data is correct!";
        }
        String response = http.sendPost(SecretData.ROOT, "method=user.getInfo&format=json" + "&api_key=" + SecretData.KEY + "&user=" + ApiParent.USERNAME);
        return parse(response);
    }
}
