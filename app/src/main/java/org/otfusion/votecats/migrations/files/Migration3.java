package org.otfusion.votecats.migrations.files;

import org.otfusion.votecats.migrations.AbstractMigration;
import org.otfusion.votecats.migrations.DatabaseTableName;
import org.otfusion.votecats.migrations.Migration;

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
