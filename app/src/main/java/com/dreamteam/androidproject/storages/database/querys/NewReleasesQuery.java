package com.dreamteam.androidproject.storages.database.querys;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.dreamteam.androidproject.storages.database.DataBase;
import com.dreamteam.androidproject.storages.database.InfoDB;


public class NewReleasesQuery extends InfoDB {

    private DataBase table;
    private final Context ctx;

    public NewReleasesQuery(Context ctx) {
        this.ctx = ctx;
        table = new DataBase(this.ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        db = table.getWritableDatabase();
    }

    public void close() {
        if (table != null) table.close();
    }

    public void insert(String name, String mbid, String urlImg) {
        ContentValues cv = new ContentValues();
        cv.put(DataBase.NEW_RELEASES_COLUMN_NAME, name);
        cv.put(DataBase.NEW_RELEASES_COLUMN_MBID, mbid);
        cv.put(DataBase.NEW_RELEASES_COLUMN_URL_ULR, urlImg);
        db.insert(DataBase.NEW_RELEASES_NAME_TABLE, null, cv);
    }

    public Cursor getTable() {
        return db.query(DataBase.NEW_RELEASES_NAME_TABLE, null, null, null, null, null, null);
    }
}