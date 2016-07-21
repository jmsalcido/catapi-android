package org.otfusion.caturday.db.repository;

import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class AbstractRepository {

    @NonNull
    <T> List<T> selectList(Cursor cursor, DbBuilder<T> dbBuilder) {
        if (isCursorEmpty(cursor)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            T object = dbBuilder.buildFromCursor(cursor);
            list.add(object);
        }
        cursor.close();
        return list;
    }

    private boolean isCursorEmpty(Cursor cursor) {
        return cursor == null || cursor.getCount() <= 0;
    }

    <T> T selectObject(Cursor cursor, DbBuilder<T> dbBuilder) {
        if (isCursorEmpty(cursor)) {
            return null;
        }
        T object = dbBuilder.buildFromCursor(cursor);
        cursor.close();
        return object;
    }

    String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

}
