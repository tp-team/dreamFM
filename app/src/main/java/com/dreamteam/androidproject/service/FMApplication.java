package com.dreamteam.androidproject.service;

import android.content.Context;

/**
 * Created by Pavel on 27.11.2014.
 */
public class FMApplication extends android.app.Application {

    public static final String PACKAGE = "com.dreamteam.androidproject";

    private ServiceHelper helperService;

    @Override
    public void onCreate() {
        super.onCreate();
        helperService = new ServiceHelper(this);
    }

    public ServiceHelper getServiceHelper() {
        return helperService;
    }

    public static FMApplication getApplication(Context context) {
        if (context instanceof FMApplication) {
            return (FMApplication) context;
        }
        return (FMApplication) context.getApplicationContext();
    }

}