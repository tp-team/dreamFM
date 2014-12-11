package com.dreamteam.androidproject.activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.dreamteam.androidproject.service.FMApplication;
import com.dreamteam.androidproject.service.ServiceCallbackListener;
import com.dreamteam.androidproject.service.ServiceHelper;

public abstract class BaseActivity extends FragmentActivity implements ServiceCallbackListener {

    private ServiceHelper serviceHelper;

    protected FMApplication getApp() {
        return (FMApplication) getApplication();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceHelper = getApp().getServiceHelper();
    }

    @Override
    protected void onResume() {
        super.onResume();
        serviceHelper.addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        serviceHelper.removeListener(this);
    }

    public ServiceHelper getServiceHelper() {
        return serviceHelper;
    }

    public void onServiceCallback(int requestId, Intent requestIntent, int resultCode, Bundle resultData) {
    }

}
