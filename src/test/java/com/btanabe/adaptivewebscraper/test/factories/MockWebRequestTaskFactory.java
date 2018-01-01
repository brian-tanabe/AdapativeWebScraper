package com.btanabe.adaptivewebscraper.test.factories;

import com.btanabe.adaptivewebscraper.tasks.WebRequestTask;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Brian on 5/15/16.
 */
public class MockWebRequestTaskFactory implements FactoryBean<WebRequestTask> {

    @Setter(onMethod = @__({@Autowired}))
    private Document pageDocument;

    @Override
    public WebRequestTask getObject() throws Exception {
        WebRequestTask mockWebRequestTask = mock(WebRequestTask.class);
        when(mockWebRequestTask.call()).thenReturn(pageDocument);
        return mockWebRequestTask;
    }

    @Override
    public Class<?> getObjectType() {
        return WebRequestTask.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
