package com.dreamteam.androidproject.storages.database.querys;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.dreamteam.androidproject.storages.database.DataBase;
import com.dreamteam.androidproject.storages.database.tables.RecommendedArtistsTable;


public class RecommendedArtistsQuery extends DataBase {

    private RecommendedArtistsTable table;
    private final Context ctx;

    public RecommendedArtistsQuery(Context ctx) {
        this.ctx = ctx;
        table = new RecommendedArtistsTable(this.ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        db = table.getWritableDatabase();
    }

    public void close() {
        if (table != null) table.close();
    }

    public void insert(String name, String ulrImg) {
        ContentValues cv = new ContentValues();
        cv.put(RecommendedArtistsTable.COLUMN_NAME, name);
        cv.put(RecommendedArtistsTable.COLUMN_ULR_IMG, ulrImg);
        db.insert(RecommendedArtistsTable.NAME_TABLE, null, cv);
    }

    public Cursor getTable() {
        return db.query(RecommendedArtistsTable.NAME_TABLE, null, null, null, null, null, null);
    }

}
