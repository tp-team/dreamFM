package com.dreamteam.androidproject.api;

/**
 * Created by nap on 10/27/2014.
 */
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

public class Auth extends ApiParent {
    private String password;
    private String sign;

    public Auth(String username, String password) {
        ApiParent.USERNAME = username;
        this.password = password;
        this.sign = DigestUtils.md5Hex("api_key" + SecretData.KEY + "methodauth.getMobileSession" + "password" + this.password + "username" + ApiParent.USERNAME + SecretData.SECRET);
    }

    @Override
    protected String parse(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        String error = getError(obj);
        if (error != "ok") {
            return error;
        }
        JSONObject session = obj.getJSONObject("session");
        ApiParent.USERKEY = (String) session.get("key");
        return error;
    }

    public String auth() throws Exception {
        URLConnector http = new URLConnector();
        if (ApiParent.USERNAME.length() == 0 || this.password.length() == 0) {
            return "check data is correct!";
        }
        String response = http.sendPost(SecretData.ROOT, "method=auth.getMobileSession&format=json" + "&api_key=" + SecretData.KEY + "&username=" + ApiParent.USERNAME + "&password=" + this.password + "&api_sig=" + this.sign);
        return parse(response);
    }
}
