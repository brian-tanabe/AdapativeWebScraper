package com.btanabe.adaptivewebscraper.transformers;

import java.util.function.Function;

/**
 * Created by Brian on 5/15/16.
 */
public class NoNumberToZeroValueTransformer implements Function<String, String> {

    @Override
    public String apply(String inputString) {
        return inputString.replaceAll("--", "0");
    }
}
