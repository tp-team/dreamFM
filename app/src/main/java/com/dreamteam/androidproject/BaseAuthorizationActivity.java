package com.dreamteam.androidproject;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

public abstract class BaseAuthorizationActivity extends Activity implements ServiceCallbackListener {

    private ServiceHelper serviceHelper;

    protected Application getApp() {
        return (Application) getApplication();
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
