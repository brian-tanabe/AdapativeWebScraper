package com.btanabe.adaptivewebscraper.collectors;

import com.btanabe.adaptivewebscraper.factories.ValueExtractorFactory;
import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.tasks.DocumentParserTask;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import lombok.NonNull;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
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
public class RecordCollector<OutputType> {

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private ListeningExecutorService executorService;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private String seedWebPage;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private ValueExtractorFactory<String> nextPageValueExtractorFactory;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private ValueExtractorFactory<Document> recordDocumentExtractorFactory;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private Map<ValueExtractorFactory, String> valueExtractorFactoryToSetterMethodNameMap;

    @NonNull
    @Setter(onMethod = @__({@Autowired}))
    private Class<OutputType> outputClassPath;

    @Setter
    private EventBus eventBus;

    public void gatherAllRecords() throws ExecutionException, InterruptedException {
        final List<ListenableFuture<OutputType>> downloadParseAndMarshallFutures = Lists.newArrayList();
        String pageUrl = seedWebPage;
        do {
            pageUrl = generateAllDownloadParseAndMarshallTasksForPageAndReturnTheUrlToTheNextPage(pageUrl, downloadParseAndMarshallFutures);
        } while (pageUrl != null);

        downloadParseAndMarshallFutures.forEach(future -> {
            try {
                eventBus.post(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Consider making this a recursive function:
    private String generateAllDownloadParseAndMarshallTasksForPageAndReturnTheUrlToTheNextPage(final String pageUrl, final List<ListenableFuture<OutputType>> outputTypeFutures) throws ExecutionException, InterruptedException {
        // Step 1: Download Page HTML
        ListenableFuture webPageDownloadFuture = executorService.submit(WebRequestTaskFactory.createWebRequestTask(pageUrl));

        // Step 2: Find the link to the next page:
        AsyncFunction<Document, Stream<String>> nextPageUrlFunction = input -> {
            return executorService.submit(nextPageValueExtractorFactory.createValueExtractor(input));
        };

        ListenableFuture<Stream<String>> nextPageUrlFuture = Futures.transformAsync(webPageDownloadFuture, nextPageUrlFunction);

        // Step 3: Extract all Elements into their own Document:
        AsyncFunction<Document, Stream<Document>> recordPartitioningFunction = input -> {
            return executorService.submit(recordDocumentExtractorFactory.createValueExtractor(input));
        };

        // Gather all Documents:
        Stream<Document> allPlayersInTheirOwnDocumentStream = (Stream<Document>) Futures.transformAsync(webPageDownloadFuture, recordPartitioningFunction, executorService).get();

        // Step 4: Parse each record:
        allPlayersInTheirOwnDocumentStream.forEach(recordDocument -> {
            DocumentParserTask<OutputType> task = new DocumentParserTask<OutputType>(executorService, recordDocument, valueExtractorFactoryToSetterMethodNameMap, outputClassPath);
            outputTypeFutures.add(executorService.submit(task));
        });

        // Get the next page URL and return:
        return nextPageUrlFuture.get().findFirst().orElse(null);
    }
}
