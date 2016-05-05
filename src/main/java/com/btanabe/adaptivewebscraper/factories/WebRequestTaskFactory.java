package com.btanabe.adaptivewebscraper.factories;

import com.btanabe.adaptivewebscraper.processors.HtmlTidier;
import com.google.common.util.concurrent.ListenableFutureTask;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import java.net.URL;

/**
 * Created by Brian on 4/15/16.
 */
public class WebRequestTaskFactory {

    public static ListenableFutureTask createWebRequestTask(final String urlToDownload) {
        return ListenableFutureTask.create(() -> Jsoup.parse(HtmlTidier.tidyHtmlAndConvertToXhtml(IOUtils.toString(new URL(urlToDownload).openStream(), "utf-8"))));
    }
}
