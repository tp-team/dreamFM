package com.dreamteam.androidproject.api.query;

import android.util.Log;

import com.dreamteam.androidproject.api.answer.AlbumGetInfoAnswer;
import com.dreamteam.androidproject.api.answer.UserGetNewReleasesAnswer;
import com.dreamteam.androidproject.api.connection.SecretData;
import com.dreamteam.androidproject.api.template.Common;
import com.dreamteam.androidproject.api.template.ObjectList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserGetNewReleases extends Common {
    private String user;

    public UserGetNewReleases(String user) {
        this.user = user;
    }

    @Override
    protected UserGetNewReleasesAnswer parse(String str) throws Exception {
        Log.d("NEW RELEASES", str);
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserGetNewReleasesAnswer answer = new UserGetNewReleasesAnswer();
        answer.setStatus(errorToCode(status));
        answer.setTextStatus(status);
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject releases = obj.getJSONObject("albums");
        JSONArray list;
        try {
            list = releases.getJSONArray("album");
        } catch (JSONException e) {
            list = new JSONArray();
            Log.d("NEW RELEASES", "ZZZ");
            list.put(0, releases.getJSONObject("album"));
        }
        JSONArray image;
        JSONObject typeImage;
        AlbumGetInfoAnswer album = new AlbumGetInfoAnswer();
        JSONObject artistObj;
        ObjectList<AlbumGetInfoAnswer> albumsList = new ObjectList<AlbumGetInfoAnswer>();
        for (int i = 0; i < list.length(); i++) {
            album.setName(list.getJSONObject(i).getString("name"));
            album.setMbid(list.getJSONObject(i).getString("mbid"));
            album.setUrl(list.getJSONObject(i).getString("url"));
            artistObj = list.getJSONObject(i).getJSONObject("artist");
            album.setArtist(artistObj.getString("name"));
            image = list.getJSONObject(i).getJSONArray("image");
            typeImage = image.getJSONObject(0);
            album.setImagesmall(typeImage.getString("#text"));
            typeImage = image.getJSONObject(1);
            album.setImagemedium(typeImage.getString("#text"));
            typeImage = image.getJSONObject(2);
            album.setImagelarge(typeImage.getString("#text"));
            albumsList.add(album);
        }
        answer.setAlbums(albumsList);
        return answer;
    }

    public UserGetNewReleasesAnswer getNewReleases() {
        if (this.user.length() == 0) {
            UserGetNewReleasesAnswer answer = new UserGetNewReleasesAnswer();
            answer.setStatus(errorToCode(EMPTY_STRING));
            return answer;
        }
        String query = "method=user.getNewReleases&format=json" + "&api_key=" + SecretData.KEY + "&user=" + this.user;
        try {
            return parse(sendQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
