package com.btanabe.adaptivewebscraper.factories.valueextractor;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jsoup.nodes.Document;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * Created by Brian on 9/11/16.
 */
@AllArgsConstructor
public class DefaultableValueExtractorFactory<OutputClazz> implements ValueExtractorFactoryI<OutputClazz> {

    @NonNull
    private final OutputClazz defaultValue;

    @Override
    public Callable<Stream<OutputClazz>> createValueExtractor(Document document) {
        return () -> Stream.of(defaultValue);
    }
}
