package org.otfusion.votecats.migrations.files;

import org.otfusion.votecats.migrations.DatabaseTableName;
import org.otfusion.votecats.migrations.Migration;

// used from MigrationUtils reflection call
@SuppressWarnings("unused")
public class Migration1 implements Migration {
    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getSQL() {
        return "CREATE TABLE " + DatabaseTableName.CATS + "(" +
                "   id TEXT PRIMARY KEY," +
                "   image_url TEXT," +
                "   provider_name TEXT" +
                ")";
    }
}
