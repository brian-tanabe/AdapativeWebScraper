package com.btanabe.adaptivewebscraper.test.unit.parsers;

import com.btanabe.adaptivewebscraper.parsers.ValueExtractor;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 4/5/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ValueExtractorTests {

    @Autowired
    @Qualifier("espnProjectionsRushingYardsValueExtractor")
    private ValueExtractor<Integer> testIntegerValueExtraction;

    @Autowired
    @Qualifier("espnProjectionsPageEddieLacy")
    private Document espnProjectionsPageEddieLacy;

    @Test
    public void shouldBeAbleToExtractIntegersFromAWebPage() throws Exception {
        testIntegerValueExtraction.setDocument(espnProjectionsPageEddieLacy);
        assertThat(testIntegerValueExtraction.call(), is(equalTo(293)));
    }
}
