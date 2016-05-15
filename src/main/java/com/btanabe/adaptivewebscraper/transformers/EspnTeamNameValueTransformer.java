package com.btanabe.adaptivewebscraper.transformers;

import com.google.common.base.Splitter;

import java.util.function.Function;

/**
 * Created by Brian on 5/14/16.
 */
public class EspnTeamNameValueTransformer implements Function<String, String> {

    @Override
    public String apply(String inputString) {
        return Splitter.on(" ").omitEmptyStrings().trimResults().split(inputString.replace(",", "")).iterator().next();
    }
}
