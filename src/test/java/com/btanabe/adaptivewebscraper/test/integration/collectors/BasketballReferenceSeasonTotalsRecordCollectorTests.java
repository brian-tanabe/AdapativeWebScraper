package com.btanabe.adaptivewebscraper.test.integration.collectors;

import com.btanabe.adaptivewebscraper.collectors.RecordCollector;
import com.btanabe.adaptivewebscraper.models.BasketballReferenceSeasonTotalsModel;
import com.btanabe.adaptivewebscraper.test.integration.MockWebRequestTaskBase;
import com.btanabe.adaptivewebscraper.test.utilities.CollectedRecordsListener;
import com.google.common.eventbus.EventBus;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BasketballReferenceSeasonTotalsRecordCollectorTests extends MockWebRequestTaskBase {

    @Autowired
    @Qualifier("basketballReferenceSeasonTotalsRecordCollector")
    private RecordCollector<BasketballReferenceSeasonTotalsModel> basketballReferenceSeasonTotalsRecordCollector;

    @Autowired
    @Qualifier("basketballReference2017SeasonTotalsNikolaJokic")
    private Document basketballReferenceStatsPageNikolaJokic;

    @Autowired
    @Qualifier("collectedRecordsEventBus")
    private EventBus eventBus;

    private List<BasketballReferenceSeasonTotalsModel> collectedRecords;

    @Before
    public void performRecordCollection() throws Exception {

        // This must be done to use the mocked WebRequestTaskFactory:
        basketballReferenceSeasonTotalsRecordCollector.setWebRequestTaskFactory(mockWebRequestTaskFactory);

        if (collectedRecords == null) {
            CollectedRecordsListener recordsListener = new CollectedRecordsListener();
            eventBus.register(recordsListener);

            basketballReferenceSeasonTotalsRecordCollector.call();
            collectedRecords = (List<BasketballReferenceSeasonTotalsModel>) (List<?>) recordsListener.getCollectedModels();
        }
    }

    @Test
    public void shouldBeAbleToFindFourHundredPlayers() throws Exception {
        assertThat(collectedRecords.size(), is(equalTo(400)));
    }
}
