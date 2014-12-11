package com.dreamteam.androidproject.newapi.query;

import com.dreamteam.androidproject.newapi.answer.ArtistGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.UserGetRecommendedArtistsAnswer;
import com.dreamteam.androidproject.newapi.connection.SecretData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.dreamteam.androidproject.newapi.template.Common;
import com.dreamteam.androidproject.newapi.template.ObjectList;

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
        if (this.limit.equals("0")) {
            this.limit = "1";
        }
        if (this.page.equals("0")) {
            this.page = "1";
        }
        this.sign = strToMD5("api_key" + SecretData.KEY + "limit" + this.limit +"methoduser.getRecommendedArtists" + "page" + this.page + "sk" + sessionKey + SecretData.SECRET);
    }

    @Override
    protected UserGetRecommendedArtistsAnswer parse(String str) throws JSONException {
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserGetRecommendedArtistsAnswer answer = new UserGetRecommendedArtistsAnswer();
        answer.setStatus(errorToCode(status));
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject recommendations = obj.getJSONObject("recommendations");
        JSONArray list;
        try {
            list = recommendations.getJSONArray("artist");
        } catch (JSONException e) {
            list = new JSONArray();
            list.put(0, recommendations.getJSONObject("artist"));
        }
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
