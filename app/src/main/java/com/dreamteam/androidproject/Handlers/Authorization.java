package com.dreamteam.androidproject.Handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.query.Auth;

import java.security.NoSuchAlgorithmException;

public class Authorization extends BaseCommand {
    private String password;
    private String login;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {
            Auth auth = new Auth(login, password);
            bun = auth.auth().getBundelObject();
            notifySuccess(bun);
        } catch (Exception e) {
            bun = new Bundle();
            bun.putString(AuthAnswer.STATUS, "Error request");
            notifyFailure(bun);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(password);
    }

    public static final Parcelable.Creator<Authorization> CREATOR = new Parcelable.Creator<Authorization>() {
        public Authorization createFromParcel(Parcel in) {
            return new Authorization(in);
        }

        public Authorization[] newArray(int size) {
            return new Authorization[size];
        }
    };

    private Authorization(Parcel in) {
        login = in.readString();
        password = in.readString();
    }

    public Authorization(String login, String password) {
        this.password = password;
        this.login    = login;
    }

}
