package com.btanabe.adaptivewebscraper.transformers;

import java.util.function.Function;

/**
 * Created by Brian on 5/14/16.
 *
 * Use an empty list of ValueTransformers instead of passing the same value through
 */
@Deprecated
public class PassThroughValueTransformer implements Function<String, String> {

    @Override
    public String apply(String inputString) {
        return inputString;
    }
}
