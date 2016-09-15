package com.btanabe.adaptivewebscraper.utilities;

/**
 * Created by Brian on 8/27/16.
 */
public enum SelectorStatementRelationshipOperators {
    DIRECT_CHILD(" > "),
    IMMEDIATELY_PRECEDED_BY(" + "),
    PRECEDED_BY(" ~ "),
    ANY_OF(", ");

    private final String description;

    private SelectorStatementRelationshipOperators(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
