package com.dreamteam.androidproject.storages.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBase extends SQLiteOpenHelper {

    public static final String COLUMN_ID = "_id";

    public static final String RECOMMEND_NAME_TABLE     = "RECOMMENDED_ARTISTS";
    public static final String RECOMMEND_COLUMN_NAME    = "ARTIST_NAME";
    public static final String RECOMMEND_COLUMN_URL_IMG = "URL_IMG";
    private String RECOMMEND_CREATE = "create table " + RECOMMEND_NAME_TABLE + " ("
                                      + COLUMN_ID + " integer primary key autoincrement,"
                                      + RECOMMEND_COLUMN_NAME + " text,"
                                      + RECOMMEND_COLUMN_URL_IMG + " text" + ");";

    public static final String NEW_RELEASES_NAME_TABLE     = "NEW_RELEASES";
    public static final String NEW_RELEASES_COLUMN_NAME    = "NEW_RELEASES_NAME";
    public static final String NEW_RELEASES_COLUMN_MBID    = "NEW_RELEASES_MBID";
    public static final String NEW_RELEASES_COLUMN_URL_ULR = "NEW_RELEASES_URL_IMG";
    private String NEW_RELEASES_CREATE = "create table " + NEW_RELEASES_NAME_TABLE + " ("
                                         + COLUMN_ID + " integer primary key autoincrement,"
                                         + NEW_RELEASES_COLUMN_NAME  + " text,"
                                         + NEW_RELEASES_COLUMN_MBID  + " text,"
                                         + NEW_RELEASES_COLUMN_URL_ULR + " text" + ");";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RECOMMEND_CREATE);
        db.execSQL(NEW_RELEASES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {

    }
}
