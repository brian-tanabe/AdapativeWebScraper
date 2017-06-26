package com.btanabe.adaptivewebscraper.test.unit.tasks;

import com.btanabe.adaptivewebscraper.tasks.SecureWebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.utilities.EspnCredentialProvider;
import org.jsoup.nodes.Document;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Callable;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 10/29/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SecureWebRequestTaskTests {

    private static SecureWebRequestTaskFactory webRequestTask;

    @BeforeClass
    public static void setupWebRequestTask() throws Exception {
        webRequestTask = new SecureWebRequestTaskFactory(new EspnCredentialProvider());
    }

    @Test
    public void shouldBeAbleToDownloadPagesFromEspn() throws Exception {
        Callable<Document> webRequestCallable = webRequestTask.createWebRequestTask("http://games.espn.com/fba/freeagency?leagueId=22603&teamId=4&seasonId=2017#&seasonId=2017&avail=-1");
        Document document = webRequestCallable.call();
        assertThat(document.html(), containsString("Russell Westbrook"));
    }
}
