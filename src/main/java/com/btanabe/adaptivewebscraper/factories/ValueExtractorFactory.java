package com.btanabe.adaptivewebscraper.factories;

import com.btanabe.adaptivewebscraper.parsers.ValueExtractor;
import lombok.NonNull;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Brian on 5/10/16.
 */
public class ValueExtractorFactory<OutputClazz> {

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

    @Setter(onMethod = @__({@Autowired}))
    private String valueExtractorName;

    public ValueExtractor createValueExtractor(final Document document) {
        return new ValueExtractor<OutputClazz>(document, objectClasspath, outputClazzFactoryClazz, xpathSelector, textGetterMethodName, textGetterMethodParameterTypes, textGetterMethodParameters, valueTransformers, valueExtractorName);
    }
}
