package com.dreamteam.androidproject.newapi.query;

import com.dreamteam.androidproject.newapi.answer.ArtistGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.TagGetInfoAnswer;
import com.dreamteam.androidproject.newapi.connection.SecretData;
import org.json.JSONArray;
import org.json.JSONObject;
import com.dreamteam.androidproject.newapi.template.Common;
import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/4/2014.
 */

public class ArtistGetInfo extends Common {
    private String artist;
    private String username;

    public ArtistGetInfo(String artist, String username) {
        this.artist = artist;
        this.username = username;
    }

    @Override
    protected ArtistGetInfoAnswer parse(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArtistGetInfoAnswer answer = new ArtistGetInfoAnswer();
        answer.setStatus(errorToCode(status));
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject artist = obj.getJSONObject("artist");
        answer.setName(artist.getString("name"));
        answer.setMbid(artist.getString("mbid"));
        answer.setUrl(artist.getString("url"));
        JSONArray image = artist.getJSONArray("image");
        JSONObject typeImage;
        typeImage = image.getJSONObject(0);
        answer.setImagesmall(typeImage.getString("#text"));
        typeImage = image.getJSONObject(1);
        answer.setImagemedium(typeImage.getString("#text"));
        typeImage = image.getJSONObject(2);
        answer.setImagelarge(typeImage.getString("#text"));
        answer.setStreamable(artist.getString("streamable"));
        JSONObject stats = artist.getJSONObject("stats");
        answer.setListeners(stats.getString("listeners"));
        answer.setPlays(stats.getString("playcount"));
        JSONObject similar = artist.getJSONObject("similar");
        JSONArray artistList = similar.getJSONArray("artist");
        ArtistGetInfoAnswer artistAnswer = new ArtistGetInfoAnswer();
        ObjectList<ArtistGetInfoAnswer> artistsList = new ObjectList<ArtistGetInfoAnswer>();
        for (int i = 0; i < artistList.length(); i++) {
            artistAnswer.setName(artistList.getJSONObject(i).getString("name"));
            artistAnswer.setUrl(artistList.getJSONObject(i).getString("url"));
            image = artistList.getJSONObject(i).getJSONArray("image");
            typeImage = image.getJSONObject(0);
            artistAnswer.setImagesmall(typeImage.getString("#text"));
            typeImage = image.getJSONObject(1);
            artistAnswer.setImagemedium(typeImage.getString("#text"));
            typeImage = image.getJSONObject(2);
            artistAnswer.setImagelarge(typeImage.getString("#text"));
            artistsList.add(artistAnswer);
        }
        answer.setSimilar(artistsList);
        JSONObject tags = artist.getJSONObject("tags");
        JSONArray tag = tags.getJSONArray("tag");
        TagGetInfoAnswer tagAnswer = new TagGetInfoAnswer();
        ObjectList<TagGetInfoAnswer> tagsList = new ObjectList<TagGetInfoAnswer>();
        for (int i = 0; i < tags.length(); i++) {
            tagAnswer.setName(tag.getJSONObject(i).getString("name"));
            tagAnswer.setName(tag.getJSONObject(i).getString("url"));
            tagsList.add(tagAnswer);
        }
        answer.setTags(tagsList);
        JSONObject bio = artist.getJSONObject("bio");
        answer.setPublished(bio.getString("published"));
        answer.setSummary(bio.getString("summary"));
        answer.setContent(bio.getString("content"));
        return answer;
    }

    public ArtistGetInfoAnswer info() {
        if (this.artist.length() == 0 || this.username.length() == 0) {
            ArtistGetInfoAnswer answer = new ArtistGetInfoAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }
        String query = "method=artist.getInfo&format=json" + "&artist=" + this.artist + "&api_key=" + SecretData.KEY + "&username=" + this.username;
        try {
            return parse(sendQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
