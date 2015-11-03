package org.otfusion.caturday.migrations.files;

import org.otfusion.caturday.migrations.AbstractMigration;
import org.otfusion.caturday.migrations.DatabaseTableName;

// used in MigrationUtils
@SuppressWarnings("unused")
public class Migration3 extends AbstractMigration {

    @Override
    public int getVersion() {
        return 3;
    }

    @Override
    public void addStatements() {
        String copyTableName = DatabaseTableName.CATS + "_copy";
        addStatement("CREATE TABLE " + copyTableName + "(" + //
                "   id INTEGER PRIMARY KEY," + //
                "   image_url TEXT," + //
                "   name TEXT," + //
                "   provider_id TEXT," + //
                "   provider_name TEXT" + //
                ");");
        addStatement("INSERT INTO " + copyTableName + " (provider_id, image_url, provider_name) " + //
                "   SELECT id, image_url, provider_name FROM " + DatabaseTableName.CATS + ";");
        addStatement("DROP TABLE " + DatabaseTableName.CATS + ";");
        addStatement("ALTER TABLE " + copyTableName + " RENAME TO " + DatabaseTableName.CATS + ";");
    }
}
