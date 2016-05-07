package com.btanabe.adaptivewebscraper.factories;

import com.btanabe.adaptivewebscraper.tasks.WebRequestTask;

/**
 * Created by Brian on 5/6/16.
 */
public class WebRequestTaskFactory {

    public static WebRequestTask createWebRequestTask(final String urlToDownload) {
        return new WebRequestTask(urlToDownload);
    }
}
