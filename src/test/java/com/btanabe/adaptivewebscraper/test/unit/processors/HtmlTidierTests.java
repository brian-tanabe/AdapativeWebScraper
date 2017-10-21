package com.btanabe.adaptivewebscraper.test.unit.processors;

import com.btanabe.adaptivewebscraper.processors.HtmlTidier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 4/15/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HtmlTidierTests {

    @Qualifier("espnNflProjectionsPageOneAsAStringUnformatted")
    @Autowired
    private String poorlyFormattedHtmlString;

    @Qualifier("espnNflProjectionsPageOneAsAStringFormatted")
    @Autowired
    private String wellFormattedHtmlString;

    @Test
    public void formattingHtmlFilesShouldModifyTheFile() {
        assertThat(poorlyFormattedHtmlString, is(not(equalTo(wellFormattedHtmlString))));
    }

    @Test
    public void shouldBeAbleToCleanUpIncorrectIndentations() {
        assertThat(HtmlTidier.tidyHtmlAndConvertToXhtml(poorlyFormattedHtmlString), is(equalTo(wellFormattedHtmlString)));
    }

    @Test
    public void shouldBeAbleToReplaceAllNbspReferences() {
        assertThat(HtmlTidier.tidyHtmlAndConvertToXhtml(poorlyFormattedHtmlString), not(containsString("&nbsp")));
    }
}
