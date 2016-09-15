package com.btanabe.adaptivewebscraper.test.unit.parsers;

import com.btanabe.adaptivewebscraper.factories.ValueExtractorFactoryI;
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
    @Qualifier("yahooPlayerStatsRowValueExtractorFactory")
    private ValueExtractorFactoryI<Document> playerRowValueExtractor;

    @Autowired
    @Qualifier("yahooStatsPageNextPageValueExtractorFactory")
    private ValueExtractorFactoryI<String> nextPageValueExtractor;

    @Autowired
    @Qualifier("yahooStatsPlayerIdValueExtractorFactory")
    private ValueExtractorFactoryI<String> playerIdValueExtractor;

    @Autowired
    @Qualifier("yahooStatsNameValueExtractorFactory")
    private ValueExtractorFactoryI<String> nameValueExtractor;

    @Autowired
    @Qualifier("yahooStatsTeamValueExtractorFactory")
    private ValueExtractorFactoryI<String> teamValueExtractor;

    @Autowired
    @Qualifier("yahooStatsGamesPlayedValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> gamesPlayedValueExtractor;

    @Autowired
    @Qualifier("yahooStatsPassingCompletionsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> passingCompletionsValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRunningBacksRushingAttemptsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> rushingAttemptsRunningBacksValueExtractor;

    @Autowired
    @Qualifier("yahooStatsQuarterbacksRushingAttemptsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> rushingAttemptsQuarterbacksValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRunningBacksRushingYardsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> yahooStatsRunningBacksRushingYardsValueExtractorFactory;

    @Autowired
    @Qualifier("yahooStatsQuarterbacksRushingYardsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> yahooStatsQuarterbacksRushingYardsValueExtractorFactory;

    @Autowired
    @Qualifier("yahooStatsRunningBacksRushingYardsPerGameValueExtractorFactory")
    private ValueExtractorFactoryI<Double> rushingYardsPerGameRunningBackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsQuarterbacksRushingYardsPerGameValueExtractorFactory")
    private ValueExtractorFactoryI<Double> rushingYardsPerGameQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRushingYardsPerAttemptRunningBacksValueExtractorFactory")
    private ValueExtractorFactoryI<Double> rushingYardsPerAttemptRunningBackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRushingYardsPerAttemptQuarterbacksValueExtractorFactory")
    private ValueExtractorFactoryI<Double> rushingYardsPerAttemptQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRunningBacksRushingTouchdownsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> rushingTouchdownsRunningBacksValueExtractor;

    @Autowired
    @Qualifier("yahooStatsQuarterbacksRushingTouchdownsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> rushingTouchdownsQuarterbacksValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceptionsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> receptionsRunningBacksValueExtractor;

    @Autowired
    @Qualifier("zeroIntegerValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> receptionsQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsTargetsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> targetsRunningBackValueExtractor;

    @Autowired
    @Qualifier("zeroIntegerValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> targetsQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingYardsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> receivingYardsRunningBackValueExtractor;

    @Autowired
    @Qualifier("zeroIntegerValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> receivingYardsQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingYardsAfterCatchValueExtractorFactory")
    private ValueExtractorFactoryI<Double> yardsAfterCatchRunningBackValueExtractor;

    @Autowired
    @Qualifier("zeroDoubleValueExtractorFactory")
    private ValueExtractorFactoryI<Double> yardsAfterCatchQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingYardsPerGameValueExtractorFactory")
    private ValueExtractorFactoryI<Double> receivingYardsPerGameRunningBackValueExtractor;

    @Autowired
    @Qualifier("zeroDoubleValueExtractorFactory")
    private ValueExtractorFactoryI<Double> receivingYardsPerGameQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingYardsPerReceptionValueExtractorFactory")
    private ValueExtractorFactoryI<Double> receivingYardsPerReceptionRunningBackValueExtractor;

    @Autowired
    @Qualifier("zeroDoubleValueExtractorFactory")
    private ValueExtractorFactoryI<Double> receivingYardsPerReceptionQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingTouchdownsValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> receivingTouchdownsRunningBackValueExtractor;

    @Autowired
    @Qualifier("zeroIntegerValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> receivingTouchdownsQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRunningBacksFumblesValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> fumblesRunningBacksValueExtractor;

    @Autowired
    @Qualifier("yahooStatsQuarterbacksFumblesValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> fumblesQuarterbacksValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRunningBacksFumblesLostValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> fumblesLostRunningBacksValueExtractor;

    @Autowired
    @Qualifier("yahooStatsQuarterbacksFumblesLostValueExtractorFactory")
    private ValueExtractorFactoryI<Integer> fumblesLostQuarterbacksValueExtractor;

    @Autowired
    @Qualifier("yahooStatsQuarterbackPositionValueExtractorFactory")
    private ValueExtractorFactoryI<String> positionQuarterbackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRunningBackPositionValueExtractorFactory")
    private ValueExtractorFactoryI<String> positionRunningBackValueExtractor;

    @Autowired
    @Qualifier("yahooStatsWideReceiverPositionValueExtractorFactory")
    private ValueExtractorFactoryI<String> positionWideReceiverValueExtractor;

    @Autowired
    @Qualifier("yahooStatsTightEndPositionValueExtractorFactory")
    private ValueExtractorFactoryI<String> positionTightEndValueExtractor;

    @Autowired
    @Qualifier("yahooStatsKickerPositionValueExtractorFactory")
    private ValueExtractorFactoryI<String> positionKickerValueExtractor;

    @Autowired
    @Qualifier("yahooStatsPageAdrianPeterson")
    private Document yahooStatsPageAdrianPeterson;

    @Autowired
    @Qualifier("yahooStatsPageRunningBacks2015")
    private Document yahooStatsPageRunningBacks;

    @Autowired
    @Qualifier("yahooStatsPageRussellWilson")
    private Document yahooStatsPageRussellWilson;

    @Autowired
    @Qualifier("yahooPlayerStatsPageAdrianPeterson")
    private YahooNflHistoricStatsModel expectedAdrianPetersonModel;

    @Autowired
    @Qualifier("yahooPlayerStatsPageRussellWilson")
    private YahooNflHistoricStatsModel expectedRussellWilson;

    @Test
    public void shouldBeAbleToExtractPlayerRowsFromPlayerDocument() throws Exception {
        assertThat(playerRowValueExtractor.createValueExtractor(yahooStatsPageRunningBacks).call().count(), is(equalTo(165L)));
    }

    @Test
    public void shouldBeAbleToExtractNextPageUrlFromPlayerDocument() throws Exception {
        assertThat(nextPageValueExtractor.createValueExtractor(yahooStatsPageRunningBacks).call().findFirst().get(), is(equalTo("season_2014")));
    }

    @Test
    public void shouldBeAbleToExtractPlayerIdsFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(playerIdValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getPlayerId())));
    }

    @Test
    public void shouldBeAbleToExtractPlayerIdsFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(playerIdValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getPlayerId())));
    }

    @Test
    public void shouldBeAbleToExtractNamesFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(nameValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getName())));
    }

    @Test
    public void shouldBeAbleToExtractNamesFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(nameValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getName())));
    }

    @Test
    public void shouldBeAbleToExtractTeamNamesFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(teamValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getTeam())));
    }

    @Test
    public void shouldBeAbleToExtractTeamNamesFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(teamValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getTeam())));
    }

    @Test
    public void shouldBeAbleToExtractGamesPlayedFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(gamesPlayedValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getGamesPlayed())));
    }

    @Test
    public void shouldBeAbleToExtractGamesPlayedFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(gamesPlayedValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getGamesPlayed())));
    }

    @Test
    public void shouldBeAbleToExtractPassingCompletionsFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(passingCompletionsValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getPassingCompletions())));
    }

    @Test
    public void shouldBeAbleToExtractRushingAttemptsFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(rushingAttemptsRunningBacksValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingAttempts())));
    }

    @Test
    public void shouldBeAbleToExtractRushingAttemptsFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(rushingAttemptsQuarterbacksValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getRushingAttempts())));
    }

    @Test
    public void shouldBeAbleToExtractRushingYardsFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(yahooStatsRunningBacksRushingYardsValueExtractorFactory.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingYards())));
    }

    @Test
    public void shouldBeAbleToExtractRushingYardsFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(yahooStatsQuarterbacksRushingYardsValueExtractorFactory.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getRushingYards())));
    }

    @Test
    public void shouldBeAbleToExtractRushingYardsPerGameFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(rushingYardsPerGameRunningBackValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingYardsPerGame())));
    }

    @Test
    public void shouldBeAbleToExtractRushingYardsPerGameFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(rushingYardsPerGameQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getRushingYardsPerGame())));
    }

    @Test
    public void shouldBeAbleToExtractAverageYardsPerRushingAttemptFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(rushingYardsPerAttemptRunningBackValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingYardsPerAttempt())));
    }

    @Test
    public void shouldBeAbleToExtractAverageYardsPerRushingAttemptFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(rushingYardsPerAttemptQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getRushingYardsPerAttempt())));
    }

    @Test
    public void shouldBeAbleToExtractRushingTouchdownsFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(rushingTouchdownsRunningBacksValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractRushingTouchdownsFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(rushingTouchdownsQuarterbacksValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getRushingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractReceptionsFromRunningBacksPlayerDocuments() throws Exception {
        assertThat(receptionsRunningBacksValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceptions())));
    }

    @Test
    public void shouldBeAbleToExtractReceptionsFromQuarterbacksPlayerDocuments() throws Exception {
        assertThat(receptionsQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getReceptions())));
    }

    @Test
    public void shouldBeAbleToExtractTargetsFromRunningBacksPlayerDocument() throws Exception {
        assertThat(targetsRunningBackValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getTargets())));
    }

    @Test
    public void shouldBeAbleToExtractTargetsFromQuarterbacksPlayerDocument() throws Exception {
        assertThat(targetsQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getTargets())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsFromRunningBacksPlayerDocument() throws Exception {
        assertThat(receivingYardsRunningBackValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingYards())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsFromQuarterbacksPlayerDocument() throws Exception {
        assertThat(receivingYardsQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getReceivingYards())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsPerGameFromRunningBacksPlayerDocument() throws Exception {
        assertThat(receivingYardsPerGameRunningBackValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingYardsPerGame())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsPerGameFromQuarterbacksPlayerDocument() throws Exception {
        assertThat(receivingYardsPerGameQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getReceivingYardsPerGame())));
    }

    @Test
    public void shouldBeAbleToExtractYardsAfterCatchFromRunningBacksPlayerDocument() throws Exception {
        assertThat(yardsAfterCatchRunningBackValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingYardsAfterCatch())));
    }

    @Test
    public void shouldBeAbleToExtractYardsAfterCatchFromQuarterbacksPlayerDocument() throws Exception {
        assertThat(yardsAfterCatchQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getReceivingYardsAfterCatch())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsPerReceptionFromRunningBacksPlayerDocument() throws Exception {
        assertThat(receivingYardsPerReceptionRunningBackValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingYardsPerReception())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsPerReceptionFromQuarterbacksPlayerDocument() throws Exception {
        assertThat(receivingYardsPerReceptionQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getReceivingYardsPerReception())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingTouchdownsFromRunningBacksPlayerDocument() throws Exception {
        assertThat(receivingTouchdownsRunningBackValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingTouchdownsFromQuarterbacksPlayerDocument() throws Exception {
        assertThat(receivingTouchdownsQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getReceivingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractFumblesFromRunningBacksPlayerDocument() throws Exception {
        assertThat(fumblesRunningBacksValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getFumbles())));
    }

    @Test
    public void shouldBeAbleToExtractFumblesFromQuarterbacksPlayerDocument() throws Exception {
        assertThat(fumblesQuarterbacksValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getFumbles())));
    }

    @Test
    public void shouldBeAbleToExtractFumblesLostFromRunningBacksPlayerDocument() throws Exception {
        assertThat(fumblesLostRunningBacksValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getFumblesLost())));
    }

    @Test
    public void shouldBeAbleToExtractFumblesLostFromQuarterbacksPlayerDocument() throws Exception {
        assertThat(fumblesLostQuarterbacksValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getFumblesLost())));
    }

    @Test
    public void shouldBeAbleToExtractPositionFromQuarterbacksPlayerDocument() throws Exception {
        assertThat(positionQuarterbackValueExtractor.createValueExtractor(yahooStatsPageRussellWilson).call().findFirst().get(), is(equalTo(expectedRussellWilson.getPosition())));
    }

    @Test
    public void shouldBeAbleToExtractPositionFromRunningBacksPlayerDocument() throws Exception {
        assertThat(positionRunningBackValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getPosition())));
    }
}
