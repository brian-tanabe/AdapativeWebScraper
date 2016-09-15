package com.btanabe.adaptivewebscraper.parsers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Brian on 4/5/16.
 */
@Slf4j
@RequiredArgsConstructor
public class ValueExtractor<OutputClazz> implements Callable<Stream<OutputClazz>> {

    @NonNull
    private final Document document;

    @NonNull
    private final Class<OutputClazz> objectClasspath;

    @NonNull
    private final Class<FactoryBean<OutputClazz>> outputClazzFactoryClazz;

    @NonNull
    private final String xpathSelector;

    @NonNull
    private final String textGetterMethodName;

    @NonNull
    private final Class<?>[] textGetterMethodParameterTypes;

    @NonNull
    private final Object[] textGetterMethodParameters;

    @NonNull
    private final List<Function<String, String>> valueTransformers;

    @Override
    public Stream<OutputClazz> call() throws Exception {
        return Stream.of(getAllOccurrencesAsString());
    }

    private OutputClazz[] getAllOccurrencesAsString() throws Exception {
        List<OutputClazz> matchedElements = new ArrayList<>();
        document.select(xpathSelector).stream().forEach(element -> {

            try {
                matchedElements.add(createOutputClassAndSetItsValue(applyTransformations(createFactoryConstructorStringArgument(element))));
            } catch (Exception e) {
                log.error(ExceptionUtils.getMessage(e));
                log.error(ExceptionUtils.getStackTrace(e));
            }
        });

        return matchedElements.toArray(instantiateOutputClazzArray(matchedElements.size()));
    }

    private String createFactoryConstructorStringArgument(final Element element) throws Exception {
        return (String) ClassUtils.getMethod(Element.class, textGetterMethodName, textGetterMethodParameterTypes).invoke(element, textGetterMethodParameters);
    }

    private String applyTransformations(final String untransformedString) {
        String transformedString = untransformedString;
        for (Function<String, String> transformer : valueTransformers) {
            transformedString = transformer.apply(transformedString);
        }

        return transformedString;
    }

    private OutputClazz createOutputClassAndSetItsValue(final String valueAsString) throws Exception {
        Constructor<FactoryBean<OutputClazz>> constructor = ClassUtils.getConstructorIfAvailable(outputClazzFactoryClazz, String.class);
        FactoryBean<OutputClazz> outputObject = BeanUtils.instantiateClass(constructor, valueAsString);
        return outputObject.getObject();
    }

    private OutputClazz[] instantiateOutputClazzArray(final int arraySize) {
        Object array = Array.newInstance(objectClasspath, arraySize);
        return (OutputClazz[]) array;
    }
}
