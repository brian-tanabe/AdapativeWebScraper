package com.btanabe.adaptivewebscraper.transformers;

import java.util.function.Function;

/**
 * Created by Brian on 7/1/17.
 */
public class BasketballReferenceUrlPrefixAppender implements Function<String, String> {

    @Override
    public String apply(String urlSuffix) {

        // I only want to consider stats post-Jordan era:
        if(urlSuffix.contains("1998")) {
            return null;
        }

        return "http://www.basketball-reference.com" + urlSuffix;
    }
}
