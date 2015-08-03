package org.otfusion.votecats.db.repository;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DbUtils {

    private DbUtils() {}

    public static <T> List<T> selectList(Cursor cursor, DbBuilder<T> dbBuilder) {
        if (cursor == null || cursor.getCount() <= 0) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            T object = dbBuilder.buildFromCursor(cursor);
            list.add(object);
        }
        return list;
    }

    public static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
}
