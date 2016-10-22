package com.btanabe.adaptivewebscraper.factories.outputobject;

import com.btanabe.adaptivewebscraper.factories.valueextractor.DefaultableValueExtractorFactory;
import com.btanabe.adaptivewebscraper.factories.valueextractor.ValueExtractorFactoryI;
import com.google.common.collect.Multimap;

import java.util.Objects;

/**
 * Created by Brian on 9/18/16.
 */
public class MultimapObjectSetter implements OutputObjectSetterI<Multimap<ValueExtractorFactoryI<String>, String>> {

    @Override
    public <ValueType> void setValue(Multimap<ValueExtractorFactoryI<String>, String> outputObject, String setterMethodName, ValueType value) throws Exception {
        if (Objects.nonNull(value)) {
            ValueExtractorFactoryI defaultableValueExtractorFactory = new DefaultableValueExtractorFactory(value);
            outputObject.put(defaultableValueExtractorFactory, setterMethodName);
        }
    }
}
