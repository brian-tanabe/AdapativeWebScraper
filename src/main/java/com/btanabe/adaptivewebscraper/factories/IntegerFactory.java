package com.btanabe.adaptivewebscraper.factories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Brian on 5/4/16.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class IntegerFactory implements FactoryBean<Integer> {
    private String integerString;

    @Override
    public Integer getObject() throws Exception {
        return Integer.valueOf(integerString);
    }

    @Override
    public Class<?> getObjectType() {
        return Integer.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
