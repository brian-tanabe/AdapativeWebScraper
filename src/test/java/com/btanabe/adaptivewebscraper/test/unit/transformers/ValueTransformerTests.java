package com.btanabe.adaptivewebscraper.test.unit.transformers;

import com.btanabe.adaptivewebscraper.transformers.DenominatorSelectorValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.EspnPositionValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.EspnTeamNameValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.NoNumberToZeroValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.NumeratorSelectorValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.PassThroughValueTransformer;
import com.btanabe.adaptivewebscraper.transformers.TableTagAdderValueTransformer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 5/14/16.
 */
public class ValueTransformerTests {

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
        assertThat(new EspnTeamNameValueTransformer().apply(", GB RB"), is(equalTo("GB")));
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
}
