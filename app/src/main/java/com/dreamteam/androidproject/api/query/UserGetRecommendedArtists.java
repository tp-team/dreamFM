package com.dreamteam.androidproject.api.query;

import android.util.Log;

import com.dreamteam.androidproject.api.answer.ArtistGetInfoAnswer;
import com.dreamteam.androidproject.api.answer.UserGetRecommendedArtistsAnswer;
import com.dreamteam.androidproject.newapi.connection.SecretData;
import com.dreamteam.androidproject.newapi.template.Common;
import com.dreamteam.androidproject.api.template.ObjectList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;

/**
 * Created by nap on 12/4/2014.
 */

public class UserGetRecommendedArtists extends Common {
    private String page;
    private String limit;
    private String sign;
    private String sessionKey;

    public UserGetRecommendedArtists(String page, String limit, String sessionKey) {
        this.page = page;
        this.limit = limit;
        this.sessionKey = sessionKey;
        if (this.limit.equals("0") || this.limit.equals("1")) {
            this.limit = "2";
        }
        if (this.page.equals("0")) {
            this.page = "1";
        }
        this.sign = strToMD5("api_key" + SecretData.KEY + "limit" +
                this.limit +"methoduser.getRecommendedArtists" + "page" +
                        this.page + "sk" + sessionKey + SecretData.SECRET);
    }

    @Override
    protected UserGetRecommendedArtistsAnswer parse(String str) throws JSONException {
        Log.d("RECOMMENDATIONS", str);
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserGetRecommendedArtistsAnswer answer = new UserGetRecommendedArtistsAnswer();
        answer.setStatus(errorToCode(status));
        answer.setTextStatus(status);
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject recommendations = obj.getJSONObject("recommendations");
        JSONArray list = recommendations.getJSONArray("artist");
        JSONArray image;
        JSONObject typeImage;
        ObjectList<ArtistGetInfoAnswer> artistsList = new ObjectList<ArtistGetInfoAnswer>();
        for (int i = 0; i < list.length(); i++) {
            ArtistGetInfoAnswer artistAnswer = new ArtistGetInfoAnswer();
            artistAnswer.setName(list.getJSONObject(i).getString("name"));
            artistAnswer.setMbid(list.getJSONObject(i).getString("mbid"));
            artistAnswer.setUrl(list.getJSONObject(i).getString("url"));
            image = list.getJSONObject(i).getJSONArray("image");
            typeImage = image.getJSONObject(0);
            artistAnswer.setImagesmall(typeImage.getString("#text"));
            typeImage = image.getJSONObject(1);
            artistAnswer.setImagemedium(typeImage.getString("#text"));
            typeImage = image.getJSONObject(2);
            artistAnswer.setImagelarge(typeImage.getString("#text"));
            typeImage = image.getJSONObject(3);
            artistAnswer.setImageextralarge(typeImage.getString("#text"));
            typeImage = image.getJSONObject(4);
            artistAnswer.setImagemega(typeImage.getString("#text"));
            artistAnswer.setStreamable(list.getJSONObject(i).getString("streamable"));
            artistsList.add(artistAnswer);
        }
        answer.setRecommendations(artistsList);
        return answer;
    }

    public UserGetRecommendedArtistsAnswer getRecomArtists() {
        if (this.sessionKey.length() == 0) {
            UserGetRecommendedArtistsAnswer answer = new UserGetRecommendedArtistsAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }
        String query = "method=user.getRecommendedArtists&format=json" + "&api_key=" + SecretData.KEY + "&page=" + this.page + "&limit=" + this.limit + "&api_sig=" + this.sign + "&sk=" + this.sessionKey;
        try {
            return parse(sendQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
