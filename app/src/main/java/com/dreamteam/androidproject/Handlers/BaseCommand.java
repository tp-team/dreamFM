package com.dreamteam.androidproject.Handlers;

/**
 * Created by Pavel on 27.11.2014.
 */
import com.dreamteam.androidproject.services.Application;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;

@SuppressLint("ParcelCreator")
public abstract class BaseCommand implements Parcelable {

    public static String EXTRA_PROGRESS = Application.PACKAGE.concat(".EXTRA_PROGRESS");

    public static final int RESPONSE_SUCCESS = 0;

    public static final int RESPONSE_FAILURE = 1;

    public static final int RESPONSE_PROGRESS = 2;

    private ResultReceiver sfCallback;

    protected volatile boolean cancelled = false;

    public final void execute(Intent intent, Context context, ResultReceiver callback) {
        this.sfCallback = callback;
        doExecute(intent, context, callback);
    }

    protected abstract void doExecute(Intent intent, Context context, ResultReceiver callback);

    protected void notifySuccess(Bundle data) {
        sendUpdate(RESPONSE_SUCCESS, data);
    }

    protected void notifyFailure(Bundle data) {
        sendUpdate(RESPONSE_FAILURE, data);
    }

    protected void sendProgress(int progress) {
        Bundle b = new Bundle();
        b.putInt(EXTRA_PROGRESS, progress);

        sendUpdate(RESPONSE_PROGRESS, b);
    }

    private void sendUpdate(int resultCode, Bundle data) {
        if (sfCallback != null) {
            sfCallback.send(resultCode, data);
        }
    }

    public synchronized void cancel() {
        cancelled = true;
    }

}