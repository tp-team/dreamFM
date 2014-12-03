package com.dreamteam.androidproject.service;

import android.content.Intent;
import android.os.Bundle;


public interface ServiceCallbackListener {

    void onServiceCallback(int requestId, Intent requestIntent, int resultCode, Bundle data);

}
