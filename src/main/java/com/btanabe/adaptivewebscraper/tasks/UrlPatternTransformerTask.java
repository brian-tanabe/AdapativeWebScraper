package com.btanabe.adaptivewebscraper.tasks;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * Created by Brian on 7/9/16.
 */
@RequiredArgsConstructor
public class UrlPatternTransformerTask implements Callable<Stream<String>> {

    private final Callable<Stream<String>> urlArgumentExtractor;
    private final String urlPattern;

    @Override
    public Stream<String> call() throws Exception {
        List<String> listOfUrls = new ArrayList<>();

        String nextPageUrlBase = urlArgumentExtractor.call().findFirst().orElse(null);
        if (nextPageUrlBase != null) {
            listOfUrls.add(String.format(urlPattern, nextPageUrlBase));
        }

        return listOfUrls.stream();
    }
}
