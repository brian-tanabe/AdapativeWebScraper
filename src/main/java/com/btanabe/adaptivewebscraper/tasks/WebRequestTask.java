package com.btanabe.adaptivewebscraper.tasks;

import com.btanabe.adaptivewebscraper.processors.HtmlTidier;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URL;
import java.util.concurrent.Callable;

import static com.google.common.base.Charsets.UTF_8;

/**
 * Created by Brian on 4/15/16.
 */
@AllArgsConstructor
public class WebRequestTask implements Callable<Document> {
    protected final String urlToDownload;

    @Override
    public Document call() throws Exception {
        return Jsoup.parse(HtmlTidier.tidyHtmlAndConvertToXhtml(IOUtils.toString(new URL(urlToDownload).openStream(), UTF_8)));
    }
}
