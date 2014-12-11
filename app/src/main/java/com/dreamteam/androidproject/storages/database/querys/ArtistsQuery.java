package com.dreamteam.androidproject.storages.database.querys;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.dreamteam.androidproject.storages.database.DataBase;
import com.dreamteam.androidproject.storages.database.InfoDB;


public class ArtistsQuery extends InfoDB {
    private DataBase table;
    private final Context ctx;

    public ArtistsQuery(Context ctx) {
        this.ctx = ctx;
        table = new DataBase(this.ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        db = table.getWritableDatabase();
    }

    public void close() {
        if (table != null) table.close();
    }

    public long insert(String name, String urlImg, String streamable, boolean recommFlag,
                       String published, String summary, String content) {
        ContentValues cv = new ContentValues();
        cv.put(DataBase.ARTISTS_COLUMN_NAME, name);
        cv.put(DataBase.ARTISTS_COLUMN_URL_IMG, urlImg);
        cv.put(DataBase.ARTISTS_COLUMN_STREAMABLE, streamable);
        cv.put(DataBase.ARTISTS_RECOMMENDED_FLAG, recommFlag);
        cv.put(DataBase.ARTISTS_COLUMN_PUBLISHED, published);
        cv.put(DataBase.ARTISTS_COLUMN_SUMMARY, summary);
        cv.put(DataBase.ARTISTS_COLUMN_CONTENT, content);
        return db.insert(DataBase.ARTISTS_NAME_TABLE, null, cv);
    }

    public Cursor getRecommended() {
        String[] columns = {DataBase.COLUMN_ID, DataBase.ARTISTS_COLUMN_NAME, DataBase.ARTISTS_COLUMN_URL_IMG, DataBase.ARTISTS_RECOMMENDED_FLAG};
        String where = null;//DataBase.ARTISTS_RECOMMENDED_FLAG + " = ?";
        String[] selection_args = null;//{"true"};

        return db.query(DataBase.ARTISTS_NAME_TABLE, columns, where, selection_args, null, null, null);
    }

}
