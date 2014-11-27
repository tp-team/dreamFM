package com.dreamteam.androidproject.service;

import android.content.Context;

public class FMApplication extends android.app.Application {

    public static final String PACKAGE = "com.dreamteam.androidproject";

    private ServiceHelper serviceHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        serviceHelper = new ServiceHelper(this);
    }

    public ServiceHelper getServiceHelper() {
        return serviceHelper;
    }

    public static FMApplication getApplication(Context context) {
        if (context instanceof FMApplication) {
            return (FMApplication) context;
        }
        return (FMApplication) context.getApplicationContext();
    }

}