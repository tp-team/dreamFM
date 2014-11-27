package com.dreamteam.androidproject.service;

import android.content.Intent;
import android.os.Bundle;


/**
 * Created by Pavel on 27.11.2014.
 */




public interface ServiceCallbackListener {

    void onServiceCallback(int requestId, Intent requestIntent, int resultCode, Bundle data);

}
