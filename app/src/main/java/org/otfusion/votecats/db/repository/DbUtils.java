package org.otfusion.votecats.db.repository;

import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbUtils {

    private DbUtils() {}

    @NonNull
    public static <T> List<T> selectList(Cursor cursor, DbBuilder<T> dbBuilder) {
        if (isCursorEmpty(cursor)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            T object = dbBuilder.buildFromCursor(cursor);
            list.add(object);
        }
        return list;
    }

    public static boolean isCursorEmpty(Cursor cursor) {
        return cursor == null || cursor.getCount() <= 0;
    }

    public static <T> T selectObject(Cursor cursor, DbBuilder<T> dbBuilder) {
        if (isCursorEmpty(cursor)) {
            return null;
        }
        return dbBuilder.buildFromCursor(cursor);
    }

    public static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
}
