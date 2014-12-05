package com.dreamteam.androidproject.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.answer.UserGetRecommendedArtistsAnswer;
import com.dreamteam.androidproject.api.query.UserGetRecommendedArtists;

public class RecommendedArtistsHandler extends BaseCommand {
    private String key;
    private String page;
    private String limit;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {
            UserGetRecommendedArtists recomm = new UserGetRecommendedArtists(page, limit, key);
            UserGetRecommendedArtistsAnswer recommAnswer = recomm.getRecomArtists();
            bun = recommAnswer.getBundleObject();

            /* здесь будим писать в базу */

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
        parcel.writeString(key);
        parcel.writeString(page);
        parcel.writeString(limit);
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