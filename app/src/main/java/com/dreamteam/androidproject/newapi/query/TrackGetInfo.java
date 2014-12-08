package com.dreamteam.androidproject.newapi.query;

import com.dreamteam.androidproject.newapi.answer.AlbumGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.ArtistGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.TagGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.TrackGetInfoAnswer;
import com.dreamteam.androidproject.newapi.connection.SecretData;
import org.json.JSONArray;
import org.json.JSONObject;
import com.dreamteam.androidproject.newapi.template.Common;
import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/5/2014.
 */

public class TrackGetInfo extends Common {
    private String track;
    private String artist;
    private String username;

    public TrackGetInfo(String track, String artist, String username) {
        this.track = track;
        this.artist = artist;
        this.username = username;
    }

    @Override
    protected TrackGetInfoAnswer parse(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TrackGetInfoAnswer answer = new TrackGetInfoAnswer();
        answer.setStatus(errorToCode(status));
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject track = obj.getJSONObject("track");
        answer.setId(track.getString("id"));
        answer.setName(track.getString("name"));
        answer.setMbid(track.getString("mbid"));
        answer.setUrl(track.getString("url"));
        answer.setDuration(track.getString("duration"));
        JSONObject streamable = track.getJSONObject("streamable");
        answer.setStreamable(streamable.getString("#text"));
        answer.setListeners(track.getString("listeners"));
        answer.setPlaycount(track.getString("playcount"));
        JSONObject artistObj = track.getJSONObject("artist");
        ArtistGetInfoAnswer artist = new ArtistGetInfoAnswer();
        artist.setName(artistObj.getString("name"));
        artist.setMbid(artistObj.getString("mbid"));
        artist.setUrl(artistObj.getString("url"));
        answer.setArtist(artist);
        JSONObject albumObj = track.getJSONObject("album");
        AlbumGetInfoAnswer album = new AlbumGetInfoAnswer();
        album.setArtist(albumObj.getString("artist"));
        album.setName(albumObj.getString("title"));
        album.setMbid(albumObj.getString("mbid"));
        album.setUrl(albumObj.getString("url"));
        JSONArray image = albumObj.getJSONArray("image");
        JSONObject typeImage;
        typeImage = image.getJSONObject(0);
        album.setImagesmall(typeImage.getString("#text"));
        typeImage = image.getJSONObject(1);
        album.setImagemedium(typeImage.getString("#text"));
        typeImage = image.getJSONObject(2);
        album.setImagelarge(typeImage.getString("#text"));
        answer.setAlbum(album);
        JSONObject tags = track.getJSONObject("toptags");
        JSONArray tag = tags.getJSONArray("tag");
        TagGetInfoAnswer tagAnswer = new TagGetInfoAnswer();
        ObjectList<TagGetInfoAnswer> tagsList = new ObjectList<TagGetInfoAnswer>();
        for (int i = 0; i < tags.length(); i++) {
            tagAnswer.setName(tag.getJSONObject(i).getString("name"));
            tagAnswer.setUrl(tag.getJSONObject(i).getString("url"));
            tagsList.add(tagAnswer);
        }
        answer.setTags(tagsList);
        JSONObject wiki = track.getJSONObject("wiki");
        answer.setPublished(wiki.getString("published"));
        answer.setSummary(wiki.getString("summary"));
        answer.setContent(wiki.getString("content"));
        return answer;
    }

    public TrackGetInfoAnswer info() {
        if (this.track.length() == 0 || this.artist.length() == 0 || this.username.length() == 0) {
            TrackGetInfoAnswer answer = new TrackGetInfoAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }
        String query = "method=track.getInfo&format=json" + "&track=" + this.track + "&artist=" + this.artist + "&api_key=" + SecretData.KEY + "&username=" + this.username;
        try {
            return parse(sendQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
