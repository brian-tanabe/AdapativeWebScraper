package com.btanabe.adaptivewebscraper.transformers;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by Brian on 5/15/16.
 */
@Setter
@AllArgsConstructor
public class NflTeamNameValueTransformer implements Function<String, String> {

    private Map<String, String> teamNameDictionary;

    @Override
    public String apply(String inputTeamName) {
        return teamNameDictionary.get(inputTeamName);
    }
}
