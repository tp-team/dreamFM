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
import android.util.Log;
import android.util.SparseArray;

import com.dreamteam.androidproject.api.query.UserGetNewReleases;
import com.dreamteam.androidproject.handlers.AlbumInfoHandler;
import com.dreamteam.androidproject.handlers.ArtistInfoHandler;
import com.dreamteam.androidproject.handlers.AuthorizationHandler;
import com.dreamteam.androidproject.handlers.BaseCommand;
import com.dreamteam.androidproject.handlers.EventInfoHandler;
import com.dreamteam.androidproject.handlers.NewReleasesHandler;
import com.dreamteam.androidproject.handlers.RecommendedArtistsHandler;
import com.dreamteam.androidproject.handlers.TrackInfoHandler;
import com.dreamteam.androidproject.handlers.UserInfoHandler;


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

    public int getAuthorization(String username, String password) {
        int requestId = createId();
        Intent i = createIntent(application, new AuthorizationHandler(username, password), requestId);
        return runRequest(requestId, i);
    }

    public int getUserInfo(String username) {
        int requestId = createId();
        Intent i = createIntent(application, new UserInfoHandler(username), requestId);
        return runRequest(requestId, i);
    }

    public int getRecommendedArtists(String page, String limit, String key) {
        int requestId = createId();
        Intent i = createIntent(application, new RecommendedArtistsHandler(page, limit, key), requestId);
        return runRequest(requestId, i);
    }

    public int getNewReleases(String username) {
        int requestId = createId();
        Intent i = createIntent(application, new NewReleasesHandler(username), requestId);
        return runRequest(requestId, i);
    }

    public int getArtistInfo(String username, String artist) {
        int requestId = createId();
        Intent i = createIntent(application, new ArtistInfoHandler(username, artist), requestId);
        return runRequest(requestId, i);
    }

    public int getAlbumInfo(String artist, String album, String username) {
        int requestId = createId();
        Intent i = createIntent(application, new AlbumInfoHandler(artist, album, username), requestId);
        return runRequest(requestId, i);
    }

    public int getTrackInfo(String track, String artist, String username) {
        int requestId = createId();
        Intent i = createIntent(application, new TrackInfoHandler(track, artist, username), requestId);
        return runRequest(requestId, i);
    }

    public int getEventInfo(String event) {
        int requestId = createId();
        Intent i = createIntent(application, new EventInfoHandler(event), requestId);
        return runRequest(requestId, i);
    }


    // =========================================

//    public void cancelCommand(int requestId) {
//        Intent i = new Intent(application, ServiceApi.class);
//        i.setAction(ServiceApi.ACTION_CANCEL_COMMAND);
//        i.putExtra(ServiceApi.EXTRA_REQUEST_ID, requestId);
//
//        application.startService(i);
//        pendingActivities.remove(requestId);
//    }

    public boolean isPending(int requestId) {
        return pendingActivities.get(requestId) != null;
    }

//    public boolean check(Intent intent, Class<? extends BaseCommand> clazz) {
//        Parcelable commandExtra = intent.getParcelableExtra(ServiceApi.EXTRA_COMMAND);
//        return commandExtra != null && commandExtra.getClass().equals(clazz);
//    }

    private int createId() {
        return idCounter.getAndIncrement();
    }

    private int runRequest(final int requestId, Intent i) {
        pendingActivities.append(requestId, i);
        application.startService(i);
        return requestId;
    }

    private Intent createIntent(final Context context, BaseCommand command, final int requestId) {
        Log.d("TAG_SERVICE_HELPER", "IN SERVICE_HELPER");
        Intent i = new Intent(context, ServiceApi.class);
        i.setAction(ServiceApi.ACTION_EXECUTE_COMMAND);
        i.putExtra(ServiceApi.EXTRA_COMMAND, command);
        i.putExtra(ServiceApi.EXTRA_REQUEST_ID, requestId);
        i.putExtra(ServiceApi.EXTRA_STATUS_RECEIVER, new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                Intent originalIntent = pendingActivities.get(requestId);
                if (isPending(requestId)) {
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

