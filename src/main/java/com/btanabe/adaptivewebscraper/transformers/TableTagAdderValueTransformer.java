package com.btanabe.adaptivewebscraper.transformers;

import java.util.function.Function;

/**
 * Created by Brian on 5/14/16.
 */
public class TableTagAdderValueTransformer implements Function<String, String> {

    @Override
    public String apply(String inputString) {
        return "<table>" + inputString + "</table>";
    }
}
