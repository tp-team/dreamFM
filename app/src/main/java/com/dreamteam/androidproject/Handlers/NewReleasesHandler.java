package com.dreamteam.androidproject.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;

import com.dreamteam.androidproject.api.answer.AlbumGetInfoAnswer;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.query.UserGetNewReleases;
import com.dreamteam.androidproject.api.answer.UserGetNewReleasesAnswer;
import com.dreamteam.androidproject.api.template.ObjectList;
import com.dreamteam.androidproject.storages.database.querys.NewReleasesQuery;


public class NewReleasesHandler extends BaseCommand {
    private String username;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {
            UserGetNewReleases newRel = new UserGetNewReleases(this.username);
            UserGetNewReleasesAnswer answer = newRel.getNewReleases();
            ObjectList<AlbumGetInfoAnswer> list = answer.getAlbums();

            this.setInDataBase(list, context);

            bun = answer.getBundleObject();

            notifySuccess(bun);
        } catch (Exception e) {
            bun = new Bundle();
            bun.putString(AuthAnswer.TEXT_STATUS, "Error request");
            notifyFailure(bun);
        }
    }

    public void setInDataBase(ObjectList<AlbumGetInfoAnswer> list, Context context) {
        NewReleasesQuery queryDB = new NewReleasesQuery(context);
        queryDB.open();

        for (int i = 0; i < list.getLength(); ++i) {
            AlbumGetInfoAnswer info = list.get(i);
            Log.d("TAAG_IIII", Integer.toString(i));
            Log.d("TAG_NEW_RELEASES", info.getName());
            Log.d("TAG_NEW_RELEASES", info.getMbid());
            Log.d("TAG_NEW_RELEASES", info.getImagelarge());
            queryDB.insert(info.getName(), info.getMbid(), info.getImagelarge());
        }
        queryDB.close();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
    }

    public static final Parcelable.Creator<NewReleasesHandler> CREATOR = new Parcelable.Creator<NewReleasesHandler>() {
        public NewReleasesHandler createFromParcel(Parcel in) {
            return new NewReleasesHandler(in);
        }

        public NewReleasesHandler[] newArray(int size) {
            return new NewReleasesHandler[size];
        }
    };

    private NewReleasesHandler(Parcel in) {
        username = in.readString();
    }

    public NewReleasesHandler(String username) {
        this.username = username;
    }
}
