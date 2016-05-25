package com.btanabe.adaptivewebscraper.test.integration.collectors;

import com.btanabe.adaptivewebscraper.collectors.RecordCollector;
import com.btanabe.adaptivewebscraper.models.EspnNflProjectionModel;
import com.btanabe.adaptivewebscraper.test.integration.MockWebRequestTaskBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Brian on 5/1/16.
 */
public class EspnProjectionsPageRecordCollectorTests extends MockWebRequestTaskBase {

    @Autowired
    @Qualifier("espnNflPlayerProjectionsRecordCollector")
    private RecordCollector<EspnNflProjectionModel> espnNflProjectionsPageRecordCollector;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageEddieLacy")
    private EspnNflProjectionModel expectedEddieLacyModel;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageJoshFerguson")
    private EspnNflProjectionModel expectedJoshFergusonModel;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageSteelersDefense")
    private EspnNflProjectionModel expectedSteelersTeamDefenseModel;

    private List<EspnNflProjectionModel> collectedRecords;

    @Before
    public void performRecordCollection() throws Exception {
        if (collectedRecords == null) {
            collectedRecords = espnNflProjectionsPageRecordCollector.getAllRecordsAsList();
        }
    }

    @Test
    public void shouldBeAbleToFindOneThousandSevenHundredSeventySixPlayersAcrossFortyFivePages() throws Exception {
        assertThat(collectedRecords.size(), is(equalTo(1776)));
    }

    @Test
    public void shouldBeAbleToCorrectlyParseASinglePlayerCorrectly() throws Exception {
        EspnNflProjectionModel playerFromRecordCollector = collectedRecords.stream().filter(player -> player.getName().equals(expectedEddieLacyModel.getName())).findFirst().get();
        assertThat(playerFromRecordCollector, is(equalTo(expectedEddieLacyModel)));
    }

    @Test
    public void shouldBeAbleToParsePlayersWithoutProjectionsCorrectly() throws Exception {
        EspnNflProjectionModel playerFromRecordCollector = collectedRecords.stream().filter(player -> player.getName().equals(expectedJoshFergusonModel.getName())).findFirst().get();
        assertThat(playerFromRecordCollector, is(equalTo(expectedJoshFergusonModel)));
    }

    @Test
    public void shouldBeAbleToParseTeamDefensesCorrectly() throws Exception {
        EspnNflProjectionModel playerFromRecordCollector = collectedRecords.stream().filter(player -> player.getName().equals(expectedSteelersTeamDefenseModel.getName())).findFirst().get();
        assertThat(playerFromRecordCollector, is(equalTo(expectedSteelersTeamDefenseModel)));
    }
}
