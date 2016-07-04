package com.btanabe.adaptivewebscraper.test.unit.transformers;

import com.btanabe.adaptivewebscraper.transformers.DenominatorSelectorValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.EspnPositionValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.EspnTeamNameValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.NoNumberToZeroValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.NumeratorSelectorValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.PassThroughValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.TableTagAdderValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.UrlPathExploder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 5/14/16.
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ValueTransformerTests {

    @Autowired
    @Qualifier("teamNameIsolatorValueTransformer")
    private EspnTeamNameValueTransformer teamNameValueTransformer;

    @Autowired
    @Qualifier("yahooPlayerIdIsolatorValueTransformer")
    private UrlPathExploder yahooPlayerIdIsolator;

    @Test
    public void shouldBeAbleToAddTableTagsUsingTheTableTagAdderValueTransformer() {
        assertThat(new TableTagAdderValueTransformer().apply("test"), is(equalTo("<table>test</table>")));
    }

    @Test
    public void shouldBeAbleToPerformNoTransformationsUsingThePassThroughValueTransformer() {
        assertThat(new PassThroughValueTransformer().apply("test"), is(equalTo("test")));
    }

    @Test
    public void shouldBeAbleToIsolateTeamNamesUsingTheEspnTeamNameValueTransformer() {
        assertThat(teamNameValueTransformer.apply(", GB RB"), is(equalTo("GB")));
    }

    @Test
    public void shouldBeAbleToIsolateTeamNamesForTeamDefensesUsingTheEspnTeamNameValueTransformer() {
        assertThat(teamNameValueTransformer.apply("Steelers D/ST"), is(equalTo("Steelers")));
    }

    @Test
    public void shouldBeAbleToIsolatePositionsUsingTheEspnPositionValueTransformer() {
        assertThat(new EspnPositionValueTransformer().apply(", GB RB"), is(equalTo("RB")));
    }

    @Test
    public void shouldBeAbleToIsolatePassingCompletionsUsingTheDenominatorSelectorValueTransformer() {
        assertThat(new NumeratorSelectorValueTransformer().apply("200/300"), is(equalTo("200")));
    }

    @Test
    public void shouldBeAbleToIsolatePassingAttemptsUsingTheDenominatorSelectorValueTransformer() {
        assertThat(new DenominatorSelectorValueTransformer().apply("200/300"), is(equalTo("300")));
    }

    @Test
    public void shouldBeAbleToTransformHyphenHyphenToZeroUsingTheNoNumberToZeroValueTransformer() {
        assertThat(new NoNumberToZeroValueTransformer().apply("--"), is(equalTo("0")));
    }

    @Test
    public void shouldBeAbleToTransformFractionsOfHyphenHyphenToZeroOverZeroUsingTheNoNumberToZeroValueTransformer() {
        assertThat(new NoNumberToZeroValueTransformer().apply("--/--"), is(equalTo("0/0")));
    }

    @Test
    public void shouldBeAbleToTransformUrlPathsToYahooPlayerIds() {
        assertThat(yahooPlayerIdIsolator.apply("/nfl/players/8261"), is(equalTo(8261)));
    }
}
