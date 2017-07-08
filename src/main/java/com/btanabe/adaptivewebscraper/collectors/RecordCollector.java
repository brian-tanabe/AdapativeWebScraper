package com.btanabe.adaptivewebscraper.collectors;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.factories.outputobject.MultimapObjectSetter;
import com.btanabe.adaptivewebscraper.factories.outputobject.MultimapOutputObjectConstructor;
import com.btanabe.adaptivewebscraper.factories.outputobject.OutputObjectConstructorI;
import com.btanabe.adaptivewebscraper.factories.outputobject.OutputObjectSetterI;
import com.btanabe.adaptivewebscraper.factories.valueextractor.DynamicValueExtractorFactory;
import com.btanabe.adaptivewebscraper.factories.valueextractor.ValueExtractorFactoryI;
import com.btanabe.adaptivewebscraper.tasks.DocumentParserTask;
import com.btanabe.adaptivewebscraper.tasks.UrlPatternTransformerTask;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * Created by Brian on 5/1/16.
 * <p>
 * This class needs to:
 * 1) Download the web page (seedWebPage) using a new WebRequestTask (main thread: t[m])
 * 2) Extract all elements in their own document as its own task     (main thread: t[m])
 * 3) In parallel, pull all relevant fields out of each document     (Each its own thread: t[0...n])
 * 4) With all fields and values, populate the output object         (Same thread as before: t[0...n])
 * 5) Find the link to the next page                                 (main thread: t[m])
 * 6) Repeat steps 2 through 5 until the next page link is null      (Each its own thread: t[n+1...2n])
 * 5) Join and return
 * <p>
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class RecordCollector<OutputType> implements Callable<Void> {

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private ListeningExecutorService executorService;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private String seedWebPage;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private ValueExtractorFactoryI<String> nextPageValueExtractorFactory;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private String nextPageUrlPattern;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private DynamicValueExtractorFactory<Document> recordDocumentExtractorFactory;

    @Setter(onMethod = @__({@Autowired}))
    private Multimap<ValueExtractorFactoryI, String> globalValueExtractorFactoryToSetterMethodNameMap;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private Multimap<ValueExtractorFactoryI, String> valueExtractorFactoryToSetterMethodNameMap;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private OutputObjectConstructorI<OutputType> outputObjectConstructor;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private OutputObjectSetterI<OutputType> outputObjectSetter;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private EventBus eventBus;

    @Override
    public Void call() throws Exception {

        log.info(String.format("RecordCollector for seed URL=[%s] starting", seedWebPage));

        gatherAllRecords();

        log.info(String.format("RecordCollector for seed URL=[%s] complete", seedWebPage));

        return null;
    }

    public void gatherAllRecords() throws Exception {
        final List<ListenableFuture<OutputType>> downloadParseAndMarshallFutures = new ArrayList<>();
        String pageUrl = seedWebPage;
        do {
            log.info(String.format("Collecting records for URL=[%s]", pageUrl));
            pageUrl = generateAllDownloadParseAndMarshallTasksForPageAndReturnTheUrlToTheNextPage(pageUrl, downloadParseAndMarshallFutures);
        } while (pageUrl != null);

        downloadParseAndMarshallFutures.forEach(future -> {
            try {
                eventBus.post(future.get());
            } catch (Exception e) {
                log.error(ExceptionUtils.getMessage(e));
                log.error(ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
        });
    }

    // Consider making this a recursive function:
    private String generateAllDownloadParseAndMarshallTasksForPageAndReturnTheUrlToTheNextPage(final String pageUrl, final List<ListenableFuture<OutputType>> outputTypeFutures) throws Exception {

        // Make a copy of the ValueExtractor -> setter method name map.  The global ValueExtractors are added to this new map for each page:
        Multimap<ValueExtractorFactoryI, String> valueExtractorFactoryToSetterMethodNameMap = LinkedHashMultimap.create(this.valueExtractorFactoryToSetterMethodNameMap);

        // Step 1: Download Page HTML
        ListenableFuture<Document> webPageDownloadFuture = executorService.submit(WebRequestTaskFactory.createWebRequestTask(pageUrl));

        // Step 2: Find the link to the next page:
        AsyncFunction<Document, Stream<String>> nextPageUrlFunction = input -> executorService.submit(new UrlPatternTransformerTask(nextPageValueExtractorFactory.createValueExtractor(input), nextPageUrlPattern));
        ListenableFuture<Stream<String>> nextPageUrlFuture = Futures.transformAsync(webPageDownloadFuture, nextPageUrlFunction);

        // Step 2: Parse the entire document and create the global ValueExtractors:
        AsyncFunction<Document, Multimap<ValueExtractorFactoryI<String>, String>> globalValueExtractorFunction = input -> executorService.submit(new DocumentParserTask<>(executorService, input, globalValueExtractorFactoryToSetterMethodNameMap, new MultimapOutputObjectConstructor<ValueExtractorFactoryI, String>(ValueExtractorFactoryI.class, String.class), new MultimapObjectSetter()));
        ListenableFuture<Multimap<ValueExtractorFactoryI<String>, String>> globalValueExtractorsFuture = Futures.transformAsync(webPageDownloadFuture, globalValueExtractorFunction, executorService);

        // Step 2: Extract all Elements into their own Document:
        AsyncFunction<Document, Stream<Document>> recordPartitioningFunction = input -> {
            return executorService.submit(recordDocumentExtractorFactory.createValueExtractor(input));
        };

        // Step 3: Gather all Documents:
        Stream<Document> allPlayersInTheirOwnDocumentStream = Futures.transformAsync(webPageDownloadFuture, recordPartitioningFunction, executorService).get();

        // Step 4: Add the global value extractors to the ValueExtractor map:
        Multimap<ValueExtractorFactoryI<String>, String> globalValues = globalValueExtractorsFuture.get();
        valueExtractorFactoryToSetterMethodNameMap.putAll(globalValues);

        // Step 5: Parse each record:
        allPlayersInTheirOwnDocumentStream.forEach(recordDocument -> outputTypeFutures.add(executorService.submit(new DocumentParserTask(executorService, recordDocument, valueExtractorFactoryToSetterMethodNameMap, outputObjectConstructor, outputObjectSetter))));

        // Get the next page URL and return:
        return nextPageUrlFuture.get().findFirst().orElse(null);
    }
}
