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
public class YahooStatsPageRecordCollectorTests extends MockWebRequestTaskBase {

    @Autowired
    @Qualifier("yahooNflRunningBackStatsRecordCollector")
    private RecordCollector<YahooNflHistoricStatsModel> yahooNflStatsPageRecordCollector;

    @Autowired
    @Qualifier("collectedRecordsEventBus")
    private EventBus eventBus;

    private List<YahooNflHistoricStatsModel> collectedRecords;

    @Before
    public void performRecordCollection() throws Exception {
        CollectedRecordsListener recordsListener = new CollectedRecordsListener();
        eventBus.register(recordsListener);

        if (collectedRecords == null) {
            yahooNflStatsPageRecordCollector.gatherAllRecords();
            collectedRecords = (List<YahooNflHistoricStatsModel>) (List<?>) recordsListener.getCollectedModels();
        }
    }

    @Test
    public void shouldBeAbleToFindTwoThousandSixHundredFortyRecords() {
        assertThat(collectedRecords.size(), is(equalTo(2640)));
    }
}