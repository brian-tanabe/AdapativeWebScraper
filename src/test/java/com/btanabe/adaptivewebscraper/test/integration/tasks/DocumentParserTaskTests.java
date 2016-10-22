package com.btanabe.adaptivewebscraper.test.integration.tasks;

import com.btanabe.adaptivewebscraper.factories.outputobject.MultimapObjectSetter;
import com.btanabe.adaptivewebscraper.factories.outputobject.OutputObjectConstructorI;
import com.btanabe.adaptivewebscraper.factories.outputobject.OutputObjectSetterI;
import com.btanabe.adaptivewebscraper.factories.valueextractor.ValueExtractorFactoryI;
import com.btanabe.adaptivewebscraper.models.YahooNflHistoricStatsModel;
import com.btanabe.adaptivewebscraper.tasks.DocumentParserTask;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 9/14/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentParserTaskTests {

    @Autowired
    @Qualifier("russellWilsonDocumentParserTask")
    private DocumentParserTask<YahooNflHistoricStatsModel> russellWilsonDocumentParser;

    @Autowired
    @Qualifier("yahooStatsPageQuarterbacks2015")
    private Document yahooQuarterbacksStatsPage2015;

    @Autowired
    @Qualifier("yahooStatsSeasonValueExtractorFactory")
    private ValueExtractorFactoryI<String> seasonYearValueExtractor;

    @Autowired
    @Qualifier("listeningExecutorService")
    private ListeningExecutorService executorService;

    @Autowired
    @Qualifier("multimapOutputObjectConstructor")
    private OutputObjectConstructorI<Multimap<ValueExtractorFactoryI<String>, String>> multimapOutputObjectConstructor;

    @Autowired
    @Qualifier("yahooPlayerStatsPageRussellWilson")
    private YahooNflHistoricStatsModel expectedRussellWilson;

    @Autowired
    @Qualifier("valueExtractorIClass")
    private Class<ValueExtractorFactoryI> valueExtractorClass;

    @Test
    public void shouldBeAbleToParseRussellWilsonsYahooStatsPageEntry() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();

        // This is set by the RecordCollector's global ValueExtractors.  Fake it here:
        russellWilsonFromCall.setSeason(2015);

        assertThat(russellWilsonFromCall, is(equalTo(expectedRussellWilson)));
    }

    @Test
    public void shouldBeAbleToParseRussellWilsonsReceptionsCorrectly() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();
        assertThat(russellWilsonFromCall.getTargets(), is(equalTo(expectedRussellWilson.getTargets())));
    }

    @Test
    public void shouldBeAbleToParseRussellWilsonsReceivingYardsCorrectly() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();
        assertThat(russellWilsonFromCall.getReceivingYards(), is(equalTo(expectedRussellWilson.getReceivingYards())));
    }

    @Test
    public void shouldBeAbleToParseRussellWilsonsReceivingYardsPerGameCorrectly() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();
        assertThat(russellWilsonFromCall.getReceivingYardsPerGame(), is(equalTo(expectedRussellWilson.getReceivingYardsPerGame())));
    }

    @Test
    public void shouldBeABleToParseRussellWilsonsReceivingYardsAfterCatchCorrectly() throws Exception {
        YahooNflHistoricStatsModel russellWilsonFromCall = russellWilsonDocumentParser.call();
        assertThat(russellWilsonFromCall.getReceivingYardsAfterCatch(), is(equalTo(expectedRussellWilson.getReceivingYardsAfterCatch())));
    }

    @Test
    public void shouldBeAbleToReturnTheProperDefaultableValueExtractorToSetterMethodMultiMapFromStatsPage() throws Exception {
        OutputObjectSetterI objectSetter = new MultimapObjectSetter();

        Multimap<ValueExtractorFactoryI, String> valueExtractorFactoryIToStringMultimap = LinkedHashMultimap.create();
        valueExtractorFactoryIToStringMultimap.put(seasonYearValueExtractor, "setSeason");

        DocumentParserTask<Multimap<ValueExtractorFactoryI<String>, String>> seasonDocumentParserTask = new DocumentParserTask<Multimap<ValueExtractorFactoryI<String>, String>>(executorService, yahooQuarterbacksStatsPage2015, valueExtractorFactoryIToStringMultimap, multimapOutputObjectConstructor, objectSetter);
        Multimap<ValueExtractorFactoryI<String>, String> yearValueExtractorAndSetterMethodMap = seasonDocumentParserTask.call();
        assertThat(yearValueExtractorAndSetterMethodMap.keys().stream().findFirst().get().createValueExtractor(yahooQuarterbacksStatsPage2015).call().findFirst().get(), is(equalTo((Object) 2015)));
    }
}
