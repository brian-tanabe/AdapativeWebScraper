package com.btanabe.adaptivewebscraper.test.integration.collectors;

import com.btanabe.adaptivewebscraper.collectors.RecordCollector;
import com.btanabe.adaptivewebscraper.models.EspnNflProjectionModel;
import com.btanabe.adaptivewebscraper.test.integration.MockWebRequestTaskBase;
import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

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
    @Qualifier("espnPlayerProjectionsPageLacheSeastrunk")
    private EspnNflProjectionModel expectedLacheSeastrunkModel;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageSteelersDefense")
    private EspnNflProjectionModel expectedSteelersTeamDefenseModel;

    @Autowired
    @Qualifier("collectedRecordsEventBus")
    private EventBus eventBus;

    private List<EspnNflProjectionModel> collectedRecords;

    @Before
    public void performRecordCollection() throws Exception {
        if (collectedRecords == null) {
            CollectedRecordsListener recordsListener = new CollectedRecordsListener();
            eventBus.register(recordsListener);

            espnNflProjectionsPageRecordCollector.call();
            collectedRecords = (List<EspnNflProjectionModel>) (List<?>) recordsListener.getCollectedModels();
        }
    }

    @Test
    public void shouldBeAbleToFindNineHundredTwoPlayersAcrossFortyFivePages() throws Exception {
        assertThat(collectedRecords.size(), is(equalTo(902)));
    }

    @Test
    public void shouldBeAbleToCorrectlyParseASinglePlayerCorrectly() throws Exception {
        EspnNflProjectionModel playerFromRecordCollector = collectedRecords.stream().filter(player -> player.getName().equals(expectedEddieLacyModel.getName())).findFirst().get();
        assertThat(playerFromRecordCollector, is(equalTo(expectedEddieLacyModel)));
    }

    @Test
    public void shouldBeAbleToParsePlayersWithoutProjectionsCorrectly() throws Exception {
        EspnNflProjectionModel playerFromRecordCollector = collectedRecords.stream().filter(player -> player.getName().equals(expectedLacheSeastrunkModel.getName())).findFirst().get();
        assertThat(playerFromRecordCollector, is(equalTo(expectedLacheSeastrunkModel)));
    }

    @Test
    public void shouldBeAbleToParseTeamDefensesCorrectly() throws Exception {
        EspnNflProjectionModel playerFromRecordCollector = collectedRecords.stream().filter(player -> player.getName().equals(expectedSteelersTeamDefenseModel.getName())).findFirst().get();
        assertThat(playerFromRecordCollector, is(equalTo(expectedSteelersTeamDefenseModel)));
    }
}
