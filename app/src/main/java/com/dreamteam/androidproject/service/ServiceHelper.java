package com.dreamteam.androidproject.service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.SparseArray;

import com.dreamteam.androidproject.Handlers.Authorization;
import com.dreamteam.androidproject.Handlers.BaseCommand;

public class ServiceHelper {

    private ArrayList<ServiceCallbackListener> currentListeners = new ArrayList<ServiceCallbackListener>();

    private AtomicInteger idCounter = new AtomicInteger();

    private SparseArray<Intent> pendingActivities = new SparseArray<Intent>();

    private Application application;

    ServiceHelper(Application app) {
        this.application = app;
    }

    public void addListener(ServiceCallbackListener currentListener) {
        currentListeners.add(currentListener);
    }

    public void removeListener(ServiceCallbackListener currentListener) {
        currentListeners.remove(currentListener);
    }

    // =========================================

    public int getAuthorization(String login, String password) {
        final int requestId = createId();

        Intent i = createIntent(application, new Authorization(login, password), requestId);
        return runRequest(requestId, i);
    }

    // =========================================

    public void cancelCommand(int requestId) {
        Intent i = new Intent(application, ServiceApi.class);
        i.setAction(ServiceApi.ACTION_CANCEL_COMMAND);
        i.putExtra(ServiceApi.EXTRA_REQUEST_ID, requestId);

        application.startService(i);
        pendingActivities.remove(requestId);
    }

    public boolean isPending(int requestId) {
        return pendingActivities.get(requestId) != null;
    }

    public boolean check(Intent intent, Class<? extends BaseCommand> clazz) {
        Parcelable commandExtra = intent.getParcelableExtra(ServiceApi.EXTRA_COMMAND);
        return commandExtra != null && commandExtra.getClass().equals(clazz);
    }

    private int createId() {
        return idCounter.getAndIncrement();
    }

    private int runRequest(final int requestId, Intent i) {
        pendingActivities.append(requestId, i);
        application.startService(i);
        return requestId;
    }

    private Intent createIntent(final Context context, BaseCommand command, final int requestId) {
        Intent i = new Intent(context, ServiceApi.class);
        i.setAction(ServiceApi.ACTION_EXECUTE_COMMAND);
        i.putExtra(ServiceApi.EXTRA_COMMAND, command);
        i.putExtra(ServiceApi.EXTRA_REQUEST_ID, requestId);
        i.putExtra(ServiceApi.EXTRA_STATUS_RECEIVER, new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                Intent originalIntent = pendingActivities.get(requestId);
                if (isPending(requestId)) {
                    if (resultCode != BaseCommand.RESPONSE_PROGRESS) {
                        pendingActivities.remove(requestId);
                    }

                    for (ServiceCallbackListener currentListener : currentListeners) {
                        if (currentListener != null) {
                            currentListener.onServiceCallback(requestId, originalIntent, resultCode, resultData);
                        }
                    }
                }
            }
        });

        return i;
    }

}
