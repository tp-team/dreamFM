package com.dreamteam.androidproject;

import android.content.Intent;
import android.os.Bundle;

interface ServiceCallbackListener {
    void onServiceCallback(int requestId, Intent requestIntent, int resultCode, Bundle data);
}
