package com.btanabe.adaptivewebscraper.utilities;

/**
 * Created by Brian on 5/1/16.
 */
public enum SelectorStatementEqualityOperators {
    EQUALS(" = "),
    STARTS_WITH(" ^= "),
    ENDS_WITH(" $= "),
    CONTAINS(" *= ");

    private final String description;

    private SelectorStatementEqualityOperators(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
