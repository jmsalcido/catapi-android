package org.otfusion.caturday.migrations;

import org.otfusion.caturday.migrations.files.Migration1;
import org.otfusion.caturday.migrations.files.Migration2;
import org.otfusion.caturday.migrations.files.Migration3;

import java.util.ArrayList;
import java.util.List;

public class MigrationUtils {

    private static List<Migration> MIGRATIONS = new ArrayList<>();

    static {
        // issue: manually set entire classes... org.reflections did not work.
        addMigration(Migration1.class);
        addMigration(Migration2.class);
        addMigration(Migration3.class);
    }

    private static <T extends Migration> void addMigration(Class<T> migrationClass) {
        try {
            T migration = migrationClass.newInstance();
            MIGRATIONS.add(migration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T extends Migration> boolean isUpgradeMigration(T migration, int version) {
        return migration.getVersion() <= version;
    }

    public static List<Migration> getMigrations(int version) {
        List<Migration> versionMigrations = new ArrayList<>();
        for (Migration migration : MIGRATIONS) {
            if (isUpgradeMigration(migration, version)) {
                versionMigrations.add(migration);
            }
        }
        return versionMigrations;
    }
}
