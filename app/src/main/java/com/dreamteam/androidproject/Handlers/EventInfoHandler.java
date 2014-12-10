package com.dreamteam.androidproject.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.dreamteam.androidproject.api.answer.AuthAnswer;
import com.dreamteam.androidproject.api.answer.EventGetInfoAnswer;
import com.dreamteam.androidproject.api.query.Auth;
import com.dreamteam.androidproject.api.query.EventGetInfo;


public class EventInfoHandler extends BaseCommand {
    private String event;

    @Override
    protected void doExecute(Intent intent, Context context, ResultReceiver callback) {
        Bundle bun;
        try {
            EventGetInfo eventGet = new EventGetInfo(event);
            EventGetInfoAnswer answer = eventGet.info();

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
        parcel.writeString(event);
    }

    public static final Parcelable.Creator<EventInfoHandler> CREATOR = new Parcelable.Creator<EventInfoHandler>() {
        public EventInfoHandler createFromParcel(Parcel in) {
            return new EventInfoHandler(in);
        }

        public EventInfoHandler[] newArray(int size) {
            return new EventInfoHandler[size];
        }
    };

    private EventInfoHandler(Parcel in) {
        event = in.readString();
    }

    public EventInfoHandler(String event) {
        this.event = event;
    }

}
