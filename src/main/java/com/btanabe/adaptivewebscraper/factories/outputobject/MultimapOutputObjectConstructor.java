package com.btanabe.adaptivewebscraper.factories.outputobject;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import lombok.AllArgsConstructor;

/**
 * Created by Brian on 9/18/16.
 */
@AllArgsConstructor
public class MultimapOutputObjectConstructor<KeyClazz, ValueClazz> implements OutputObjectConstructorI {
    private Class<KeyClazz> keyClass;
    private Class<ValueClazz> valueClass;

    @Override
    public Multimap<KeyClazz, ValueClazz> createOutputObject() throws Exception {
        Multimap<KeyClazz, ValueClazz> multiMap = LinkedHashMultimap.create();
        return multiMap;
    }
}
