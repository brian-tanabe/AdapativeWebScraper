package com.btanabe.adaptivewebscraper.test.unit.parsers;

import com.btanabe.adaptivewebscraper.factories.ValueExtractorFactory;
import com.btanabe.adaptivewebscraper.models.YahooNflHistoricStatsModel;
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
 * Created by Brian on 7/3/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class YahooValueExtractorTests {

    @Autowired
    @Qualifier("yahooStatsNameValueExtractorFactory")
    private ValueExtractorFactory<String> nameValueExtractor;

    @Autowired
    @Qualifier("yahooStatsPageAdrianPeterson")
    private Document yahooStatsPageAdrianPeterson;

    @Autowired
    @Qualifier("yahooPlayerStatsPageAdrianPeterson")
    private YahooNflHistoricStatsModel expectedAdrianPetersonModel;

    @Test
    public void shouldBeAbleToExtractNamesFromPlayerDocuments() throws Exception {
        assertThat(nameValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getName())));
    }
}
