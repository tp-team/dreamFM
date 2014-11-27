package com.dreamteam.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import com.dreamteam.androidproject.service.FMApplication;
import com.dreamteam.androidproject.service.ServiceCallbackListener;
import com.dreamteam.androidproject.service.ServiceHelper;

public abstract class BaseAuthorizationActivity extends Activity implements ServiceCallbackListener {

    private ServiceHelper helperService;

    protected FMApplication getApp() {
        return (FMApplication) getApplication();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helperService = getApp().getServiceHelper();
    }

    @Override
    protected void onResume() {
        super.onResume();
        helperService.addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        helperService.removeListener(this);
    }

    public ServiceHelper getServiceHelper() {
        return helperService;
    }

    public void onServiceCallback(int requestId, Intent requestIntent, int resultCode, Bundle resultData) {
    }

}
