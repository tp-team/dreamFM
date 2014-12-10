package com.dreamteam.androidproject.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.answer.TagGetInfoAnswer;
import com.dreamteam.androidproject.api.query.TagGetInfo;


public class TagInfoHandler extends BaseCommand {
    private String tag;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {
            TagGetInfo tagGet = new TagGetInfo(tag);
            TagGetInfoAnswer answer = tagGet.info();

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
        parcel.writeString(tag);
    }

    public static final Parcelable.Creator<TagInfoHandler> CREATOR = new Parcelable.Creator<TagInfoHandler>() {
        public TagInfoHandler createFromParcel(Parcel in) {
            return new TagInfoHandler(in);
        }

        public TagInfoHandler[] newArray(int size) {
            return new TagInfoHandler[size];
        }
    };

    private TagInfoHandler(Parcel in) {
        tag = in.readString();
    }

    public TagInfoHandler(String tag) {
        this.tag = tag;
    }

}
