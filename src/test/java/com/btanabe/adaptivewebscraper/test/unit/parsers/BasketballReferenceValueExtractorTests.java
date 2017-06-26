package com.btanabe.adaptivewebscraper.test.unit.parsers;

import com.btanabe.adaptivewebscraper.factories.valueextractor.DynamicValueExtractorFactory;
import com.btanabe.adaptivewebscraper.models.BasketballReferenceSeasonTotalsModel;
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

@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BasketballReferenceValueExtractorTests {

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsNameValueExtractorFactory")
    private DynamicValueExtractorFactory<String> nameValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReference2017SeasonTotalsNikolaJokic")
    private Document basketballReferenceStatsPageNikolaJokic;

    @Autowired
    @Qualifier("basketballReference2017SeasonTotals")
    private Document basketballReferenceSeasonTotals;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotals2017NikolaJokic")
    private BasketballReferenceSeasonTotalsModel expectedNikolaJokicModel;

    @Test
    public void shouldBeAbleToExtractNamesFromPlayerDocuments() throws Exception {
        assertThat(nameValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findFirst().get(), is(equalTo(expectedNikolaJokicModel.getName())));
    }
}
