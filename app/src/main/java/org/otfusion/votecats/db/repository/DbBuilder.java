package org.otfusion.votecats.db.repository;

import android.database.Cursor;

public interface DbBuilder<T> {

    T buildFromCursor(Cursor cursor);

}
