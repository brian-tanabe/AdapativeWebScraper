package com.btanabe.adaptivewebscraper.test.integration.collectors;

import com.btanabe.adaptivewebscraper.collectors.RecordCollector;
import com.btanabe.adaptivewebscraper.models.EspnNflProjectionModel;
import com.btanabe.adaptivewebscraper.models.Model;
import com.btanabe.adaptivewebscraper.test.integration.MockWebRequestTaskBase;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Brian on 5/1/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
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
    private CollectedRecordsListener recordsListener = new CollectedRecordsListener();

    @Before
    public void performRecordCollection() throws Exception {
        EventBus eventBus = new EventBus();
        eventBus.register(recordsListener);
        espnNflProjectionsPageRecordCollector.setEventBus(eventBus);

        if (collectedRecords == null) {
            espnNflProjectionsPageRecordCollector.gatherAllRecords();
            collectedRecords = (List<EspnNflProjectionModel>) (List<?>) recordsListener.getCollectedModels();
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

    @Getter
    private class CollectedRecordsListener {
        private List<Model> collectedModels = new ArrayList<>();

        @Subscribe
        public void collectedRecord(final Model collectedRecord) {
            collectedModels.add(collectedRecord);
        }

    }
}
