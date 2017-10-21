package com.btanabe.adaptivewebscraper.test.sandbox;

import com.btanabe.adaptivewebscraper.factories.valueextractor.DynamicValueExtractorFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Document;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.net.URL;

import static com.google.common.base.Charsets.UTF_8;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 9/16/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestWebpageDownloader {

    @Autowired
    @Qualifier("yahooPlayerStatPagePlayerRowValueExtractor")
    private String playerDocumentValueExtractor;

    @Autowired
    @Qualifier("yahooPlayerStatsRowValueExtractorFactory")
    private DynamicValueExtractorFactory<Document> playerRowDocumentValueExtractor;

    @Autowired
    @Qualifier("yahooStatsPageQuarterbacks2015")
    private Document quarterbackPage;

    @Ignore
    @Test
    public void shouldBeAbleToIncludeTheYearInPlayerDocumentRows() throws Exception {
        playerRowDocumentValueExtractor.setXpathSelector("select[name = year] > option[selected]");
        Document playerRowDocument = playerRowDocumentValueExtractor.createValueExtractor(quarterbackPage).call().findFirst().get();

        String pageMarkup = playerRowDocument.html();
        assertThat(pageMarkup, containsString("season_2015"));
        assertThat(pageMarkup, containsString("Russell Wilson"));
    }

    @Ignore
    @Test
    public void shouldBeAbleToDownloadOneWebpage() throws Exception {
        for (int year = 2012; year > 1999; year--) {
            String webpage = String.format("https://sports.yahoo.com/nfl/stats/byposition?pos=QB&conference=NFL&year=season_%d&timeframe=All&qualified=1&sort=49&old_category=QB", year);
            File outputFile = new File(String.format("./src/test/resources/test-webpages/yahoo/stat-pages/qb/yahoo-player-stats-qb-%d.html", year));
            String pageContent = IOUtils.toString(new URL(webpage).openStream(), UTF_8);
            FileUtils.writeStringToFile(outputFile, pageContent, UTF_8, false);
        }
    }
}
