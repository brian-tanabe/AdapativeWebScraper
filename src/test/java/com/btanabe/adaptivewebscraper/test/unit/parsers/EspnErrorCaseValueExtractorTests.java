package com.btanabe.adaptivewebscraper.test.unit.parsers;

import com.btanabe.adaptivewebscraper.factories.DynamicValueExtractorFactory;
import com.btanabe.adaptivewebscraper.models.EspnNflProjectionModel;
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
 * Created by Brian on 5/15/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EspnErrorCaseValueExtractorTests {

    @Autowired
    @Qualifier("espnProjectionsPlayerIdValueExtractorFactory")
    private DynamicValueExtractorFactory<String> playerIdValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPageRankValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> rankValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsNameValueExtractorFactory")
    private DynamicValueExtractorFactory<String> nameValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsTeamValueExtractorFactory")
    private DynamicValueExtractorFactory<String> teamValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPositionValueExtractorFactory")
    private DynamicValueExtractorFactory<String> positionValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPassingCompletionsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> passingCompletionsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPassingAttemptsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> passingAttemptsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPassingYardsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> passingYardsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPassingTouchdownsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> passingTouchdownsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsInterceptionsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> interceptionsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsRushingAttemptsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> rushingAttemptsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsRushingYardsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> rushingYardsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsRushingTouchdownsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> rushingTouchdownsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsReceptionsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> receptionsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsReceivingYardsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> receivingYardsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsReceivingTouchdownsValueExtractorFactory")
    private DynamicValueExtractorFactory<Integer> receivingTouchdownsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsFantasyPointsValueExtractorFactory")
    private DynamicValueExtractorFactory<Double> fantasyPointsValueExtractorFactory;

    @Autowired
    @Qualifier("espnProjectionsPageLacheSeastrunk")
    private Document playerProjectionDocumentWithNoProjections;

    @Autowired
    @Qualifier("espnProjectionsPageSteelersDefense")
    private Document playerProjectionsDocumentWithNoLastNameOrTeam;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageLacheSeastrunk")
    private EspnNflProjectionModel expectedJoshFergusonModel;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageSteelersDefense")
    private EspnNflProjectionModel expectedSteelersTeamDefenseModel;

    @Test
    public void shouldBeAbleToSetNameCorrectlyWhenTheInputPlayerIsATeamDefense() throws Exception {
        assertThat(nameValueExtractorFactory.createValueExtractor(playerProjectionsDocumentWithNoLastNameOrTeam).call().findFirst().get(), is(equalTo(expectedSteelersTeamDefenseModel.getName())));
    }

    @Test
    public void shouldBeAbleToSetTeamCorrectlyWhenTheInputPlayerIsATeamDefense() throws Exception {
        assertThat(teamValueExtractorFactory.createValueExtractor(playerProjectionsDocumentWithNoLastNameOrTeam).call().findFirst().get(), is(equalTo(expectedSteelersTeamDefenseModel.getTeam())));
    }

    @Test
    public void shouldBeAbleToSetPositionCorrectlyWhenTheInputPlayerIsATeamDefense() throws Exception {
        assertThat(positionValueExtractorFactory.createValueExtractor(playerProjectionsDocumentWithNoLastNameOrTeam).call().findFirst().get(), is(equalTo(expectedSteelersTeamDefenseModel.getPosition())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersPassingCompletionsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(passingCompletionsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getPassingCompletions())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersPassingAttemptsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(passingAttemptsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getPassingAttempts())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersPassingYardsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(passingYardsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getPassingYards())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersPassingTouchdownsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(passingTouchdownsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getPassingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersPassingInterceptionsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(interceptionsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getInterceptions())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersRushingAttemptsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(rushingAttemptsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getRushingAttempts())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersRushingYardsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(rushingYardsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getRushingYards())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersRushingTouchdownsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(rushingTouchdownsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getRushingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersReceptionsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(receptionsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getReceptions())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersReceivingYardsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(receivingYardsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getReceivingYards())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersReceivingTouchdownsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(receivingTouchdownsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getReceivingTouchdowns())));
    }

    @Test
    public void shouldBeAbleToExtractAPlayersTotalPointsAsZeroWhenTheyHaveNoProjectionListed() throws Exception {
        assertThat(fantasyPointsValueExtractorFactory.createValueExtractor(playerProjectionDocumentWithNoProjections).call().findFirst().get(), is(equalTo(expectedJoshFergusonModel.getFantasyPoints())));
    }
}
