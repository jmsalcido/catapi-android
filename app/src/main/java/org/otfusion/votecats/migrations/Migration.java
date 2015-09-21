package org.otfusion.votecats.migrations;

public interface Migration {
    int getVersion();
    String getSQL();
}
