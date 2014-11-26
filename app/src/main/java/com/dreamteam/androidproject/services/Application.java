package com.dreamteam.androidproject.services;

import android.content.Context;

/**
 * Created by Pavel on 27.11.2014.
 */
public class Application extends android.app.Application {

    public static final String PACKAGE = "android_team";

    private HelperService helperService;

    @Override
    public void onCreate() {
        super.onCreate();
        helperService = new HelperService(this);
    }

    public HelperService getHelperService() {
        return helperService;
    }

    public static Application getApplication(Context context) {
        if (context instanceof Application) {
            return (Application) context;
        }
        return (Application) context.getApplicationContext();
    }

}