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
    @Qualifier("basketballReferenceSeasonTotalsPlayerRowValueExtractorFactory")
    private DynamicValueExtractorFactory<Document> playerRowValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsNameValueExtractorFactory")
    private DynamicValueExtractorFactory<String> nameValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsPositionValueExtractorFactory")
    private DynamicValueExtractorFactory<String> positionValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsTeamValueExtractorFactory")
    private DynamicValueExtractorFactory<String> teamValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsGamesPlayedValueExtractorFactory")
    private DynamicValueExtractorFactory<String> gamesPlayedValueExtractorFactory;

    @Autowired
    @Qualifier("gamesStartedValueExtractorFactory")
    private DynamicValueExtractorFactory<String> gamesStartedValueExtractorFactory;

    @Autowired
    @Qualifier("minutesPlayedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> minutesPlayedValueExtractorFactory;

    @Autowired
    @Qualifier("fieldGoalsMadeValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> fieldGoalsMadeValueExtractorFactory;

    @Autowired
    @Qualifier("fieldGoalsAttemptedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> fieldGoalsAttemptedValueExtractorFactory;

    @Autowired
    @Qualifier("fieldGoalPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> fieldGoalPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("threePointersMadeValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> threePointersMadeValueExtractorFactory;

    @Autowired
    @Qualifier("threePointersAttemptedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> threePointersAttemptedValueExtractorFactory;

    @Autowired
    @Qualifier("threePointerPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> threePointerPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("twoPointerFieldGoalsMadeValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> twoPointerFieldGoalsMadeValueExtractorFactory;

    @Autowired
    @Qualifier("twoPointerFieldGoalsAttemptedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> twoPointerFieldGoalsAttemptedValueExtractorFactory;

    @Autowired
    @Qualifier("twoPointerFieldGoalPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> twoPointerFieldGoalPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("effectiveFieldGoalPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> effectiveFieldGoalPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("freeThrowsMadeValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> freeThrowsMadeValueExtractorFactory;

    @Autowired
    @Qualifier("freeThrowsAttemptedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> freeThrowsAttemptedValueExtractorFactory;

    @Autowired
    @Qualifier("freeThrowPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> freeThrowPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("offensiveReboundsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> offensiveReboundsValueExtractorFactory;

    @Autowired
    @Qualifier("defensiveReboundsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> defensiveReboundsValueExtractorFactory;

    @Autowired
    @Qualifier("totalReboundsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> totalReboundsValueExtractorFactory;

    @Autowired
    @Qualifier("assistsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> assistsValueExtractorFactory;

    @Autowired
    @Qualifier("stealsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> stealsValueExtractorFactory;

    @Autowired
    @Qualifier("blocksValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> blocksValueExtractorFactory;

    @Autowired
    @Qualifier("turnoversValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> turnoversValueExtractorFactory;

    @Autowired
    @Qualifier("personalFoulsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> personalFoulsValueExtractorFactory;

    @Autowired
    @Qualifier("pointsScoredValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> pointsScoredValueExtractorFactory;

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
    public void shouldBeAbleToExtractPlayerRowsFromPlayerDocuments() throws Exception {
        assertThat(playerRowValueExtractorFactory.createValueExtractor(basketballReferenceSeasonTotals).call().count(), is(equalTo(595L)));
    }

    @Test
    public void shouldBeAbleToExtractNamesFromPlayerDocuments() throws Exception {
        assertThat(nameValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findFirst().get(), is(equalTo(expectedNikolaJokicModel.getName())));
    }

    @Test
    public void shouldBeAbleToExtractPositionsFromPlayerDocuments() throws Exception {
        assertThat(positionValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findFirst().get(), is(equalTo(expectedNikolaJokicModel.getPosition())));
    }

    @Test
    public void shouldBeAbleToExtractTeamFromPlayerDocuments() throws Exception {
        assertThat(teamValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findFirst().get(), is(equalTo(expectedNikolaJokicModel.getTeam())));
    }

    @Test
    public void shouldBeAbleToExtractGamesPlayedFromPlayerDocuments() throws Exception {
        assertThat(gamesPlayedValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findFirst().get(), is(equalTo(expectedNikolaJokicModel.getGamesPlayed())));
    }

    @Test
    public void shouldBeAbleToExtractGamesStartedFromPlayerDocuments() throws Exception {
        assertThat(gamesStartedValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findFirst().get(), is(equalTo(expectedNikolaJokicModel.getGamesStarted())));
    }

    @Test
    public void shouldBeAbleToExtractMinutesPlayedFromPlayerDocuments() throws Exception {
        assertThat(minutesPlayedValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getMinutesPlayed())));
    }

    @Test
    public void shouldBeAbleToExtractFieldGoalsMadeFromPlayerDocuments() throws Exception {
        assertThat(fieldGoalsMadeValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getFieldGoalsMade())));
    }

    @Test
    public void shouldBeAbleToExtractFieldGoalsAttemptedFromPlayerDocuments() throws Exception {
        assertThat(fieldGoalsAttemptedValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getFieldGoalsAttempted())));
    }

    @Test
    public void shouldBeAbleToExtractFieldGoalPercentageFromPlayerDocuments() throws Exception {
        assertThat(fieldGoalPercentageValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getFieldGoalPercentage())));
    }

    @Test
    public void shouldBeAbleToExtractThreePointersMadeFromPlayerDocuments() throws Exception {
        assertThat(threePointersMadeValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getThreePointersMade())));
    }

    @Test
    public void shouldBeAbleToExtractThreePointersAttemptedFromPlayerDocuments() throws Exception {
        assertThat(threePointersAttemptedValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getThreePointersAttempted())));
    }

    @Test
    public void shouldBeAbleToExtractThreePointerPercentageFromPlayerDocuments() throws Exception {
        assertThat(threePointerPercentageValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getThreePointPercentage())));
    }

    @Test
    public void shouldBeAbleToExtractTwoPointFieldGoalsMadeFromPlayerDocuments() throws Exception {
        assertThat(twoPointerFieldGoalsMadeValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getTwoPointFieldGoalsMade())));
    }

    @Test
    public void shouldBeAbleToExtractTwoPointFieldGoalsAttemptedFromPlayerDocuments() throws Exception {
        assertThat(twoPointerFieldGoalsAttemptedValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getTwoPointFieldGoalsAttempted())));
    }

    @Test
    public void shouldBeAbleToExtractTwoPointFieldGoalPercentageFromPlayerDocuments() throws Exception {
        assertThat(twoPointerFieldGoalPercentageValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getTwoPointFieldGoalPercentage())));
    }

    @Test
    public void shouldBeAbleToExtractEffectiveFieldGoalPercentageFromPlayerDocuments() throws Exception {
        assertThat(effectiveFieldGoalPercentageValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getEffectiveFieldGoalPercentage())));
    }

    @Test
    public void shouldBeAbleToExtractFreeThrowsMadeFromPlayerDocuments() throws Exception {
        assertThat(freeThrowsMadeValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getFreeThrowsMade())));
    }

    @Test
    public void shouldBeAbleToExtractFreeThrowsAttemptedFromPlayerDocuments() throws Exception {
        assertThat(freeThrowsAttemptedValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getFreeThrowsAttempted())));
    }

    @Test
    public void shouldBeAbleToExtractFreeThrowPercentageFromPlayerDocuments() throws Exception {
        assertThat(freeThrowPercentageValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getFreeThrowPercentage())));
    }

    @Test
    public void shouldBeAbleToExtractOffensiveReboundsFromPlayerDocuments() throws Exception {
        assertThat(offensiveReboundsValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getOffensiveRebounds())));
    }

    @Test
    public void shouldBeAbleToExtractDefensiveReboundsFromPlayerDocuments() throws Exception {
        assertThat(defensiveReboundsValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getDefensiveRebounds())));
    }

    @Test
    public void shouldBeAbleToExtractTotalReboundsFromPlayerDocuments() throws Exception {
        assertThat(totalReboundsValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getTotalRebounds())));
    }

    @Test
    public void shouldBeAbleToExtractAssistsFromPlayerDocuments() throws Exception {
        assertThat(assistsValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getAssists())));
    }

    @Test
    public void shouldBeAbleToExtractStealsFromPlayerDocuments() throws Exception {
        assertThat(stealsValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getSteals())));
    }

    @Test
    public void shouldBeAbleToExtractBlocksFromPlayerDocuments() throws Exception {
        assertThat(blocksValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getBlocks())));
    }

    @Test
    public void shouldBeAbleToExtractTurnoversFromPlayerDocuments() throws Exception {
        assertThat(turnoversValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getTurnovers())));
    }

    @Test
    public void shouldBeAbleToExtractPersonalFoulsFromPlayerDocuments() throws Exception {
        assertThat(personalFoulsValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getPersonalFouls())));
    }

    @Test
    public void shouldBeAbleToExtractPointsScoredFromPlayerDocuments() throws Exception {
        assertThat(pointsScoredValueExtractorFactory.createValueExtractor(basketballReferenceStatsPageNikolaJokic).call().findAny().get(), is(equalTo(expectedNikolaJokicModel.getPoints())));
    }
}
