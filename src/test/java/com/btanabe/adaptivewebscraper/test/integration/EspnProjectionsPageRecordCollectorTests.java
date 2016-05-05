package com.btanabe.adaptivewebscraper.test.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.fail;

/**
 * Created by Brian on 5/1/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EspnProjectionsPageRecordCollectorTests {

    @Test
    public void shouldBeAbleToFindFortyPlayersOnPageOne() throws Exception {
        fail("NOT YET IMPLEMENTED");
    }
}
