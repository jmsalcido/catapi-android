package org.otfusion.votecats.migrations.files;

import org.otfusion.votecats.migrations.DatabaseTableName;
import org.otfusion.votecats.migrations.Migration;

// used from MigrationUtils reflection call
@SuppressWarnings("unused")
public class Migration2 implements Migration {
    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public String getSQL() {
        return "ALTER TABLE " + DatabaseTableName.CATS + " " +
                "ADD COLUMN name TEXT";
    }
}
