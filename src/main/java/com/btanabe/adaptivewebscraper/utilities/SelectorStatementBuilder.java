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

    /**
     * Parent tag selector:
     */
    private String otherTagName;
    private String otherAttributeName;
    private SelectorStatementEqualityOperators otherAttributeEqualityOperator;
    private String otherAttributeValue;

    private SelectorStatementEqualityOperators otherClassnameEqualityOperator;
    private String otherClassname;

    private String otherContainsText;

    /**
     * Parent-child relationship:
     */
    SelectorStatementRelationshipOperators tagRelationship;

    /**
     * tag selector:
     */
    private String tagName;
    private String attributeName;
    private SelectorStatementEqualityOperators attributeNameEqualityOperator;
    private String attributeValue;

    private SelectorStatementEqualityOperators classnameEqualityOperator;
    private String className;

    private Integer childElementIndex;
    private String containsText;

    @Override
    public String getObject() throws Exception {
        checkArguments();

        StringBuilder statement = new StringBuilder();

        appendTagName(statement, otherTagName);
        appendAttribute(statement, otherAttributeName, otherAttributeEqualityOperator, otherAttributeValue);
        appendClass(statement, otherClassnameEqualityOperator, otherClassname);
        appendContainsTextSelectorStatement(statement, otherContainsText);

        appendRelationship(statement, tagRelationship);

        appendTagName(statement, tagName);
        appendAttribute(statement, attributeName, attributeNameEqualityOperator, attributeValue);
        appendClass(statement, classnameEqualityOperator, className);
        appendEqualsSelectorStatement(statement, childElementIndex);
        appendContainsTextSelectorStatement(statement, containsText);

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
        if (otherTagName != null) {
            statement.append(buildTagAndClassSelectorStatement(otherTagName, otherAttributeEqualityOperator, otherAttributeValue, null, null, null)).append(" > ");
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

    private void appendEqualsSelectorStatement(final StringBuilder statement, final Integer childElementIndex) {
        if (childElementIndex != null) {
            statement.append(":eq(").append(childElementIndex).append(")");
        }
    }

    private void appendContainsTextSelectorStatement(final StringBuilder statement, final String containsText) {
        if (containsText != null) {
            statement.append(":contains(").append(containsText).append(")");
        }
    }

    private void appendTagName(final StringBuilder statement, final String tagName) {
        if (tagName != null) {
            statement.append(tagName);
        }
    }

    private void appendAttribute(final StringBuilder statement, final String attributeName, final SelectorStatementEqualityOperators equalityOperator, final String attributeValue) {
        if (attributeName != null && attributeValue != null) {
            statement.append("[").append(attributeName).append(equalityOperator).append(attributeValue).append("]");
        } else if(attributeValue != null) {
            statement.append("[").append(attributeValue).append("]");
        }
    }

    private void appendClass(final StringBuilder statement, final SelectorStatementEqualityOperators equalityOperator, final String className) {
        if (equalityOperator == null && className != null) {
            statement.append(".").append(className);
        } else if (className != null) {
            appendAttribute(statement, "class", equalityOperator, className);
        }
    }

    private void appendRelationship(StringBuilder statement, SelectorStatementRelationshipOperators tagRelationship) {
        if (tagRelationship != null) {
            statement.append(tagRelationship);
        }
    }

    private void checkArguments() {
        if (classnameEqualityOperator != null && !classnameEqualityOperator.equals(EQUALS) && attributeNameEqualityOperator != null && !attributeNameEqualityOperator.equals(EQUALS)) {
            throw new IllegalArgumentException("Cannot specify both classnameEqualityOperator and attributeNameEqualityOperator which are not both \"EQUALS\"");
        }
    }
}
