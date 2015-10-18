package org.otfusion.votecats.migrations.files;

import org.otfusion.votecats.migrations.AbstractMigration;
import org.otfusion.votecats.migrations.DatabaseTableName;
import org.otfusion.votecats.migrations.Migration;

import java.util.ArrayList;
import java.util.List;

// used from MigrationUtils reflection call
@SuppressWarnings("unused")
public class Migration2 extends AbstractMigration {

    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public void addStatements() {
        addStatement("ALTER TABLE " + DatabaseTableName.CATS + " " +
                "ADD COLUMN name TEXT");
    }
}
