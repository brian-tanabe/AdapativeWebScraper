package com.btanabe.adaptivewebscraper.test.unit.tasks;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.test.integration.MockWebRequestTaskBase;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Brian on 5/15/16.
 */
public class WebRequestTasksTests extends MockWebRequestTaskBase {

    @Test
    public void shouldBeAbleToMockAllPageDownloadCorrectly() {

        ((Set<String>) mockWebRequestCreation).forEach(mockedUrl -> {
            try {
                assertThat(WebRequestTaskFactory.createWebRequestTask(mockedUrl).call(), is(notNullValue()));
            } catch (Exception e) {
                e.printStackTrace();
                fail(String.format("WebRequestTask failed to mock url=[%s] correctly!", mockedUrl));
            }
        });
    }
}
