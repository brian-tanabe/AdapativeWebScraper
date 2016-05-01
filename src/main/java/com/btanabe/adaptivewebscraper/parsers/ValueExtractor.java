package com.btanabe.adaptivewebscraper.parsers;

import lombok.NonNull;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;

import java.util.concurrent.Callable;

/**
 * Created by Brian on 4/5/16.
 */
public class ValueExtractor<OutputClazz> implements Callable<OutputClazz> {

    @Setter(onMethod = @__({@Autowired}))
    private Class<OutputClazz> objectClasspath;

    @Setter
    @NonNull
    private Document document;

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    private String xpathSelector;

    @Override
    public OutputClazz call() throws Exception {
        return BeanUtils.instantiateClass(ClassUtils.getConstructorIfAvailable(objectClasspath, String.class), getValueAsString());
    }

    private String getValueAsString() {
        Elements matchingElements = document.select(xpathSelector);
        Element firstElement = matchingElements.first();
        String data = firstElement.text();
        return data;
    }
}
