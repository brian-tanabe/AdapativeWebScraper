package com.btanabe.adaptivewebscraper.web;

import com.btanabe.adaptivewebscraper.processors.HtmlTidier;
import lombok.Builder;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by Brian on 4/15/16.
 */
@Builder
public class WebRequestCallable implements Callable<Document> {
    private String urlToDownload;

    @Override
    public Document call() throws Exception {
        return Jsoup.parse(HtmlTidier.tidyHtmlAndConvertToXhtml(IOUtils.toString(new URL(urlToDownload).openStream(), "utf-8")));
    }
}
