package com.btanabe.adaptivewebscraper.test.integration;

import com.btanabe.adaptivewebscraper.factories.WebRequestTaskFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by Brian on 5/15/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
public abstract class MockWebRequestTaskBase {

    @Autowired
    @Qualifier("webRequestTaskFactory")
    protected WebRequestTaskFactory mockWebRequestTaskFactory;

}
