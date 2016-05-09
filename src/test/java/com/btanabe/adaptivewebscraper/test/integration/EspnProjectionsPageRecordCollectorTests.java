package com.btanabe.adaptivewebscraper.test.integration;

import com.btanabe.adaptivewebscraper.collectors.RecordCollector;
import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.models.EspnNflProjectionModel;
import com.btanabe.adaptivewebscraper.tasks.WebRequestTask;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Brian on 5/1/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(PowerMockRunner.class)
@PrepareForTest(WebRequestTaskFactory.class)
public class EspnProjectionsPageRecordCollectorTests {

    private TestContextManager testContextManager;

    @Autowired
    @Qualifier("espnNflPlayerProjectionsRecordCollector")
    private RecordCollector<EspnNflProjectionModel> espnNflProjectionsPageRecordCollector;

    @Autowired
    @Qualifier("espnNflProjectionsPageOneFormatted")
    private Document firstPageDocument;

    @Before
    public void setupMockWebRequestTaskFactory() throws Exception {

        // Provides the same functionality as @RunWith(SpringJUnit4ClassRunner.class):
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);

        // Page one:
        WebRequestTask firstPageWebRequestTask = mock(WebRequestTask.class);
        when(firstPageWebRequestTask.call()).thenReturn(firstPageDocument);

        PowerMockito.mockStatic(WebRequestTaskFactory.class);
        BDDMockito.given(WebRequestTaskFactory.createWebRequestTask(anyString())).willReturn(firstPageWebRequestTask);

//        when(mockWebRequestTaskFactory.createWebRequestTask(eq("http://games.espn.go.com/ffl/tools/projections?leagueId=84978"))).thenReturn(firstPageWebRequestTask);
    }

    @Test
    public void shouldBeAbleToFindFortyPlayersOnPageOne() throws Exception {
        final List<EspnNflProjectionModel> records = espnNflProjectionsPageRecordCollector.getAllRecordsAsList();
        assertThat(records.size(), is(equalTo(40)));
        records.forEach(record -> System.out.println(record));
    }
}
