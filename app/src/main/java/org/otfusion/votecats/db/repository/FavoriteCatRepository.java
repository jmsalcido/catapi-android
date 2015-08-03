package org.otfusion.votecats.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.otfusion.votecats.common.model.Cat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class FavoriteCatRepository {

    @Inject
    public FavoriteCatRepository() {
    }

    public long saveFavoriteCat(Context context, Cat cat) {
        SQLiteDatabase db = getSQLiteWritableDatabase(context);
        return db.insert(DatabaseTableName.CATS.getName(), null, buildContentValues(cat));
    }

    public Map<String, Cat> getFavoriteCatsMap(Context context) {
        Cursor query = readDatabaseCursor(context);
        final Map<String, Cat> allCats = new HashMap<>();
        DbUtils.selectList(query, new DbBuilder<Cat>() {
            @Override
            public Cat buildFromCursor(Cursor cursor) {
                Cat cat = buildCatFromCursor(cursor);
                allCats.put(cat.getId(), cat);
                return cat;
            }
        });
        return allCats;
    }

    public List<Cat> getFavoriteCats(Context context) {
        Cursor query = readDatabaseCursor(context);
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

    private Cursor readDatabaseCursor(Context context) {
        SQLiteDatabase db = getSQLiteReadableDatabase(context);
        String[] columns = getColumns();
        return db.query(DatabaseTableName.CATS.getName(), columns, null, null, null,
                null, null);
    }

    private String[] getColumns() {
        return new String[] {
                "id",
                "image_url",
                "provider_name"
        };
    }

    private SQLiteDatabase getSQLiteWritableDatabase(Context context) {
        VoteCatsDbHelper dbHelper = new VoteCatsDbHelper(context);
        return dbHelper.getWritableDatabase();
    }

    private SQLiteDatabase getSQLiteReadableDatabase(Context context) {
        VoteCatsDbHelper dbHelper = new VoteCatsDbHelper(context);
        return dbHelper.getReadableDatabase();
    }

    private ContentValues buildContentValues(Cat cat) {
        ContentValues values = new ContentValues();
        values.put("id", cat.getId());
        values.put("image_url", cat.getImageUrl());
        values.put("provider_name", cat.getProviderName());
        return values;
    }

}
