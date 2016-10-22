package com.btanabe.adaptivewebscraper.factories.outputobject;

import lombok.AllArgsConstructor;
import org.springframework.util.ClassUtils;

/**
 * Created by Brian on 9/18/16.
 */
@AllArgsConstructor
public class ClassWithConstructorOutputObjectConstructor<OutputClazz> implements OutputObjectConstructorI<OutputClazz> {
    private Class<OutputClazz> outputClazzPath;

    @Override
    public OutputClazz createOutputObject() throws Exception {
        return ClassUtils.getConstructorIfAvailable(outputClazzPath).newInstance();
    }
}
