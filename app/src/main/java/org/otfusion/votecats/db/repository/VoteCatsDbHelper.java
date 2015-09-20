package org.otfusion.votecats.db.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VoteCatsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "votecats.db";
    private static final String CREATE_ENTRIES = "" +
            "CREATE TABLE " + DatabaseTableName.CATS + "(" +
            "   id TEXT PRIMARY KEY," +
            "   image_url TEXT," +
            "   provider_name TEXT" +
            ")";

    public VoteCatsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
