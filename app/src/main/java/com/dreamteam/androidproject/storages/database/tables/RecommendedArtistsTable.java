package com.dreamteam.androidproject.storages.database.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.DatabaseMetaData;


public class RecommendedArtistsTable extends SQLiteOpenHelper {
    public static final String NAME_TABLE     = "RECOMMENDED_ARTISTS";
    public static final String COLUMN_NAME    = "ARTIST_NAME";
    public static final String COLUMN_ULR_IMG = "URL_IMG";

    private String CREATE_SQL;

    public RecommendedArtistsTable(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DATABASE_____", NAME_TABLE);
        this.CREATE_SQL = "create table " + RecommendedArtistsTable.NAME_TABLE + " ("
                + "id integer primary key autoincrement,"
                + RecommendedArtistsTable.COLUMN_NAME + " text,"
                + RecommendedArtistsTable.COLUMN_ULR_IMG + " text" + ");";

        db.execSQL(this.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
