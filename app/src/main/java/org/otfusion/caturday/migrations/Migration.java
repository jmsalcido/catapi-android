package org.otfusion.caturday.migrations;

import java.util.List;

public interface Migration {
    int getVersion();
    List<String> getStatements();
}
