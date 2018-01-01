package com.btanabe.adaptivewebscraper.test.factories;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.tasks.WebRequestTask;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Brian on 5/15/16.
 */
public class MockWebRequestTaskFactoryFactory implements FactoryBean<WebRequestTaskFactory> {

    @Setter(onMethod = @__({@Autowired}))
    private Map<String, WebRequestTask> webRequestTaskToSiteUrlMap;

    @Override
    public WebRequestTaskFactory getObject() throws Exception {
        WebRequestTaskFactory webRequestTaskFactory = mock(WebRequestTaskFactory.class);
        webRequestTaskToSiteUrlMap.forEach((url, webRequestTask) -> when(webRequestTaskFactory.createWebRequestTask(eq(url))).thenReturn(webRequestTask));

        return webRequestTaskFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return WebRequestTaskFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}