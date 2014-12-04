package com.dreamteam.androidproject.storages;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesSystem {

    private static final String APP_SHARED_PREFS = PreferencesSystem.class.getSimpleName(); //  Name of the file -.xml
    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;

    public PreferencesSystem(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public void setText(String key, String value) {
        this._prefsEditor.putString(key, value);
        this._prefsEditor.commit();
    }

    public String getText(String key) {
        return this._sharedPrefs.getString(key, "");
    }
}
