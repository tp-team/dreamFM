package com.dreamteam.androidproject.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

import com.dreamteam.androidproject.api.answer.AlbumGetInfoAnswer;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.query.AlbumGetInfo;


public class AlbumInfoHandler extends BaseCommand {
    private String artist;
    private String album;
    private String username;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {
            AlbumGetInfo albumGet = new AlbumGetInfo(artist, album, username);
            AlbumGetInfoAnswer answer = albumGet.info();

            /* база */


            bun = answer.getBundleObject();
            notifySuccess(bun);
        } catch (Exception e) {
            bun = new Bundle();
            bun.putString(AuthAnswer.TEXT_STATUS, "Error request");
            notifyFailure(bun);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(artist);
        parcel.writeString(album);
    }

    public static final Parcelable.Creator<AlbumInfoHandler> CREATOR = new Parcelable.Creator<AlbumInfoHandler>() {
        public AlbumInfoHandler createFromParcel(Parcel in) {
            return new AlbumInfoHandler(in);
        }

        public AlbumInfoHandler[] newArray(int size) {
            return new AlbumInfoHandler[size];
        }
    };

    private AlbumInfoHandler(Parcel in) {
        username = in.readString();
        artist = in.readString();
        album = in.readString();
    }

    public AlbumInfoHandler(String username, String artist, String album) {
        this.album = album;
        this.artist = artist;
        this.username = username;
    }

}