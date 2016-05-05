package com.btanabe.adaptivewebscraper.collectors;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by Brian on 5/1/16.
 */
public class RecordCollector<OutputType> {
    private final ExecutorService executorService;

    public RecordCollector(final ExecutorService executorService) {
        this.executorService = executorService;
    }

    public List<OutputType> getAllRecordsAsList() {
        ListenableFutureTask downloadHtmlTask = WebRequestTaskFactory.createWebRequestTask("http://games.espn.go.com/ffl/tools/projections?leagueId=84978");
        executorService.submit(downloadHtmlTask);
//        downloadHtmlTask.addListener();

        return new ArrayList<>();
    }
}
