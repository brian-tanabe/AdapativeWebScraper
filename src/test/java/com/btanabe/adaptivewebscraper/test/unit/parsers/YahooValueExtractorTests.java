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
    @Qualifier("yahooStatsTeamValueExtractorFactory")
    private ValueExtractorFactory<String> teamValueExtractor;

    @Autowired
    @Qualifier("yahooStatsGamesPlayedValueExtractorFactory")
    private ValueExtractorFactory<Integer> gamesPlayedValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRushingAttemptsValueExtractorFactory")
    private ValueExtractorFactory<Integer> rushingAttemptsValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRushingYardsValueExtractorFactory")
    private ValueExtractorFactory<Integer> rushingYardsValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRushingYardsPerGameValueExtractorFactory")
    private ValueExtractorFactory<Double> rushingYardsPerGameValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRushingYardsPerAttemptValueExtractorFactory")
    private ValueExtractorFactory<Double> rushingYardsPerAttemptValueExtractor;

    @Autowired
    @Qualifier("yahooStatsRushingTouchdownsValueExtractorFactory")
    private ValueExtractorFactory<Integer> rushingTouchdownsValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceptionsValueExtractorFactory")
    private ValueExtractorFactory<Integer> receptionsValueExtractor;

    @Autowired
    @Qualifier("yahooStatsTargetsValueExtractorFactory")
    private ValueExtractorFactory<Integer> targetsValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingYardsValueExtractorFactory")
    private ValueExtractorFactory<Integer> receivingYardsValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingYardsAfterCatchValueExtractorFactory")
    private ValueExtractorFactory<Double> yardsAfterCatchValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingYardsPerGameValueExtractorFactory")
    private ValueExtractorFactory<Double> receivingYardsPerGameValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingYardsPerReceptionValueExtractorFactory")
    private ValueExtractorFactory<Double> receivingYardsPerReceptionValueExtractor;

    @Autowired
    @Qualifier("yahooStatsReceivingTouchdownsValueExtractorFactory")
    private ValueExtractorFactory<Integer> receivingTouchdownsValueExtractor;

    @Autowired
    @Qualifier("yahooStatsFumblesValueExtractorFactory")
    private ValueExtractorFactory<Integer> fumblesValueExtractor;

    @Autowired
    @Qualifier("yahooStatsFumblesLostValueExtractorFactory")
    private ValueExtractorFactory<Integer> fumblesLostValueExtractor;

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

    @Test
    public void shouldBeAbleToExtractTeamNamesFromPlayerDocuments() throws Exception {
        assertThat(teamValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getTeam())));
    }

    @Test
    public void shouldBeAbleToExtractGamesPlayedFromPlayerDocuments() throws Exception {
        assertThat(gamesPlayedValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getGamesPlayed())));
    }

    @Test
    public void shouldBeAbleToExtractRushingAttemptsFromPlayerDocuments() throws Exception {
        assertThat(rushingAttemptsValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingAttempts())));
    }

    @Test
    public void shouldBeAbleToExtractRushingYardsFromPlayerDocuments() throws Exception {
        assertThat(rushingYardsValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingYards())));
    }

    @Test
    public void shouldBeAbleToExtractRushingYardsPerGameFromPlayerDocuments() throws Exception {
        assertThat(rushingYardsPerGameValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingYardsPerGame())));
    }

    @Test
    public void shouldBeAbleToExtractAverageYardsPerRushingAttemptFromPlayerDocuments() throws Exception {
        assertThat(rushingYardsPerAttemptValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingYardsPerAttempt())));
    }

    @Test
    public void shouldBeAbleToExtractRushingTouchdownsFromPlayerDocuments() throws Exception {
        assertThat(rushingTouchdownsValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getRushingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractReceptionsFromPlayerDocuments() throws Exception {
        assertThat(receptionsValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceptions())));
    }

    @Test
    public void shouldBeAbleToExtractTargetsFromPlayerDocument() throws Exception {
        assertThat(targetsValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getTargets())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsFromPlayerDocument() throws Exception {
        assertThat(receivingYardsValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingYards())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsPerGameFromPlayerDocument() throws Exception {
        assertThat(receivingYardsPerGameValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingYardsPerGame())));
    }

    @Test
    public void shouldBeAbleToExtractYardsAfterCatchFromPlayerDocument() throws Exception {
        assertThat(yardsAfterCatchValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingYardsAfterCatch())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingYardsPerReceptionFromPlayerDocument() throws Exception {
        assertThat(receivingYardsPerReceptionValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingYardsPerReception())));
    }

    @Test
    public void shouldBeAbleToExtractReceivingTouchdownsFromPlayerDocument() throws Exception {
        assertThat(receivingTouchdownsValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getReceivingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractFumblesFromPlayerDocument() throws Exception {
        assertThat(fumblesValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getFumbles())));
    }

    @Test
    public void shouldBeAbleToExtractFumblesLostFromPlayerDocument() throws Exception {
        assertThat(fumblesLostValueExtractor.createValueExtractor(yahooStatsPageAdrianPeterson).call().findFirst().get(), is(equalTo(expectedAdrianPetersonModel.getFumblesLost())));
    }
}
