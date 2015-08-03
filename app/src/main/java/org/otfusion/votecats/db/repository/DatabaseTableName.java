package org.otfusion.votecats.db.repository;

public enum DatabaseTableName {

    CATS("cats");

    private String _name;

    DatabaseTableName(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
}
