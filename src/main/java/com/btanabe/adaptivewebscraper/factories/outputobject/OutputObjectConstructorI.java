package com.btanabe.adaptivewebscraper.factories.outputobject;

/**
 * Created by Brian on 9/18/16.
 */
public interface OutputObjectConstructorI<OutputClazz> {
    OutputClazz createOutputObject() throws Exception;
}
