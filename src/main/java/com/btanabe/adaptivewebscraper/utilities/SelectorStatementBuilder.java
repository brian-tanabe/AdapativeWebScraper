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
    private int childElementIndex;

    @Override
    public String getObject() throws Exception {
        StringBuilder statement = new StringBuilder();

        String parentElementSelectorStatement = buildTagAndClassSelectorStatement(parentTagName, parentClassnameEqualityOperator, parentClassname);
        if (!parentElementSelectorStatement.isEmpty()) {
            statement.append(parentElementSelectorStatement).append(" > ");

        }

        statement.append(buildTagAndClassSelectorStatement(tagName, classnameEqualityOperator, className)).append(":eq(").append(childElementIndex).append(")");

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
}
