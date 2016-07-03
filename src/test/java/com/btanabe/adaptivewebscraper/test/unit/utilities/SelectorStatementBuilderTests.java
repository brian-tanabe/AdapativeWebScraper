package com.btanabe.adaptivewebscraper.test.unit.utilities;

import com.btanabe.adaptivewebscraper.utilities.SelectorStatementBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.btanabe.adaptivewebscraper.utilities.SelectorStatementEqualityOperators.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Brian on 5/1/16.
 * <p>
 * This class builds Jsoup's element(s) selector statement
 * Please refer to: https://jsoup.org/cookbook/extracting-data/selector-syntax
 */
@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SelectorStatementBuilderTests {

    private final String testParentTagName = "tr";
    private final String testParentClassName = "some-class";

    private final String testTagName = "td";

    private final String testAttributeName = "href";
    private final String testAttributeValue = "some-website";

    private final String testClassName = "playertable";
    private final int testChildElementIndex = 6;

    private final String testContainsText = "NEXT";

    @Autowired
    @Qualifier("espnProjectionsPageNameSelectorStatement")
    private String playerNameXpathSelectorStatement;

    @Autowired
    @Qualifier("espnProjectionsPageTeamAndPositionSelectorStatement")
    private String playerTeamXpathSelectorStatement;

    @Autowired
    @Qualifier("espnProjectionsPagePassingCompletionsAndAttemptsSelectorStatement")
    private String playerPassingCompletionsAndAttemptsSelectorStatement;

    @Autowired
    @Qualifier("espnProjectionsPagePassingYardsSelectorStatement")
    private String playerPassingYardsSelectorStatement;

    @Autowired
    @Qualifier("espnProjectionsPagePassingTouchdownsSelectorStatement")
    private String playerPassingTouchdownsSelectorStatement;

    @Autowired
    @Qualifier("espnProjectionsPageInterceptionsSelectorStatement")
    private String playerInterceptionsSelectorStatement;

    @Autowired
    @Qualifier("espnProjectionsPageRushingAttemptsSelectorStatement")
    private String playerRushingAttemptsSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageRushingYardsSelectorStatement")
    private String playerRushingYardsSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageRushingTouchdownsSelectorStatement")
    private String playerRushingTouchdownsSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageReceptionsSelectorStatement")
    private String playerReceptionsSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageReceivingYardsSelectorStatement")
    private String playerReceivingYardsSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageReceivingTouchdownsSelectorStatement")
    private String playerReceivingTouchdownsSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageFantasyPointsSelectorStatement")
    private String playerFantasyPointsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageNameSelectorStatement")
    private String yahooPlayerStatPageNameSelectorStatement;

    //////////////////// statement builder implementation tests: ////////////////////
    @Test
    public void shouldBeAbleToBuildStatementsWithoutParentElements() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s]:eq(%d)", testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElements() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().parentTagName(testParentTagName).parentClassnameEqualityOperator(EQUALS).parentClassname(testParentClassName).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElementsWhoseClassPrefixIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().parentTagName(testParentTagName).parentClassnameEqualityOperator(STARTS_WITH).parentClassname(testParentClassName).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class ^= %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElementsWhoseClassSuffixIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().parentTagName(testParentTagName).parentClassnameEqualityOperator(ENDS_WITH).parentClassname(testParentClassName).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class $= %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElementsWhoseClassSubstringIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().parentTagName(testParentTagName).parentClassnameEqualityOperator(CONTAINS).parentClassname(testParentClassName).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class *= %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElementsWhoseClassNameEqualsSpecifiedValue() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().parentTagName(testParentTagName).parentClassnameEqualityOperator(EQUALS).parentClassname(testParentClassName).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithElementsWhoseClassPrefixIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().parentTagName(testParentTagName).parentClassnameEqualityOperator(EQUALS).parentClassname(testParentClassName).tagName(testTagName).className(testClassName).classnameEqualityOperator(STARTS_WITH).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class ^= %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithElementsWhoseClassSuffixIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().parentTagName(testParentTagName).parentClassnameEqualityOperator(EQUALS).parentClassname(testParentClassName).tagName(testTagName).className(testClassName).classnameEqualityOperator(ENDS_WITH).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class $= %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithElementsWhoseClassSubstringIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().parentTagName(testParentTagName).parentClassnameEqualityOperator(EQUALS).parentClassname(testParentClassName).tagName(testTagName).className(testClassName).classnameEqualityOperator(CONTAINS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class *= %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithElementsWhoseClassNameEqualsSpecifiedValue() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().parentTagName(testParentTagName).parentClassnameEqualityOperator(EQUALS).parentClassname(testParentClassName).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithContainsTextValuesSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().tagName(testTagName).containsText(testContainsText).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s:contains(%s)", testTagName, testContainsText))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithAttributeValuesSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().tagName(testTagName).attributeName(testAttributeName).attributeNameEqualityOperator(STARTS_WITH).attributeValue(testAttributeValue).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[%s ^= %s]", testTagName, testAttributeName, testAttributeValue))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithAttributeAndClassValuesSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).attributeName(testAttributeName).attributeNameEqualityOperator(STARTS_WITH).attributeValue(testAttributeValue).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[%s ^= %s].%s", testTagName, testAttributeName, testAttributeValue, testClassName))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnIllegalArgumentExceptionsWhenAttemptingToBuildStatementsWithBothAttributeAndClassEqualityOperators() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().tagName(testTagName).className(testClassName).classnameEqualityOperator(STARTS_WITH).attributeName(testAttributeName).attributeNameEqualityOperator(STARTS_WITH).attributeValue(testAttributeValue).build();
        testStatement.getObject();
    }

    //////////////////// Targeted selector statement builder tests: ////////////////////
    @Test
    public void shouldBeAbleToConstructNameSelectorStatementCorrectly() {
        assertThat(playerNameXpathSelectorStatement, is(equalTo("td[class = playertablePlayerName] > a[class = flexpop]")));
    }

    @Test
    public void shouldBeAbleToConstructTeamSelectorStatementCorrectly() {
        assertThat(playerTeamXpathSelectorStatement, is(equalTo("td[class = playertablePlayerName]")));
    }

    @Test
    public void shouldBeAbleToConstructPassingCompletionsAndAttemptsSelectorStatementCorrectly() {
        assertThat(playerPassingCompletionsAndAttemptsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(4)")));
    }

    @Test
    public void shouldBeAbleToConstructPassingYardsSelectorStatementCorrectly() {
        assertThat(playerPassingYardsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(5)")));
    }

    @Test
    public void shouldBeAbleToConstructPassingTouchdownsSelectorStatementCorrectly() {
        assertThat(playerPassingTouchdownsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(6)")));
    }

    @Test
    public void shouldBeAbleToConstructInterceptionsSelectorStatementCorrectly() {
        assertThat(playerInterceptionsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(7)")));
    }

    @Test
    public void shouldBeAbleToConstructRushingAttemptsSelectorStatementCorrectly() {
        assertThat(playerRushingAttemptsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(8)")));
    }

    @Test
    public void shouldBeAbleToConstructRushingYardsSelectorStatementCorrectly() {
        assertThat(playerRushingYardsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(9)")));
    }

    @Test
    public void shouldBeAbleToConstructRushingTouchdownsSelectorStatementCorrectly() {
        assertThat(playerRushingTouchdownsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(10)")));
    }

    @Test
    public void shouldBeAbleToConstructReceptionsSelectorStatementCorrectly() {
        assertThat(playerReceptionsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(11)")));
    }

    @Test
    public void shouldBeAbleToConstructReceivingYardsSelectorStatementCorrectly() {
        assertThat(playerReceivingYardsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(12)")));
    }

    @Test
    public void shouldBeAbleToConstructReceivingTouchdownsSelectorStatementCorrectly() {
        assertThat(playerReceivingTouchdownsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(13)")));
    }

    @Test
    public void shouldBeAbleToConstructFantasyPointsSelectorStatementCorrectly() {
        assertThat(playerFantasyPointsSelectorStatement, is(equalTo("td[class = playertableStat appliedPoints]")));
    }

    @Test
    public void shouldBeAbleToConstructYahooNameSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageNameSelectorStatement, is(equalTo("a[href ^= /nfl/players]")));
    }
}
