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
public abstract class ValueExtractor<OutputClazz> implements Callable<Stream<OutputClazz>> {

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    private Class<OutputClazz> objectClasspath;

    @Setter(onMethod = @__({@Autowired}))
    private Class<FactoryBean<OutputClazz>> outputClazzFactoryClazz;

    @Setter
    @NonNull
    private Document document;

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    private String xpathSelector;

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

    protected abstract String createFactoryConstructorStringArgument(final Element element);

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
