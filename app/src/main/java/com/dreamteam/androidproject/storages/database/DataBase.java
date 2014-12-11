package com.dreamteam.androidproject.storages.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBase extends SQLiteOpenHelper {

    public static final String COLUMN_ID = "_id";

    public static final String ARTISTS_NAME_TABLE        = "ARTISTS_TABLE";
    public static final String ARTISTS_COLUMN_NAME       = "ARTIST_NAME";
    public static final String ARTISTS_COLUMN_STREAMABLE = "RECOMMEND_COLUMN_STREAMABLE";
    public static final String ARTISTS_COLUMN_URL_IMG    = "URL_IMG";
    public static final String ARTISTS_COLUMN_PUBLISHED  = "ARTISTS_COLUMN_PUBLISHED";
    public static final String ARTISTS_COLUMN_SUMMARY    = "ARTISTS_COLUMN_SUMMARY";
    public static final String ARTISTS_COLUMN_CONTENT    = "ARTISTS_COLUMN_CONTENT";
    public static final String ARTISTS_RECOMMENDED_FLAG  = "ARTISTS_RECOMMENDED_FLAG";
    private String ARTISTS_CREATE = "create table " + ARTISTS_NAME_TABLE + " ("
                                      + COLUMN_ID + " integer primary key autoincrement,"
                                      + ARTISTS_COLUMN_NAME + " text,"
                                      + ARTISTS_COLUMN_STREAMABLE + " text,"
                                      + ARTISTS_COLUMN_URL_IMG + " text,"
                                      + ARTISTS_COLUMN_PUBLISHED + " text,"
                                      + ARTISTS_COLUMN_SUMMARY + " text,"
                                      + ARTISTS_COLUMN_CONTENT + " text,"
                                      + ARTISTS_RECOMMENDED_FLAG + " boolean"  + ");";

    public static final String SIMILAR_NAME_TABLE        = "SIMILAR_NAME_TABLE";
    public static final String SIMILAR_COLUMN_ID_ARTIST  = "SIMILAR_COLUMN_ID_ARTIST";
    public static final String SIMILAR_COLUMN_ID_SIMILAR = "SIMILAR_COLUMN_ID_SIMILAR";
    private String SIMILAR_CREATE = "create table " + SIMILAR_NAME_TABLE + " ("
                                   + COLUMN_ID + " integer primary key autoincrement,"
                                   + SIMILAR_COLUMN_ID_ARTIST  + " integer,"
                                   + SIMILAR_COLUMN_ID_SIMILAR  + " integer" + ");";



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
        db.execSQL(ARTISTS_CREATE);
        db.execSQL(NEW_RELEASES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {

    }
}
