package com.btanabe.adaptivewebscraper.factories.outputobject;

import lombok.AllArgsConstructor;
import org.springframework.util.ClassUtils;

/**
 * Created by Brian on 9/18/16.
 */
@AllArgsConstructor
public class OutputObjectSetter<ObjectType> implements OutputObjectSetterI<ObjectType> {
    private Class<ObjectType> outputObjectClazzPath;

    @Override
    public <ValueType> void setValue(ObjectType outputObject, String setterMethodName, ValueType value) throws Exception {
        if (value != null) {
            ClassUtils.getMethod(outputObjectClazzPath, setterMethodName, value.getClass()).invoke(outputObject, value);
        }
    }
}
