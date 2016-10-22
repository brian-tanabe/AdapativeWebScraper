package com.btanabe.adaptivewebscraper.test.integration.collectors;

import com.btanabe.adaptivewebscraper.collectors.RecordCollector;
import com.btanabe.adaptivewebscraper.models.YahooNflHistoricStatsModel;
import com.btanabe.adaptivewebscraper.test.integration.MockWebRequestTaskBase;
import com.btanabe.adaptivewebscraper.test.utilities.CollectedRecordsListener;
import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 7/9/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
public class YahooStatsPageQuarterbacksRecordCollectorTests extends MockWebRequestTaskBase {

    @Autowired
    @Qualifier("yahooNflQuarterbackStatsRecordCollector")
    private RecordCollector<YahooNflHistoricStatsModel> yahooQuarterbacksStatsPageRecordCollector;

    @Autowired
    @Qualifier("yahooPlayerStatsPageRussellWilson")
    private YahooNflHistoricStatsModel expectedRussellWilson;

    @Autowired
    @Qualifier("collectedRecordsEventBus")
    private EventBus eventBus;

    private List<YahooNflHistoricStatsModel> collectedRecords;

    @Before
    public void performRecordCollection() throws Exception {
        CollectedRecordsListener recordsListener = new CollectedRecordsListener();
        eventBus.register(recordsListener);

        if (collectedRecords == null) {
            yahooQuarterbacksStatsPageRecordCollector.gatherAllRecords();
            collectedRecords = (List<YahooNflHistoricStatsModel>) (List<?>) recordsListener.getCollectedModels();
        }
    }

    /**
     * TODO: Check if it should actually be 1185.  That number changed when I updated the input files.
     */
    @Test
    public void shouldBeAbleToFindOneThousandOneHundredEightyFiveRecords() {
//        assertThat(collectedRecords.size(), is(equalTo(1185)));
        assertThat(collectedRecords.size(), is(equalTo(525)));
    }

    @Test
    public void shouldBeAbleToParseASingleQuarterbackCorrectly() {
        YahooNflHistoricStatsModel playerFromRecordCollector = collectedRecords.stream().filter(player -> player.getName().equals(expectedRussellWilson.getName())).findFirst().get();
        assertThat(playerFromRecordCollector, is(equalTo(expectedRussellWilson)));
    }
}
