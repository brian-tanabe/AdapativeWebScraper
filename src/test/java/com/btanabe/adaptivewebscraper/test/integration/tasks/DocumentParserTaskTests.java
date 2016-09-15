package com.btanabe.adaptivewebscraper.test.integration.tasks;

import com.btanabe.adaptivewebscraper.models.YahooNflHistoricStatsModel;
import com.btanabe.adaptivewebscraper.tasks.DocumentParserTask;
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
 * Created by Brian on 9/14/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentParserTaskTests {

    @Autowired
    @Qualifier("russellWilsonDocumentParserTask")
    private DocumentParserTask<YahooNflHistoricStatsModel> russellWilsonDocumentParser;

    @Autowired
    @Qualifier("yahooPlayerStatsPageRussellWilson")
    private YahooNflHistoricStatsModel expectedRussellWilson;

    @Test
    public void shouldBeAbleToParseRussellWilsonsYahooStatsPageEntry() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();
        assertThat(russellWilsonFromCall, is(equalTo(expectedRussellWilson)));
    }

    @Test
    public void shouldBeAbleToParseRussellWilsonsReceptionsCorrectly() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();
        assertThat(russellWilsonFromCall.getTargets(), is(equalTo(expectedRussellWilson.getTargets())));
    }

    @Test
    public void shouldBeAbleToParseRussellWilsonsReceivingYardsCorrectly() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();
        assertThat(russellWilsonFromCall.getReceivingYards(), is(equalTo(expectedRussellWilson.getReceivingYards())));
    }

    @Test
    public void shouldBeAbleToParseRussellWilsonsReceivingYardsPerGameCorrectly() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();
        assertThat(russellWilsonFromCall.getReceivingYardsPerGame(), is(equalTo(expectedRussellWilson.getReceivingYardsPerGame())));
    }

    @Test
    public void shouldBeABleToParseRussellWilsonsReceivingYardsAfterCatchCorrectly() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();
        assertThat(russellWilsonFromCall.getReceivingYardsAfterCatch(), is(equalTo(expectedRussellWilson.getReceivingYardsAfterCatch())));
    }
}
