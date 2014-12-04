package com.dreamteam.androidproject.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.query.UserInfo;


public class UserInfoHandler extends BaseCommand {
    private String username;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {
            UserInfo info = new UserInfo(username);
            bun = info.info().getBundleObject();
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
    }

    public static final Parcelable.Creator<UserInfoHandler> CREATOR = new Parcelable.Creator<UserInfoHandler>() {
        public UserInfoHandler createFromParcel(Parcel in) {
            return new UserInfoHandler(in);
        }

        public UserInfoHandler[] newArray(int size) {
            return new UserInfoHandler[size];
        }
    };

    private UserInfoHandler(Parcel in) {
        username = in.readString();
    }

    public UserInfoHandler(String username) {
        this.username = username;
    }

}