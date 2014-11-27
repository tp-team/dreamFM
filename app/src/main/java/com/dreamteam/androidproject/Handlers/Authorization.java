package com.dreamteam.androidproject.Handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.ResultReceiver;

public class Authorization extends BaseCommand {
    private String password;
    private String login;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    public Authorization(String login, String password) {
        this.password = password;
        this.login    = login;
    }

}
