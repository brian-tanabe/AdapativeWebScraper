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
    @Qualifier("espnProjectionsPlayerIdValueExtractorFactory")
    private ValueExtractorFactory<String> playerIdValueExtractorFactory;

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
    @Qualifier("espnProjectionsPassingCompletionsValueExtractorFactory")
    private ValueExtractorFactory<Integer> passingCompletionsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPassingAttemptsValueExtractorFactory")
    private ValueExtractorFactory<Integer> passingAttemptsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPassingYardsValueExtractorFactory")
    private ValueExtractorFactory<Integer> passingYardsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPassingTouchdownsValueExtractorFactory")
    private ValueExtractorFactory<Integer> passingTouchdownsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsInterceptionsValueExtractorFactory")
    private ValueExtractorFactory<Integer> interceptionsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsRushingAttemptsValueExtractorFactory")
    private ValueExtractorFactory<Integer> rushingAttemptsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsRushingYardsValueExtractorFactory")
    private ValueExtractorFactory<Integer> rushingYardsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsRushingTouchdownsValueExtractorFactory")
    private ValueExtractorFactory<Integer> rushingTouchdownsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsReceptionsValueExtractorFactory")
    private ValueExtractorFactory<Integer> receptionsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsReceivingYardsValueExtractorFactory")
    private ValueExtractorFactory<Integer> receivingYardsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsReceivingTouchdownsValueExtractorFactory")
    private ValueExtractorFactory<Integer> receivingTouchdownsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsFantasyPointsValueExtractorFactory")
    private ValueExtractorFactory<Double> fantasyPointsValueExtractorFactory;

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

    @Test
    public void shouldBeAbleToExtractPlayerIdFromPlayerDocuments() throws Exception {
        assertThat(playerIdValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getPlayerId())));
    }

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
    public void shouldBeAbleToExtractPassingCompletionsFromPlayerDocuments() throws Exception {
        assertThat(passingCompletionsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getPassingCompletions())));
    }

    @Test
    public void shouldBeAbleToExtractPassingAttemptsFromPlayerDocuments() throws Exception {
        assertThat(passingAttemptsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getPassingAttempts())));
    }

    @Test
    public void shouldBeAbleToExtractPassingYardsFromPlayerDocuments() throws Exception {
        assertThat(passingYardsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getPassingYards())));
    }

    @Test
    public void shouldBeAbleToExtractPassingTouchdownsFromPlayerDocuments() throws Exception {
        assertThat(passingTouchdownsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getPassingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractInterceptionsFromPlayerDocuments() throws Exception {
        assertThat(interceptionsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getInterceptions())));
    }

    @Test
    public void shouldBeAbleToExtractRushingAttemptsFromPlayerDocuments() throws Exception {
        assertThat(rushingAttemptsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getRushingAttempts())));
    }

    @Test
    public void shouldBeAbleToExtractRushingYardsFromPlayerDocuments() throws Exception {
        assertThat(rushingYardsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getRushingYards())));
    }

    @Test
    public void shouldBeAbleToExtractRushingTouchdownsFromPlayerDocuments() throws Exception {
        assertThat(rushingTouchdownsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getRushingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractReceptionsFromPlayerDocuments() throws Exception {
        assertThat(receptionsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getReceptions())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsFromPlayerDocuments() throws Exception {
        assertThat(receivingYardsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getReceivingYards())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingTouchdownsFromPlayerDocuments() throws Exception {
        assertThat(receivingTouchdownsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getReceivingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractFantasyPointsFromPlayerDocuments() throws Exception {
        assertThat(fantasyPointsValueExtractorFactory.createValueExtractor(espnProjectionsPageEddieLacy).call().findFirst().get(), is(equalTo(expectedEddieLacyModel.getFantasyPoints())));
    }

    @Test
    public void shouldBeAbleToExtractLikeIntegersFromMoreThanOneHtmlElementOnAPage() throws Exception {
        assertThat(playerProjectionRowValueExtractorFactory.createValueExtractor(espnProjectionsPageOne).call().count(), is(equalTo(40L)));
    }

    @Test
    public void shouldBeAbleToExtractNextPageLinkFromPlayerProjectionPageDocument() throws Exception {
        assertThat(nextPageValueExtractorFactory.createValueExtractor(espnProjectionsPageOne).call().findFirst().get(), is(equalTo("http://games.espn.com/ffl/tools/projections?leagueId=84978&startIndex=40")));
    }
}
