package com.dreamteam.androidproject.newapi.answer;

import com.dreamteam.androidproject.newapi.template.ObjectList;

/**
 * Created by nap on 12/5/2014.
 */

public class UserGetNewReleasesAnswer {
    private String status;
    private ObjectList<AlbumGetInfoAnswer> albums = new ObjectList<AlbumGetInfoAnswer>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ObjectList<AlbumGetInfoAnswer> getAlbums() {
        return albums;
    }

    public void setAlbums(ObjectList<AlbumGetInfoAnswer> albums) {
        this.albums = albums;
    }
}
