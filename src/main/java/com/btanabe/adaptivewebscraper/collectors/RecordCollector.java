package com.btanabe.adaptivewebscraper.collectors;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.parsers.ValueExtractor;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.stream.Collectors;
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
 */
@AllArgsConstructor
public class RecordCollector<OutputType> {
    private final ListeningExecutorService executorService;

    private final String seedWebPage;
    private final WebRequestTaskFactory webRequestTaskFactory;
    private final ValueExtractor<String> nextPageValueExtractor;
    private final ValueExtractor<Document> recordDocumentExtractor;

    public List<OutputType> getAllRecordsAsList() {
        List<OutputType> recordList = Lists.newArrayList();

        String pageUrl = seedWebPage;
        do {
            recordList.addAll(getAllRecordsOnPage(seedWebPage).collect(Collectors.toList()));

        } while (pageUrl != null);

        return recordList;
    }

    private Stream<OutputType> getAllRecordsOnPage(final String pageUrl) {
        // Step 1: Download Page HTML
        ListenableFuture webPageDownloadFuture = executorService.submit(webRequestTaskFactory.createWebRequestTask(seedWebPage));

        // Step 2: Extract all Elements into their own Document:
        AsyncFunction<Document, Stream<Document>> recordPartitioningFunction = input -> {
            recordDocumentExtractor.setDocument(input);
            return executorService.submit(recordDocumentExtractor);
        };

        return null;
    }
}
