package org.otfusion.votecats.db.repository;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.otfusion.votecats.application.VoteCatsApplication;
import org.otfusion.votecats.migrations.Migration;
import org.otfusion.votecats.migrations.MigrationUtils;

import java.util.List;

public class VoteCatsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "votecats.db";

    private static final List<Migration> MIGRATIONS =
            MigrationUtils.getMigrations(DATABASE_VERSION);

    public VoteCatsDbHelper() {
        super(VoteCatsApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (Migration migration : MIGRATIONS) {
            db.execSQL(migration.getSQL());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (Migration migration : MIGRATIONS) {
            if (isUpgradeMigration(oldVersion, migration)) {
                db.execSQL(migration.getSQL());
            }
        }
    }

    private boolean isUpgradeMigration(int oldVersion, Migration migration) {
        return migration.getVersion() > oldVersion;
    }
}
