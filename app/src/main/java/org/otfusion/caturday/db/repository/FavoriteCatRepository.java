package org.otfusion.caturday.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.migrations.DatabaseTableName;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class FavoriteCatRepository extends AbstractRepository {

    private final Context context;

    @Inject
    public FavoriteCatRepository(Context context) {
        this.context = context;
    }

    public long saveFavoriteCat(Cat cat) {
        SQLiteDatabase db = getSQLiteWritableDatabase();
        long result = db.insert(getTableName(), null, buildContentValues(cat));
        db.close();
        return result;
    }

    public void deleteFromFavorites(Cat cat) {
        SQLiteDatabase db = getSQLiteWritableDatabase();
        db.delete(DatabaseTableName.CATS, "id = ?", new String[]{String.valueOf(cat.getId())});
        db.close();
    }

    public Map<String, Cat> getFavoriteCatsMap() {
        Cursor query = readDatabaseCursor();
        final Map<String, Cat> cats = selectObject(query, new DbBuilder<Map<String, Cat>>() {
            @Override
            public Map<String, Cat> buildFromCursor(Cursor cursor) {
                Map<String, Cat> result = new HashMap<>();
                while (cursor.moveToNext()) {
                    Cat cat = buildCatFromCursor(cursor);
                    result.put(cat.getProviderId(), cat);
                }
                return result;
            }
        });
        if (cats == null) {
            return Collections.emptyMap();
        }
        query.close();
        return cats;
    }

    @NonNull
    public List<Cat> getFavoriteCats() {
        Cursor query = readDatabaseCursor();
        List<Cat> cats = selectList(query, new DbBuilder<Cat>() {
            @Override
            public Cat buildFromCursor(Cursor cursor) {
                return buildCatFromCursor(cursor);
            }
        });
        query.close();
        return cats;
    }

    private Cat buildCatFromCursor(Cursor cursor) {
        Cat cat = new Cat();
        cat.setId(getLong(cursor, "id"));
        cat.setImageUrl(getString(cursor, "image_url"));
        cat.setName(getString(cursor, "name"));
        cat.setProviderId(getString(cursor, "provider_id"));
        cat.setProviderName(getString(cursor, "provider_name"));
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
                "provider_name",
                "provider_id",
                "name"
        };
    }

    private SQLiteDatabase getSQLiteWritableDatabase() {
        VoteCatsDbHelper dbHelper = new VoteCatsDbHelper(context);
        return dbHelper.getWritableDatabase();
    }

    private SQLiteDatabase getSQLiteReadableDatabase() {
        VoteCatsDbHelper dbHelper = new VoteCatsDbHelper(context);
        return dbHelper.getReadableDatabase();
    }

    private ContentValues buildContentValues(Cat cat) {
        ContentValues values = new ContentValues();
        values.put("image_url", cat.getImageUrl());
        values.put("name", cat.getName());
        values.put("provider_id", cat.getProviderId());
        values.put("provider_name", cat.getProviderName());
        return values;
    }
}
