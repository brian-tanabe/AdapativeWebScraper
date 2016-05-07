package com.btanabe.adaptivewebscraper.test.unit.parsers;

import com.btanabe.adaptivewebscraper.parsers.ValueExtractor;
import com.btanabe.adaptivewebscraper.utilities.SelectorStatementBuilder;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Stream;

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
    private ValueExtractor<Integer> rushingYardsProjectionValueExtractor;

    @Autowired
    @Qualifier("espnPlayerProjectionRowValueExtractor")
    private ValueExtractor<Document> playerProjectionRowValueExtractor;

    @Autowired
    @Qualifier("espnProjectionsPageNextPageValueExtractor")
    private ValueExtractor<String> nextPageValueExtractor;

    @Autowired
    @Qualifier("espnProjectionsPageEddieLacyDocument")
    private Document espnProjectionsPageEddieLacy;

    @Autowired
    @Qualifier("espnNflProjectionsPageOneFormatted")
    private Document espnProjectionsPageOne;

    @Test
    public void shouldBeAbleToExtractIntegersFromAWebPage() throws Exception {
        rushingYardsProjectionValueExtractor.setDocument(espnProjectionsPageEddieLacy);
        assertThat(rushingYardsProjectionValueExtractor.call().findFirst().get(), is(equalTo(293)));
    }

    @Test
    public void shouldBeAbleToExtractLikeIntegersFromMoreThanOneHtmlElementOnAPage() throws Exception {
        playerProjectionRowValueExtractor.setDocument(espnProjectionsPageOne);

        Stream<Document> playerProjectionDocumentStream = playerProjectionRowValueExtractor.call();
        assertThat(playerProjectionDocumentStream.count(), is(equalTo(40L)));
    }

    @Test
    public void shouldBeAbleToPopulateOutputObjectsProperly() throws Exception {
        playerProjectionRowValueExtractor.setDocument(espnProjectionsPageOne);

        Stream<Document> playerProjectionDocumentStream = playerProjectionRowValueExtractor.call();
        final String playerNameSelectorStatement = SelectorStatementBuilder.builder().tagName("a").className("flexpop").build().getObject();
        assertThat(playerProjectionDocumentStream.anyMatch(document -> document.select(playerNameSelectorStatement).text().contains("Antonio Brown")), is(true));
    }

    @Test
    public void shouldBeAbleToFindTheLinkToTheNextPage() throws Exception {
        nextPageValueExtractor.setDocument(espnProjectionsPageOne);

        Stream<String> nextPageLinkStream = nextPageValueExtractor.call();
        assertThat(nextPageLinkStream.findFirst().get(), is(equalTo("http://games.espn.go.com/ffl/tools/projections?leagueId=84978&startIndex=40")));
    }
}
