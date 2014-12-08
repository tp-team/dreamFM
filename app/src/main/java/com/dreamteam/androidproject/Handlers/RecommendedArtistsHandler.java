package com.dreamteam.androidproject.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;

import com.dreamteam.androidproject.api.answer.ArtistGetInfoAnswer;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.answer.UserGetRecommendedArtistsAnswer;
import com.dreamteam.androidproject.api.query.UserGetRecommendedArtists;
import com.dreamteam.androidproject.api.template.ObjectList;

import com.dreamteam.androidproject.storages.database.DataBase;
import com.dreamteam.androidproject.storages.database.querys.RecommendedArtistsQuery;

public class RecommendedArtistsHandler extends BaseCommand {
    private String key;
    private String page;
    private String limit;
    private RecommendedArtistsQuery queryDB;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {

            UserGetRecommendedArtists recommend = new UserGetRecommendedArtists("", "10", key);
            UserGetRecommendedArtistsAnswer recommendAnswer = recommend.getRecomArtists();
            bun = recommendAnswer.getBundleObject();

            context.deleteDatabase("MY_DATABASE");

            queryDB = new RecommendedArtistsQuery(context);
            queryDB.open();

            ObjectList<ArtistGetInfoAnswer> list = recommendAnswer.getRecommendations();

            Log.d("TAG_DATABASE", Integer.toString(list.getLength()));

            for (int i = 0; i < list.getLength(); ++i) {
                ArtistGetInfoAnswer info = list.get(i);
                Log.d("TAAG_IIII", Integer.toString(i));
                Log.d("TAG_DATABASE", info.getName());
                Log.d("TAG_DATABASE", info.getImagelarge());
                queryDB.insert(info.getName(), info.getImagelarge());
            }
            queryDB.close();
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
        parcel.writeString(page);
        parcel.writeString(limit);
        parcel.writeString(key);
    }

    public static final Parcelable.Creator<RecommendedArtistsHandler> CREATOR = new Parcelable.Creator<RecommendedArtistsHandler>() {
        public RecommendedArtistsHandler createFromParcel(Parcel in) {
            return new RecommendedArtistsHandler(in);
        }

        public RecommendedArtistsHandler[] newArray(int size) {
            return new RecommendedArtistsHandler[size];
        }
    };

    private RecommendedArtistsHandler(Parcel in) {
        page = in.readString();
        limit = in.readString();
        key = in.readString();
    }

    public RecommendedArtistsHandler(String page, String limit, String key) {
        this.page = page;
        this.limit = limit;
        this.key = key;
    }

}