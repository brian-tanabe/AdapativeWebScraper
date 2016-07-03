package com.btanabe.adaptivewebscraper.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

import static com.btanabe.adaptivewebscraper.utilities.SelectorStatementEqualityOperators.EQUALS;

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

    private String attributeName;
    private SelectorStatementEqualityOperators attributeNameEqualityOperator;
    private String attributeValue;

    private Integer childElementIndex;
    private String containsText;

    @Override
    public String getObject() throws Exception {
        checkArguments();

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
        if (parentTagName != null) {
            statement.append(buildTagAndClassSelectorStatement(parentTagName, parentClassnameEqualityOperator, parentClassname, null, null, null)).append(" > ");
        }
    }

    private void appendElementSelectorStatement(final StringBuilder statement) {
        statement.append(buildTagAndClassSelectorStatement(tagName, classnameEqualityOperator, className, attributeName, attributeNameEqualityOperator, attributeValue));
    }

    private String buildTagAndClassSelectorStatement(final String tagName, final SelectorStatementEqualityOperators classnameEqualityOperator, final String className, final String attributeName, final SelectorStatementEqualityOperators attributeNameEqualityOperator, final String attributeValue) {
        StringBuilder statement = new StringBuilder();
        if (tagName != null) {
            statement.append(tagName);

            if (attributeNameEqualityOperator != null && className != null) {
                statement.append("[").append(attributeName).append(attributeNameEqualityOperator).append(attributeValue).append("]").append(".").append(className);
            } else if (attributeNameEqualityOperator != null) {
                statement.append("[").append(attributeName).append(attributeNameEqualityOperator).append(attributeValue).append("]");
            } else if (className != null) {
                statement.append("[class").append(classnameEqualityOperator).append(className).append("]");
            }
        }

        return statement.toString();
    }

    private void appendEqualsSelectorStatement(final StringBuilder statement) {
        if (childElementIndex != null) {
            statement.append(":eq(").append(childElementIndex).append(")");
        }
    }

    private void appendContainsTextSelectorStatement(final StringBuilder statement) {
        if (containsText != null) {
            statement.append(":contains(").append(containsText).append(")");
        }
    }

    private void checkArguments() {
        if (classnameEqualityOperator != null && !classnameEqualityOperator.equals(EQUALS) && attributeNameEqualityOperator != null && !attributeNameEqualityOperator.equals(EQUALS)) {
            throw new IllegalArgumentException("Cannot specify both classnameEqualityOperator and attributeNameEqualityOperator which are not both \"EQUALS\"");
        }
    }
}
