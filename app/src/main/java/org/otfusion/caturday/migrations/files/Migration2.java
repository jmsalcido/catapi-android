package org.otfusion.caturday.migrations.files;

import org.otfusion.caturday.migrations.AbstractMigration;
import org.otfusion.caturday.migrations.DatabaseTableName;

// used from MigrationUtils reflection call
@SuppressWarnings("unused")
public class Migration2 extends AbstractMigration {

    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public void addStatements() {
        addStatement("ALTER TABLE " + DatabaseTableName.CATS + " ADD COLUMN name TEXT");
    }
}
