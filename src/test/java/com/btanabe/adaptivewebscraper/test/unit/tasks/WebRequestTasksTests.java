package com.btanabe.adaptivewebscraper.test.unit.tasks;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.tasks.WebRequestTask;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Brian on 5/15/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class WebRequestTasksTests {

    @Resource(name = "webRequestTaskToSiteUrlMap")
    private Map<String, WebRequestTask> urlToWebRequestTaskMap;

    @Autowired
    @Qualifier("webRequestTaskFactory")
    protected WebRequestTaskFactory mockWebRequestTaskFactory;

    @Test
    public void shouldBeAbleToMockAllPageDownloadCorrectly() {

        urlToWebRequestTaskMap.keySet().forEach(mockedUrl -> {
            try {
                assertThat(mockWebRequestTaskFactory.createWebRequestTask(mockedUrl).call(), is(notNullValue()));
            } catch (Exception e) {
                e.printStackTrace();
                fail(String.format("WebRequestTask failed to mock url=[%s] correctly!", mockedUrl));
            }
        });
    }

    @Test
    public void shouldBeAbleToDownloadTheSecondYahooRunningBacksPage() throws Exception {
        Document document = mockWebRequestTaskFactory.createWebRequestTask("https://sports.yahoo.com/nfl/stats/byposition?pos=RB&conference=NFL&year=season_2014&timeframe=All&sort=17&old_category=RB").call();
        assertThat(document, is(notNullValue()));
        assertThat(document.head().text(), is(equalTo("NFL - Statistics by Position - Yahoo Sports")));
    }
}
