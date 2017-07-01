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
    @Qualifier("basketballReferenceSeasonTotalsNextPageValueExtractorFactory")
    private DynamicValueExtractorFactory<String> nextPageUrlValueExtractorFactory;

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
    @Qualifier("basketballReferenceSeasonTotalsGamesStartedValueExtractorFactory")
    private DynamicValueExtractorFactory<String> gamesStartedValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsMinutesPlayedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> minutesPlayedValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsFieldGoalsMadeValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> fieldGoalsMadeValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsFieldGoalsAttemptedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> fieldGoalsAttemptedValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsFieldGoalPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> fieldGoalPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsThreePointersMadeValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> threePointersMadeValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsThreePointersAttemptedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> threePointersAttemptedValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsThreePointerPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> threePointerPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsTwoPointerFieldGoalsMadeValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> twoPointerFieldGoalsMadeValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsTwoPointerFieldGoalsAttemptedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> twoPointerFieldGoalsAttemptedValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsTwoPointerFieldGoalPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> twoPointerFieldGoalPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsEffectiveFieldGoalPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> effectiveFieldGoalPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsFreeThrowsMadeValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> freeThrowsMadeValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsFreeThrowsAttemptedValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> freeThrowsAttemptedValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsFreeThrowPercentageValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> freeThrowPercentageValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsOffensiveReboundsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> offensiveReboundsValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsDefensiveReboundsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> defensiveReboundsValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsTotalReboundsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> totalReboundsValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsAssistsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> assistsValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsStealsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> stealsValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsBlocksValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> blocksValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsTurnoversValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> turnoversValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsPersonalFoulsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> personalFoulsValueExtractorFactory;

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsPointsScoredValueExtractorFactory")
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
    public void shouldBeAbleToExtractNextPageUrlFromPlayerDocuments() throws Exception {
        assertThat(nextPageUrlValueExtractorFactory.createValueExtractor(basketballReferenceSeasonTotals).call().findFirst().get(), is(equalTo("/leagues/NBA_2016_totals.html")));
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
