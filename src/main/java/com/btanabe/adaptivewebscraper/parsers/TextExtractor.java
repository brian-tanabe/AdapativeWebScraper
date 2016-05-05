package com.btanabe.adaptivewebscraper.parsers;

import org.jsoup.nodes.Element;

/**
 * Created by Brian on 5/4/16.
 */
public class TextExtractor<OutputClazz> extends ValueExtractor<OutputClazz> {

    @Override
    protected String createFactoryConstructorStringArgument(Element element) {
        return element.text();
    }
}
