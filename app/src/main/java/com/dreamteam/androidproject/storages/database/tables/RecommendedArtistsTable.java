package com.dreamteam.androidproject.storages.database.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class RecommendedArtistsTable extends SQLiteOpenHelper {
    public static final String NAME_TABLE     = "RECOMMENDED_ARTISTS";
    public static final String COLUMN_NAME    = "NAME";
    public static final String COLUMN_ULR_IMG = "ULR_IMG";

    private final String CREATE_SQL = "create table" + NAME_TABLE + "("
            + "id integer primary key autoincrement,"
            + COLUMN_NAME + "text,"
            + COLUMN_ULR_IMG + "text" + ");";

    public RecommendedArtistsTable(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
