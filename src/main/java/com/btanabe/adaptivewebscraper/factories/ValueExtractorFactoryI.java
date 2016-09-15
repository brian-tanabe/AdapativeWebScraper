package com.btanabe.adaptivewebscraper.factories;

import org.jsoup.nodes.Document;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * Created by Brian on 9/11/16.
 */
public interface ValueExtractorFactoryI<OutputClazz> {
    Callable<Stream<OutputClazz>> createValueExtractor(final Document document);
}
