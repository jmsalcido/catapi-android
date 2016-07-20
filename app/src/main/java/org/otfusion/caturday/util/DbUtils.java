package org.otfusion.caturday.util;

import android.database.Cursor;
import android.support.annotation.NonNull;

import org.otfusion.caturday.db.repository.DbBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// todo should be moved to another class, utils are a bad design.
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
        cursor.close();
        return list;
    }

    public static boolean isCursorEmpty(Cursor cursor) {
        return cursor == null || cursor.getCount() <= 0;
    }

    public static <T> T selectObject(Cursor cursor, DbBuilder<T> dbBuilder) {
        if (isCursorEmpty(cursor)) {
            return null;
        }
        T object = dbBuilder.buildFromCursor(cursor);
        cursor.close();
        return object;
    }

    public static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}
