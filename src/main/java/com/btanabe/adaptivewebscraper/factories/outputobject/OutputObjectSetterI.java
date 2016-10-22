package com.btanabe.adaptivewebscraper.factories.outputobject;

/**
 * Created by Brian on 9/18/16.
 */
public interface OutputObjectSetterI<ObjectType> {
    <ValueType> void setValue(ObjectType outputObject, String setterMethodName, ValueType value) throws Exception;
}
