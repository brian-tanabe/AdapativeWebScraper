package com.btanabe.adaptivewebscraper.test.unit.factories;

import com.btanabe.adaptivewebscraper.factories.DocumentFactory;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 5/4/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentFactoryTests {

    @Autowired
    @Qualifier("espnProjectionsPageEddieLacyString")
    private String singlePlayerHtmlText;

    @Autowired
    @Qualifier("espnNflProjectionsPageOneAsAStringFormatted")
    private String entirePageHtmlText;

    @Test
    public void shouldBeAbleToConstructDocumentsFromPartialHtmlPages() throws Exception {
        final Document document = DocumentFactory.builder().documentText(singlePlayerHtmlText).build().getObject();

        assertThat(document.text(), containsString("Eddie Lacy"));
    }

    @Test
    public void shouldBeAbleToConstructDocumentsFromEntireHtmlPages() throws Exception {
        final Document document = DocumentFactory.builder().documentText(entirePageHtmlText).build().getObject();

        assertThat(document.text(), containsString("Antonio Brown"));
        assertThat(document.text(), containsString("Matt Forte"));
    }

}
