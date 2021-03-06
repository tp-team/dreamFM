package com.dreamteam.androidproject.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

import com.dreamteam.androidproject.api.answer.ArtistGetInfoAnswer;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.query.ArtistGetInfo;
import com.dreamteam.androidproject.api.query.Auth;


public class ArtistInfoHandler extends BaseCommand {
    private String artist;
    private String username;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {
            ArtistGetInfo artistGet = new ArtistGetInfo(username, artist);
            ArtistGetInfoAnswer answer = artistGet.info();

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
    }

    public static final Parcelable.Creator<ArtistInfoHandler> CREATOR = new Parcelable.Creator<ArtistInfoHandler>() {
        public ArtistInfoHandler createFromParcel(Parcel in) {
            return new ArtistInfoHandler(in);
        }

        public ArtistInfoHandler[] newArray(int size) {
            return new ArtistInfoHandler[size];
        }
    };

    private ArtistInfoHandler(Parcel in) {
        username = in.readString();
        artist = in.readString();
    }

    public ArtistInfoHandler(String username, String artist) {
        this.artist = artist;
        this.username = username;
    }
}
