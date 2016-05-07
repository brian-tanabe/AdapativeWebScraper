package com.btanabe.adaptivewebscraper.test.integration;

import com.btanabe.adaptivewebscraper.collectors.RecordCollector;
import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.models.EspnNflProjectionModel;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Executors;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

/**
 * Created by Brian on 5/1/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EspnProjectionsPageRecordCollectorTests {

    private RecordCollector<EspnNflProjectionModel> espnNflProjectionsPageRecordCollector;
    private WebRequestTaskFactory mockWebRequestTaskFactory;
    private ListeningExecutorService executorService;
    private final String seedWebPage = "http://games.espn.go.com/ffl/tools/projections?leagueId=84978";

    @Before
    public void setUpTestRecordCollector() {
        executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(50));
    }

    @Before
    public void setupMockWebRequestTaskFactory() {
        mockWebRequestTaskFactory = mock(WebRequestTaskFactory.class);
    }

    @Test
    public void shouldBeAbleToFindFortyPlayersOnPageOne() throws Exception {
//        espnNflProjectionsPageRecordCollector = new RecordCollector<EspnNflProjectionModel>(executorService, seedWebPage, mockWebRequestTaskFactory, );

        fail("NOT YET IMPLEMENTED");
    }
}
