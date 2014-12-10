package com.dreamteam.androidproject.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.answer.TrackGetInfoAnswer;
import com.dreamteam.androidproject.api.query.TrackGetInfo;


public class TrackInfoHandler extends BaseCommand {
    private String track;
    private String artist;
    private String username;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {
            TrackGetInfo trackGet = new TrackGetInfo(track, artist, username);
            TrackGetInfoAnswer answer = trackGet.info();

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
        parcel.writeString(track);
    }

    public static final Parcelable.Creator<TrackInfoHandler> CREATOR = new Parcelable.Creator<TrackInfoHandler>() {
        public TrackInfoHandler createFromParcel(Parcel in) {
            return new TrackInfoHandler(in);
        }

        public TrackInfoHandler[] newArray(int size) {
            return new TrackInfoHandler[size];
        }
    };

    private TrackInfoHandler(Parcel in) {
        username = in.readString();
        artist = in.readString();
        track = in.readString();
    }

    public TrackInfoHandler(String track, String artist, String username) {
        this.artist = artist;
        this.track = track;
        this.username = username;
    }

}
