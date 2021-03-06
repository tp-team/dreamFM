package com.dreamteam.androidproject.newapi.query;

import com.dreamteam.androidproject.newapi.answer.AlbumGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.TagGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.TrackGetInfoAnswer;
import com.dreamteam.androidproject.newapi.connection.SecretData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.dreamteam.androidproject.newapi.template.Common;
import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/5/2014.
 */

public class AlbumGetInfo extends Common {
    private String artist;
    private String album;
    private String username;

    public AlbumGetInfo(String artist, String album, String username) {
        this.artist = artist;
        this.album = album;
        this.username = username;
    }

    @Override
    protected AlbumGetInfoAnswer parse(String str) throws JSONException {
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AlbumGetInfoAnswer answer = new AlbumGetInfoAnswer();
        answer.setStatus(errorToCode(status));
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject album = obj.getJSONObject("album");
        answer.setName(album.getString("name"));
        answer.setArtist(album.getString("artist"));
        answer.setId(album.getString("id"));
        answer.setMbid(album.getString("mbid"));
        answer.setUrl(album.getString("url"));
        answer.setReleasedate(album.getString("releasedate"));
        JSONArray image = album.getJSONArray("image");
        JSONObject typeImage;
        typeImage = image.getJSONObject(0);
        answer.setImagesmall(typeImage.getString("#text"));
        typeImage = image.getJSONObject(1);
        answer.setImagemedium(typeImage.getString("#text"));
        typeImage = image.getJSONObject(2);
        answer.setImagelarge(typeImage.getString("#text"));
        answer.setListeners(album.getString("listeners"));
        answer.setPlaycount(album.getString("playcount"));
        JSONObject tags = album.getJSONObject("toptags");
        JSONArray tag = tags.getJSONArray("tag");
        TagGetInfoAnswer tagAnswer = new TagGetInfoAnswer();
        ObjectList<TagGetInfoAnswer> tagsList = new ObjectList<TagGetInfoAnswer>();
        for (int i = 0; i < tags.length(); i++) {
            tagAnswer.setName(tag.getJSONObject(i).getString("name"));
            tagAnswer.setUrl(tag.getJSONObject(i).getString("url"));
            tagsList.add(tagAnswer);
        }
        answer.setToptags(tagsList);
        JSONObject trackList = album.getJSONObject("tracks");
        JSONArray track = trackList.getJSONArray("track");
        TrackGetInfoAnswer trackAnswer = new TrackGetInfoAnswer();
        ObjectList<TrackGetInfoAnswer> tracksList = new ObjectList<TrackGetInfoAnswer>();
        for (int i = 0; i < trackList.length(); i++) {
            trackAnswer.setName(track.getJSONObject(i).getString("name"));
            trackAnswer.setDuration(track.getJSONObject(i).getString("duration"));
            trackAnswer.setMbid(track.getJSONObject(i).getString("mbid"));
            JSONObject streamble = track.getJSONObject(i).getJSONObject("streamable");
            trackAnswer.setStreamable(streamble.getString("#text"));
            JSONObject artist = track.getJSONObject(i).getJSONObject("artist");
            trackAnswer.setName(artist.getString("name"));
            trackAnswer.setMbid(artist.getString("mbid"));
            trackAnswer.setUrl(artist.getString("url"));
            tracksList.add(trackAnswer);
        }
        answer.setTracks(tracksList);
        return answer;
    }

    public AlbumGetInfoAnswer info() {
        if (this.artist.length() == 0 || this.username.length() == 0) {
            AlbumGetInfoAnswer answer = new AlbumGetInfoAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }
        String query = "method=album.getInfo&format=json" + "&artist=" + this.artist + "&album=" + this.album + "&api_key=" + SecretData.KEY + "&username=" + this.username;
        try {
            return parse(sendQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
