package com.dreamteam.androidproject.storages.database.querys;

import android.content.ContentValues;
import android.content.Context;
import com.dreamteam.androidproject.storages.database.DataBase;
import com.dreamteam.androidproject.storages.database.InfoDB;


public class SimilarQuery extends InfoDB {
    private DataBase table;
    private final Context ctx;

    public SimilarQuery(Context ctx) {
        this.ctx = ctx;
        table = new DataBase(this.ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        db = table.getWritableDatabase();
    }

    public void close() {
        if (table != null) table.close();
    }

    public long insert(long idArtist, long idSimilar) {
        ContentValues cv = new ContentValues();
        cv.put(DataBase.SIMILAR_COLUMN_ID_ARTIST, idArtist);
        cv.put(DataBase.SIMILAR_COLUMN_ID_SIMILAR, idSimilar);
        return db.insert(DataBase.ARTISTS_NAME_TABLE, null, cv);
    }


}
