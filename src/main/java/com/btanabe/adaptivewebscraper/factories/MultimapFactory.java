package com.btanabe.adaptivewebscraper.factories;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
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
public class MultimapFactory<KeyClazz, ValueClazz> implements FactoryBean<Multimap<KeyClazz, ValueClazz>> {
    private KeyClazz key;
    private ValueClazz value;

    @Override
    public Multimap getObject() throws Exception {
        Multimap multimap = LinkedHashMultimap.create();
        multimap.put(key, value);

        return multimap;
    }

    @Override
    public Class<?> getObjectType() {
        return Multimap.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
