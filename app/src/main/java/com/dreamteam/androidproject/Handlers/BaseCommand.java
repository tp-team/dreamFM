package com.dreamteam.androidproject.Handlers;

import com.dreamteam.androidproject.service.FMApplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;

import java.security.NoSuchAlgorithmException;

@SuppressLint("ParcelCreator")
public abstract class BaseCommand implements Parcelable {

    public static final int RESPONSE_SUCCESS = 0;

    public static final int RESPONSE_FAILURE = 1;

    private ResultReceiver Callback;

    protected volatile boolean cancelled = false;

    public final void execute(Intent intent, Context context, ResultReceiver callback) {
        try {
            this.Callback = callback;
            doExecute(intent, context, callback);
        }
        catch (Exception e) {

        }
    }

    protected abstract void doExecute(Intent intent, Context context, ResultReceiver callback) throws NoSuchAlgorithmException;

    protected void notifySuccess(Bundle data) {
        Log.d("TAG_BASE  ", "SUC");
        sendUpdate(RESPONSE_SUCCESS, data);
    }

    protected void notifyFailure(Bundle data) {
        Log.d("TAG_BASE  ", "Fail");
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