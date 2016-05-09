package com.btanabe.adaptivewebscraper.parsers;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * Created by Brian on 4/5/16.
 */
public class ValueExtractor<OutputClazz> implements Callable<Stream<OutputClazz>> {

    @Setter
    @NonNull
    protected Document document;

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    protected Class<OutputClazz> objectClasspath;

    @Setter(onMethod = @__({@Autowired}))
    protected Class<FactoryBean<OutputClazz>> outputClazzFactoryClazz;

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    protected String xpathSelector;

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    private String textGetterMethodName;

    @Setter(onMethod = @__({@Autowired}))
    private Class<?>[] textGetterMethodParameterTypes;

    @Setter(onMethod = @__({@Autowired}))
    private Object[] textGetterMethodParameters;

    @Override
    public Stream<OutputClazz> call() throws Exception {
        return Stream.of(getAllOccurrencesAsString());
    }

    private OutputClazz[] getAllOccurrencesAsString() throws Exception {
        List<OutputClazz> matchedElements = Lists.newArrayList();
        document.select(xpathSelector).stream().forEach(element -> {

            try {
                matchedElements.add(createOutputClassAndSetItsValue(createFactoryConstructorStringArgument(element)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });

        return matchedElements.toArray(instantiateOutputClazzArray(matchedElements.size()));
    }

    protected String createFactoryConstructorStringArgument(final Element element) throws Exception {
        String value = (String) ClassUtils.getMethod(Element.class, textGetterMethodName, textGetterMethodParameterTypes).invoke(element, textGetterMethodParameters);
        System.out.println(String.format("value=[%s], elementSiblingIndex=[%d], siblingIndex=[%d]", value, element.elementSiblingIndex(), element.siblingIndex()));

        return (String) ClassUtils.getMethod(Element.class, textGetterMethodName, textGetterMethodParameterTypes).invoke(element, textGetterMethodParameters);
    }

    protected OutputClazz createOutputClassAndSetItsValue(final String valueAsString) throws Exception {
        Constructor<FactoryBean<OutputClazz>> constructor = ClassUtils.getConstructorIfAvailable(outputClazzFactoryClazz, String.class);
        FactoryBean<OutputClazz> outputObject = BeanUtils.instantiateClass(constructor, valueAsString);
        return outputObject.getObject();
    }

    protected OutputClazz[] instantiateOutputClazzArray(final int arraySize) {
        Object array = Array.newInstance(objectClasspath, arraySize);
        return (OutputClazz[]) array;
    }
}
