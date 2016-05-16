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

    private TestContextManager testContextManager;

    @Autowired
    @Qualifier("mockStaticWebRequestFactory")
    protected Object mockWebRequestCreation;

    @Before
    public void setupSpringAutowiringContexts() throws Exception {
        // Provides the same functionality as @RunWith(SpringJUnit4ClassRunner.class):
        this.testContextManager = new TestContextManager(getClass());
        this.testContextManager.prepareTestInstance(this);

        System.out.println(String.format("Mocked web pages=%s", mockWebRequestCreation));
    }

    @Test
    @Ignore
    public void atLeastOneTestIsNeededInThisClassToPreventInitializationErrors() {
    }

}
