package com.dreamteam.androidproject.api; /**
 * Created by nap on 10/27/2014.
 */
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

public class Auth extends User {
    private String password;
    private String sign;

    public Auth(String username, String password) {
        User.USERNAME = username;
        this.password = password;
        this.sign = DigestUtils.md5Hex("api_key" + SecretData.KEY + "methodauth.getMobileSession" + "password" + this.password + "username" + User.USERNAME + SecretData.SECRET);
    }

    @Override
    protected String parse(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        String status = getStatus(obj);
        if (!status.equals("ok")) {
            return status;
        }
        JSONObject session = obj.getJSONObject("session");
        User.USERKEY = session.getString("key");
        User.SUBSCRIBER = session.getInt("subscriber");
        return status;
    }

    public String auth() throws Exception {
        URLConnector http = new URLConnector();
        if (User.USERNAME.length() == 0 || this.password.length() == 0) {
            return "check data is correct!";
        }
        String response = http.sendPost(SecretData.ROOT, "method=auth.getMobileSession&format=json" + "&api_key=" + SecretData.KEY + "&username=" + User.USERNAME + "&password=" + this.password + "&api_sig=" + this.sign);
        return parse(response);
    }
}
