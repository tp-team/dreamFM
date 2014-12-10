package com.dreamteam.androidproject.storages.database.querys;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.dreamteam.androidproject.storages.database.DataBase;
import com.dreamteam.androidproject.storages.database.InfoDB;


public class RecommendedArtistsQuery extends InfoDB {

    private DataBase table;
    private final Context ctx;

    public RecommendedArtistsQuery(Context ctx) {
        this.ctx = ctx;
        table = new DataBase(this.ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        db = table.getWritableDatabase();
    }

    public void close() {
        if (table != null) table.close();
    }

    public void insert(String name, String urlImg, String streamable) {
        ContentValues cv = new ContentValues();
        cv.put(DataBase.RECOMMEND_COLUMN_NAME, name);
        cv.put(DataBase.RECOMMEND_COLUMN_URL_IMG, urlImg);
        cv.put(DataBase.RECOMMEND_COLUMN_STREAMABLE, streamable);
        db.insert(DataBase.RECOMMEND_NAME_TABLE, null, cv);
    }

    public Cursor getTable() {
        return db.query(DataBase.RECOMMEND_NAME_TABLE, null, null, null, null, null, null);
    }

}
