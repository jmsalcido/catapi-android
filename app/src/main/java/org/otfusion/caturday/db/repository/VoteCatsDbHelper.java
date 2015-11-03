package org.otfusion.caturday.db.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.otfusion.caturday.migrations.Migration;
import org.otfusion.caturday.migrations.MigrationUtils;

import java.util.List;

public class VoteCatsDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "votecats.db";

    private static final List<Migration> MIGRATIONS =
            MigrationUtils.getMigrations(DATABASE_VERSION);

    public VoteCatsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (Migration migration : MIGRATIONS) {
            executeTransactionalMigration(db, migration);
        }
    }

    private void executeTransactionalMigration(SQLiteDatabase db, Migration migration) {
        for (String query : migration.getStatements()) {
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (Migration migration : MIGRATIONS) {
            if (isUpgradeMigration(oldVersion, migration)) {
                executeTransactionalMigration(db, migration);
            }
        }
    }

    private boolean isUpgradeMigration(int oldVersion, Migration migration) {
        return migration.getVersion() > oldVersion;
    }
}
