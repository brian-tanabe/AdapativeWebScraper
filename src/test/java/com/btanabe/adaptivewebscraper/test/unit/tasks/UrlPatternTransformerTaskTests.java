package com.btanabe.adaptivewebscraper.test.unit.tasks;

import com.btanabe.adaptivewebscraper.factories.DynamicValueExtractorFactory;
import com.btanabe.adaptivewebscraper.tasks.UrlPatternTransformerTask;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 7/9/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UrlPatternTransformerTaskTests {

    @Autowired
    @Qualifier("yahooStatsPageNextPageValueExtractorFactory")
    private DynamicValueExtractorFactory<String> nextPageValueExtractorFactory;

    @Autowired
    @Qualifier("yahooStatsPageRunningBacks2015")
    private Document yahooStatsPageRunningBacks;

    private final String yahooRunningBacksUrlPattern = "https://sports.yahoo.com/nfl/stats/byposition?pos=RB&conference=NFL&year=%s&timeframe=All&sort=17&old_category=RB";

    @Test
    public void shouldBeAbleToCreateUrlsWithPatternTemplates() throws Exception {
        assertThat(new UrlPatternTransformerTask(nextPageValueExtractorFactory.createValueExtractor(yahooStatsPageRunningBacks), yahooRunningBacksUrlPattern).call().findFirst().get(), is(equalTo("https://sports.yahoo.com/nfl/stats/byposition?pos=RB&conference=NFL&year=season_2014&timeframe=All&sort=17&old_category=RB")));
    }
}
