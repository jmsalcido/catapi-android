package org.otfusion.votecats.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.otfusion.votecats.application.VoteCatsApplication;
import org.otfusion.votecats.common.model.Cat;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoriteCatRepository {

    private final Context _context;

    public FavoriteCatRepository() {
        _context = VoteCatsApplication.getContext();
    }

    public long saveFavoriteCat(Cat cat) {
        SQLiteDatabase db = getSQLiteWritableDatabase();
        return db.insert(getTableName(), null, buildContentValues(cat));
    }

    public Map<String, Cat> getFavoriteCatsMap() {
        Cursor query = readDatabaseCursor();
        final Map<String, Cat> cats =
                DbUtils.selectObject(query, new DbBuilder<Map<String, Cat>>() {
                    @Override
                    public Map<String, Cat> buildFromCursor(Cursor cursor) {
                        Map<String, Cat> result = new HashMap<>();
                        while (cursor.moveToNext()) {
                            Cat cat = buildCatFromCursor(cursor);
                            result.put(cat.getId(), cat);
                        }
                        return result;
                    }
                });
        if (cats == null) {
            return Collections.emptyMap();
        }
        return cats;
    }

    @NonNull
    public List<Cat> getFavoriteCats() {
        Cursor query = readDatabaseCursor();
        return DbUtils.selectList(query, new DbBuilder<Cat>() {
            @Override
            public Cat buildFromCursor(Cursor cursor) {
                return buildCatFromCursor(cursor);
            }
        });
    }

    private Cat buildCatFromCursor(Cursor cursor) {
        Cat cat = new Cat();
        cat.setId(DbUtils.getString(cursor, "id"));
        cat.setImageUrl(DbUtils.getString(cursor, "image_url"));
        cat.setProviderName(DbUtils.getString(cursor, "provider_name"));
        return cat;
    }

    private Cursor readDatabaseCursor() {
        SQLiteDatabase db = getSQLiteReadableDatabase();
        String[] columns = getColumns();
        return db.query(getTableName(), columns, null, null, null,
                null, null);
    }

    @NonNull
    private String getTableName() {
        return DatabaseTableName.CATS;
    }

    private String[] getColumns() {
        return new String[]{
                "id",
                "image_url",
                "provider_name"
        };
    }

    private SQLiteDatabase getSQLiteWritableDatabase() {
        VoteCatsDbHelper dbHelper = new VoteCatsDbHelper(getContext());
        return dbHelper.getWritableDatabase();
    }

    private SQLiteDatabase getSQLiteReadableDatabase() {
        VoteCatsDbHelper dbHelper = new VoteCatsDbHelper(getContext());
        return dbHelper.getReadableDatabase();
    }

    private ContentValues buildContentValues(Cat cat) {
        ContentValues values = new ContentValues();
        values.put("id", cat.getId());
        values.put("image_url", cat.getImageUrl());
        values.put("provider_name", cat.getProviderName());
        return values;
    }

    public Context getContext() {
        return _context;
    }
}
