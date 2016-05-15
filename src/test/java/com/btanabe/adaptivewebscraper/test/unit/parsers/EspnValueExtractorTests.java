package com.btanabe.adaptivewebscraper.test.unit.parsers;

import com.btanabe.adaptivewebscraper.factories.ValueExtractorFactory;
import com.btanabe.adaptivewebscraper.models.EspnNflProjectionModel;
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
public class EspnValueExtractorTests {

    @Autowired
    @Qualifier("espnProjectionsPageRankValueExtractorFactory")
    private ValueExtractorFactory<Integer> rankValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsNameValueExtractorFactory")
    private ValueExtractorFactory<String> nameValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsTeamValueExtractorFactory")
    private ValueExtractorFactory<String> teamValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPositionValueExtractorFactory")
    private ValueExtractorFactory<String> positionValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsRushingYardsValueExtractorFactory")
    private ValueExtractorFactory<Integer> rushingYardsProjectionValueExtractorFactory;

    @Autowired
    @Qualifier("espnPlayerProjectionRowValueExtractorFactory")
    private ValueExtractorFactory<Document> playerProjectionRowValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPageNextPageValueExtractorFactory")
    private ValueExtractorFactory<String> nextPageValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPageEddieLacyDocument")
    private Document espnProjectionsPageEddieLacy;

    @Autowired
    @Qualifier("espnNflProjectionsPageOneFormatted")
    private Document espnProjectionsPageOne;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageEddieLacy")
    private EspnNflProjectionModel expectedEddieLacyModel;

    @Autowired
    @Qualifier("espnProjectionsPageNameSelectorStatement")
    private String playerNameSelectorStatement;

    @Test
    public void shouldBeAbleToExtractRanksFromPlayerDocuments() throws Exception {
        assertThat(rankValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getRank())));
    }

    @Test
    public void shouldBeAbleToExtractNamesFromPlayerDocuments() throws Exception {
        assertThat(nameValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getName())));
    }

    @Test
    public void shouldBeAbleToExtractTeamNamesFromPlayerDocuments() throws Exception {
        assertThat(teamValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getTeam())));
    }

    @Test
    public void shouldBeAbleToExtractPositionsFromPlayerDocuments() throws Exception {
        assertThat(positionValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo((String) expectedEddieLacyModel.getPosition())));
    }

    @Test
    public void shouldBeAbleToExtractIntegersFromAWebPage() throws Exception {
        assertThat(rushingYardsProjectionValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getRushingYards())));
    }

    @Test
    public void shouldBeAbleToExtractLikeIntegersFromMoreThanOneHtmlElementOnAPage() throws Exception {
        Stream<Document> playerProjectionDocumentStream = playerProjectionRowValueExtractorFactory.createValueExtractor(espnProjectionsPageOne).call();
        assertThat(playerProjectionDocumentStream.count(), is(equalTo(40L)));
    }

    @Test
    public void shouldBeAbleToPopulateOutputObjectsProperly() throws Exception {
        Stream<Document> playerProjectionDocumentStream = playerProjectionRowValueExtractorFactory.createValueExtractor(espnProjectionsPageOne).call();
        assertThat(playerProjectionDocumentStream.anyMatch(document -> document.select(playerNameSelectorStatement).text().contains(expectedEddieLacyModel.getName())), is(true));
    }

    @Test
    public void shouldBeAbleToFindTheLinkToTheNextPage() throws Exception {
        Stream<String> nextPageLinkStream = nextPageValueExtractorFactory.createValueExtractor(espnProjectionsPageOne).call();
        assertThat(nextPageLinkStream.findFirst().get(), is(equalTo("http://games.espn.go.com/ffl/tools/projections?leagueId=84978&startIndex=40")));
    }
}
