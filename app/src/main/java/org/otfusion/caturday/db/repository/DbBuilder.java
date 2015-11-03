package org.otfusion.caturday.db.repository;

import android.database.Cursor;

public interface DbBuilder<T> {

    T buildFromCursor(Cursor cursor);

}
