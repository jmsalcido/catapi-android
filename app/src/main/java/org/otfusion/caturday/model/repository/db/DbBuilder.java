package org.otfusion.caturday.model.repository.db;

import android.database.Cursor;

public interface DbBuilder<T> {

    T buildFromCursor(Cursor cursor);

}
