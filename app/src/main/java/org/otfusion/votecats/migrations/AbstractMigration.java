package org.otfusion.votecats.migrations;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMigration implements Migration {

    protected List<String> statements;

    public AbstractMigration() {
        this.statements = new ArrayList<>();
    }

    @Override
    public List<String> getStatements() {
        addStatements();
        return statements;
    }

    public abstract void addStatements();

    protected void addStatement(String sql) {
        statements.add(sql);
    }
}
