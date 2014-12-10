package com.dreamteam.androidproject.newapi.query;

import com.dreamteam.androidproject.newapi.answer.ArtistGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.ArtistGetSimilarAnswer;
import com.dreamteam.androidproject.newapi.connection.SecretData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.dreamteam.androidproject.newapi.template.Common;
import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/10/2014.
 */

public class ArtistGetSimilar extends Common {
    private String limit;
    private String artist;

    public ArtistGetSimilar(String limit, String artist) {
        this.limit = limit;
        this.artist = artist;
    }

    @Override
    protected ArtistGetSimilarAnswer parse(String str) throws JSONException {
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArtistGetSimilarAnswer answer = new ArtistGetSimilarAnswer();
        answer.setStatus(errorToCode(status));
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject recommendations = obj.getJSONObject("similarartists");
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
            artistAnswer.setStreamable(list.getJSONObject(i).getString("streamable"));
            artistsList.add(artistAnswer);
        }
        answer.setSimilarartists(artistsList);
        return answer;
    }

    public ArtistGetSimilarAnswer getSimilar() {
        if (this.artist.length() == 0) {
            ArtistGetSimilarAnswer answer = new ArtistGetSimilarAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }
        if (this.limit.equals("0") || this.limit.equals("1")) {
            this.limit = "2";
        }
        String query = "method=artist.getSimilar&format=json" + "&api_key=" + SecretData.KEY + "&limit=" + this.limit + "&artist=" + this.artist;
        try {
            return parse(sendQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
