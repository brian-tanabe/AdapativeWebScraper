package com.btanabe.adaptivewebscraper.factories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Brian on 5/14/16.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class DoubleFactory implements FactoryBean<Double> {
    private String doubleString;

    @Override
    public Double getObject() throws Exception {
        return Double.valueOf(doubleString);
    }

    @Override
    public Class<?> getObjectType() {
        return Double.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
