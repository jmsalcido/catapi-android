package org.otfusion.caturday.migrations.files;

import org.otfusion.caturday.migrations.AbstractMigration;
import org.otfusion.caturday.migrations.DatabaseTableName;

// used from MigrationUtils reflection call
@SuppressWarnings("unused")
public class Migration1 extends AbstractMigration {

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void addStatements() {
        statements.add("CREATE TABLE " + DatabaseTableName.CATS + "(" +
                "   id TEXT PRIMARY KEY," +
                "   image_url TEXT," +
                "   provider_name TEXT" +
                ");");
    }
}
