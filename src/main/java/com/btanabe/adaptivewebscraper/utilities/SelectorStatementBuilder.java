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
    public String tagname;
    public String classname;
    public int childElementIndex;

    @Override
    public String getObject() throws Exception {
        return String.format("%s.%s:eq(%d)", tagname, classname, childElementIndex);
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
