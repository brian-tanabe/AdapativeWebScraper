package com.btanabe.adaptivewebscraper.test.integration;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

/**
 * Created by Brian on 5/15/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(PowerMockRunner.class)
@PrepareForTest(WebRequestTaskFactory.class)
public class MockWebRequestTaskBase {

    @Autowired
    @Qualifier("mockStaticWebRequestFactory")
    protected Object mockWebRequestCreation;

    private TestContextManager testContextManager;

    @Before
    public void setupSpringAutowiringContexts() throws Exception {
        // Provides the same functionality as @RunWith(SpringJUnit4ClassRunner.class):
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);
    }

    @Test
    @Ignore
    public void atLeastOneTestIsNeededInThisClassToPreventInitializationErrors() {
    }
}
