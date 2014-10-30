package com.dreamteam.androidproject.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.dreamteam.androidproject.api.Auth;
import com.dreamteam.androidproject.messagesSystem.AuthorizationMessage;
import com.dreamteam.androidproject.messagesSystem.BusProvider;
import com.squareup.otto.Bus;

public class ServiceApi extends Service {
    private Bus BUS = new BusProvider().getInstance();
    private String USERNAME;
    private String PASSWORD;
    private String result;

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("INTENT!!!", intent.toString());
        USERNAME = intent.getStringExtra("USERNAME");
        PASSWORD = intent.getStringExtra("PASSWORD");
        Log.d("TAG", "IN SERVICE");
        isAuthorisation();
        BUS.post(new AuthorizationMessage(result));

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void isAuthorisation() {
        new Thread(new Runnable() {
            public void run() {
                Auth auth = new Auth(USERNAME, PASSWORD);
                Log.d("TAG", "IN SERVICE2");
                try {
                    result = auth.auth();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("TAG", result);

                stopSelf();
            }
        }).start();
    }

}
