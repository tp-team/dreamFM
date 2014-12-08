package com.dreamteam.androidproject.api.answer;

import android.os.Bundle;

import com.dreamteam.androidproject.api.answer.AlbumGetInfoAnswer;
import com.dreamteam.androidproject.api.template.ObjectList;

public class UserGetNewReleasesAnswer {
    public static String STATUS_NEW_RELEASES = "STATUS_NEW_RELEASES";
    public static String TEXT_STATUS         = "TEXT_STATUS";
    private String status;
    private String textStatus;
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

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }

    public Bundle getBundleObject() {
        Bundle bun = new Bundle();
        bun.putString(STATUS_NEW_RELEASES, status);
        bun.putString(TEXT_STATUS, textStatus);
        return bun;
    }
}
