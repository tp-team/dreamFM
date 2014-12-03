package com.dreamteam.androidproject.Handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

public class Authorization extends BaseCommand {
    private String password;
    private String login;
    private String TestPassword  = "admin";
    private String TestLogin     = "admin";
    public static String SUCCESS = "SUCCESS";
    public static String FAILURE = "FAILURE";

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle b = new Bundle();
        if (TestLogin.equals(login) && TestPassword.equals(password)) {
            b.putString(SUCCESS, SUCCESS);
            notifySuccess(b);
            
        } else {
            b.putString(FAILURE, FAILURE);
            notifyFailure(b);
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
