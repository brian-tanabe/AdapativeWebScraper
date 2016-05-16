package com.btanabe.adaptivewebscraper.test.factories;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import com.btanabe.adaptivewebscraper.tasks.WebRequestTask;
import lombok.Setter;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Set;

import static org.mockito.Matchers.eq;

/**
 * Created by Brian on 5/15/16.
 */
public class MockWebRequestTaskFactoryFactory implements FactoryBean<Set<String>> {

    @Setter(onMethod = @__({@Autowired}))
    private Map<String, WebRequestTask> webRequestTaskToSiteUrlMap;

    @Override
    public Set<String> getObject() throws Exception {
        PowerMockito.mockStatic(WebRequestTaskFactory.class);
        webRequestTaskToSiteUrlMap.forEach((url, webRequestTask) -> {
            BDDMockito.given(WebRequestTaskFactory.createWebRequestTask(eq(url))).willReturn(webRequestTask);
        });

        return webRequestTaskToSiteUrlMap.keySet();
    }

    @Override
    public Class<?> getObjectType() {
        return Set.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}