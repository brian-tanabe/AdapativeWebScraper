package com.btanabe.adaptivewebscraper.factories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Brian on 5/7/16.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class StringFactory implements FactoryBean<String> {
    private String stringString;

    @Override
    public String getObject() throws Exception {
        return new String(stringString);
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
