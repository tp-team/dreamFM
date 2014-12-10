package com.dreamteam.androidproject.newapi.query;

import com.dreamteam.androidproject.newapi.answer.AlbumGetInfoAnswer;
import com.dreamteam.androidproject.newapi.answer.UserGetNewReleasesAnswer;
import com.dreamteam.androidproject.newapi.connection.SecretData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.dreamteam.androidproject.newapi.template.Common;
import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/5/2014.
 */

public class UserGetNewReleases extends Common {
    private String user;

    public UserGetNewReleases(String user) {
        this.user = user;
    }

    @Override
    protected UserGetNewReleasesAnswer parse(String str) throws JSONException {
        JSONObject obj = new JSONObject(str);
        String status = null;
        try {
            status = getStatus(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserGetNewReleasesAnswer answer = new UserGetNewReleasesAnswer();
        answer.setStatus(errorToCode(status));
        if (!status.equals("ok")) {
            return answer;
        }
        JSONObject releases = obj.getJSONObject("albums");
        JSONArray list = releases.getJSONArray("album");
        JSONArray image;
        JSONObject typeImage;
        JSONObject artistObj;
        ObjectList<AlbumGetInfoAnswer> albumsList = new ObjectList<AlbumGetInfoAnswer>();
        for (int i = 0; i < list.length(); i++) {
            AlbumGetInfoAnswer album = new AlbumGetInfoAnswer();
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
