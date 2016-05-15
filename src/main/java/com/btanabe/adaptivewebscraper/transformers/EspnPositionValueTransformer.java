package com.btanabe.adaptivewebscraper.transformers;

import java.util.function.Function;

/**
 * Created by Brian on 5/14/16.
 */
public class EspnPositionValueTransformer implements Function<String, String> {

    @Override
    public String apply(String inputString) {
        final String teamNameAndPosition = inputString.replaceAll(",", "").trim();
        return teamNameAndPosition.substring(teamNameAndPosition.indexOf(" ")).trim();
    }
}
