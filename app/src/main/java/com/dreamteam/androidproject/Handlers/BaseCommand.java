package com.dreamteam.androidproject.Handlers;

import com.dreamteam.androidproject.service.FMApplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;

@SuppressLint("ParcelCreator")
public abstract class BaseCommand implements Parcelable {

    public static final int RESPONSE_SUCCESS = 0;

    public static final int RESPONSE_FAILURE = 1;

    private ResultReceiver Callback;

    protected volatile boolean cancelled = false;

    public final void execute(Intent intent, Context context, ResultReceiver callback) {
        this.Callback = callback;
        doExecute(intent, context, callback);
    }

    protected abstract void doExecute(Intent intent, Context context, ResultReceiver callback);

    protected void notifySuccess(Bundle data) {
        sendUpdate(RESPONSE_SUCCESS, data);
    }

    protected void notifyFailure(Bundle data) {
        sendUpdate(RESPONSE_FAILURE, data);
    }

    private void sendUpdate(int resultCode, Bundle data) {
        if (Callback != null) {
            Callback.send(resultCode, data);
        }
    }

    public synchronized void cancel() {
        cancelled = true;
    }

}