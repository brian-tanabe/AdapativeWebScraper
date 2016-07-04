package com.btanabe.adaptivewebscraper.transformers;

import com.google.common.base.Splitter;
import lombok.AllArgsConstructor;

import java.util.function.Function;

/**
 * Created by Brian on 7/4/16.
 * <p>
 * TODO COME UP WITH A BETTER NAME FOR THIS CLASS
 */
@AllArgsConstructor
public class UrlPathExploder implements Function<String, String> {
    private final Integer pathIndex;

    @Override
    public String apply(String urlPath) {
        return Splitter.on("/").trimResults().omitEmptyStrings().splitToList(urlPath).get(pathIndex);
    }
}
