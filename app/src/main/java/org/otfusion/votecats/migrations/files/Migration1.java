package org.otfusion.votecats.migrations.files;

import org.otfusion.votecats.migrations.AbstractMigration;
import org.otfusion.votecats.migrations.DatabaseTableName;
import org.otfusion.votecats.migrations.Migration;

import java.util.ArrayList;
import java.util.List;

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
