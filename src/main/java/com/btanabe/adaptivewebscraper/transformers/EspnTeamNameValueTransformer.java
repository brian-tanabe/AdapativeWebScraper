package com.btanabe.adaptivewebscraper.transformers;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by Brian on 5/14/16.
 */
@Setter
@RequiredArgsConstructor
public class EspnTeamNameValueTransformer implements Function<String, String> {

    private final Map<String, String> teamNameDictionary;

    @Override
    public String apply(String inputString) {
        return Splitter.on(CharMatcher.anyOf(", ")).omitEmptyStrings().trimResults().splitToList(inputString).stream().filter(teamName -> teamNameDictionary.containsKey(teamName)).findFirst().orElse("FA");
    }
}
