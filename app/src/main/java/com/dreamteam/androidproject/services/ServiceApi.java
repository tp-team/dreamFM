package com.dreamteam.androidproject.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.dreamteam.androidproject.api.Auth;
import com.dreamteam.androidproject.messagesSystem.AuthorizationMessage;
import com.dreamteam.androidproject.messagesSystem.BusProvider;
import com.squareup.otto.Bus;

import java.security.NoSuchAlgorithmException;

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
                try {
                    Auth auth = new Auth(USERNAME, PASSWORD);
                    Log.d("TAG", "IN SERVICE2");
                    result = auth.auth();
                    Log.d("TAG", result);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                stopSelf();
            }
        }).start();
    }

}
