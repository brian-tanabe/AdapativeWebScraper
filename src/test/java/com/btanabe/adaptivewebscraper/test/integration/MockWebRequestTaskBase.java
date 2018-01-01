package com.btanabe.adaptivewebscraper.test.integration;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Brian on 5/15/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class MockWebRequestTaskBase {

    @Autowired
    @Qualifier("webRequestTaskFactory")
    protected WebRequestTaskFactory mockWebRequestTaskFactory;

}
