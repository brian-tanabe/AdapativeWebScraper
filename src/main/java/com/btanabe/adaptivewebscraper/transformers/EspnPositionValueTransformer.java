package com.btanabe.adaptivewebscraper.transformers;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Brian on 5/14/16.
 */
public class EspnPositionValueTransformer implements Function<String, String> {

    @Override
    public String apply(String inputString) {
        final Matcher positionMatcher = Pattern.compile("QB|RB|WR|TE|K|D/ST").matcher(inputString);
        return positionMatcher.find() ? positionMatcher.group() : null;
    }
}
