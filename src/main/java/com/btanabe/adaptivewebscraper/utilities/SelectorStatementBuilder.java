package com.btanabe.adaptivewebscraper.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Brian on 4/30/16.
 */
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectorStatementBuilder implements FactoryBean<String> {
    private String parentTagName;
    private SelectorStatementEqualityOperators parentClassnameEqualityOperator;
    private String parentClassname;

    private String tagName;
    private SelectorStatementEqualityOperators classnameEqualityOperator;
    private String className;
    private Integer childElementIndex;
    private String containsText;

    @Override
    public String getObject() throws Exception {
        StringBuilder statement = new StringBuilder();

        appendParentElementSelectorStatement(statement);
        appendElementSelectorStatement(statement);
        appendEqualsSelectorStatement(statement);
        appendContainsTextSelectorStatement(statement);

        return statement.toString();
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    private void appendParentElementSelectorStatement(final StringBuilder statement) {
        if(parentTagName != null) {
            statement.append(buildTagAndClassSelectorStatement(parentTagName, parentClassnameEqualityOperator, parentClassname)).append(" > ");
        }
    }

    private void appendElementSelectorStatement(final StringBuilder statement) {
        statement.append(buildTagAndClassSelectorStatement(tagName, classnameEqualityOperator, className));
    }

    private String buildTagAndClassSelectorStatement(final String tagName, final SelectorStatementEqualityOperators classnameEqualityOperator, final String className) {
        StringBuilder statement = new StringBuilder();
        if (tagName != null) {
            statement.append(tagName);

            if (classnameEqualityOperator != null) {
                statement.append("[class").append(classnameEqualityOperator).append(className).append("]");
            }
        }

        return statement.toString();
    }

    private void appendEqualsSelectorStatement(final StringBuilder statement) {
        if(childElementIndex != null) {
            statement.append(":eq(").append(childElementIndex).append(")");
        }
    }

    private void appendContainsTextSelectorStatement(final StringBuilder statement) {
        if(containsText != null) {
            statement.append(":contains(").append(containsText).append(")");
        }
    }
}
