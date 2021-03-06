package com.btanabe.adaptivewebscraper.factories.valueextractor;

import com.btanabe.adaptivewebscraper.parsers.ValueExtractor;
import lombok.NonNull;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Brian on 5/10/16.
 */
public class DynamicValueExtractorFactory<OutputClazz> implements ValueExtractorFactoryI<OutputClazz> {

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    private Class<OutputClazz> objectClasspath;

    @Setter(onMethod = @__({@Autowired}))
    private Class<FactoryBean<OutputClazz>> outputClazzFactoryClazz;

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    private String xpathSelector;

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    private String textGetterMethodName;

    @Setter(onMethod = @__({@Autowired}))
    private Class<?>[] textGetterMethodParameterTypes;

    @Setter(onMethod = @__({@Autowired}))
    private Object[] textGetterMethodParameters;

    @Setter(onMethod = @__({@Autowired}))
    private List<Function<String, String>> valueTransformers;

    @Override
    public Callable<Stream<OutputClazz>> createValueExtractor(final Document document) {
        return new ValueExtractor(document, objectClasspath, outputClazzFactoryClazz, xpathSelector, textGetterMethodName, textGetterMethodParameterTypes, textGetterMethodParameters, valueTransformers);
    }
}
