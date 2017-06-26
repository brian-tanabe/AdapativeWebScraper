package com.btanabe.adaptivewebscraper.test.unit.utilities;

import com.btanabe.adaptivewebscraper.utilities.SelectorStatementBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.btanabe.adaptivewebscraper.utilities.SelectorStatementEqualityOperators.CONTAINS;
import static com.btanabe.adaptivewebscraper.utilities.SelectorStatementEqualityOperators.ENDS_WITH;
import static com.btanabe.adaptivewebscraper.utilities.SelectorStatementEqualityOperators.EQUALS;
import static com.btanabe.adaptivewebscraper.utilities.SelectorStatementEqualityOperators.STARTS_WITH;
import static com.btanabe.adaptivewebscraper.utilities.SelectorStatementRelationshipOperators.DIRECT_CHILD;
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
    @Qualifier("espnPlayerProjectionRowSelectorStatement")
    private String playerRowSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageNextPageSelectorStatement")
    private String espnNextPageSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageRankSelectorStatement")
    private String espnRankSelectorStatement;

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
    @Qualifier("espnProjectionsPageRunningBacksRushingAttemptsSelectorStatement")
    private String playerStatPageRunningBacksRushingAttemptsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageQuarterbacksRushingAttemptsSelectorStatement")
    private String yahooPlayerStatPageQuarterbacksRushingAttemptsSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageRushingYardsSelectorStatement")
    private String espnPlayerProjectionsPageRushingYardsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageQuarterbacksRushingYardsSelectorStatement")
    private String yahooPlayerStatPageQuarterbacksRushingYardsSelectorStatement;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageRushingTouchdownsSelectorStatement")
    private String playerStatsPageRunningBacksRushingTouchdownsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageQuarterbacksRushingTouchdownsSelectorStatement")
    private String playerStatPageQuarterbacksRushingTouchdownsSelectorStatement;

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
    @Qualifier("yahooPlayerStatPagePlayerRowValueExtractor")
    private String yahooPlayerRowSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageNameSelectorStatement")
    private String yahooPlayerStatPageNameSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageTeamSelectorStatement")
    private String yahooPlayerStatPageTeamSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageGamesPlayedSelectorStatement")
    private String yahooPlayerStatPageGamesPlayedSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPagePassingCompletionsSelectorStatement")
    private String yahooPlayerStatsPagePassingCompletionsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPagePassingAttemptsSelectorStatement")
    private String yahooPlayerStatPagePassingAttemptsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageCompletionPercentageSelectorStatement")
    private String yahooPlayerStatPageCompletionPercentageSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPagePassingYardsSelectorStatement")
    private String yahooPlayerStatPagePassingYardsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPagePassingYardsPerGameSelectorStatement")
    private String yahooPlayerStatPagePassingYardsPerGameSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPagePassingYardsPerAttemptSelectorStatement")
    private String yahooPlayerStatPagePassingYardsPerAttemptSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPagePassingTouchdownsSelectorStatement")
    private String yahooPlayerStatPagePassingTouchdownsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageInterceptionsSelectorStatement")
    private String yahooPlayerStatPageInterceptionsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageSacksTakenSelectorStatement")
    private String yahooPlayerStatPageSacksTakenSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageSacksYardsLostSelectorStatement")
    private String yahooPlayerStatPageSacksYardsLostSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageRunningBacksRushingAttemptsSelectorStatement")
    private String yahooPlayerStatPageRushingAttemptsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageRunningBacksRushingYardsSelectorStatement")
    private String yahooPlayerStatPageRushingYardsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageRunningBacksRushingYardsPerGameSelectorStatement")
    private String yahooPlayerStatPageRunningBacksRushingYardsPerGameSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageQuarterbacksRushingYardsPerGameSelectorStatement")
    private String yahooPlayerStatPageQuarterbacksRushingYardsPerGameSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageRunningBacksRushingYardsPerAttemptSelectorStatement")
    private String yahooPlayerStatPageRunningBacksRushingYardsPerAttemptSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageQuarterbacksRushingYardsPerAttemptSelectorStatement")
    private String yahooPlayerStatPageQuarterbacksRushingYardsPerAttemptSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageReceivingYardsPerGameSelectorStatement")
    private String yahooPlayerStatPageReceivingYardsPerGameSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageRunningBacksRushingTouchdownsSelectorStatement")
    private String yahooPlayerStatPageRushingTouchdownsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageReceptionsSelectorStatement")
    private String yahooReceptionsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageTargetsSelectorStatement")
    private String yahooTargetsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageReceivingYardsSelectorStatement")
    private String yahooReceivingYardsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageReceivingYardsPerReceptionSelectorStatement")
    private String yahooReceivingYardsPerReceptionSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageYardsAfterCatchSelectorStatement")
    private String yahooYardsAfterCatchSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageReceivingTouchdownsSelectorStatement")
    private String yahooReceivingTouchdownsSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageRunningBacksFumblesSelectorStatement")
    private String yahooRunningBacksFumblesSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageQuarterbacksFumblesSelectorStatement")
    private String yahooQuarterbacksFumblesSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageRunningBacksFumblesLostSelectorStatement")
    private String yahooRunningBacksFumblesLostSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageQuarterbacksFumblesLostSelectorStatement")
    private String yahooQuarterbacksFumblesLostSelectorStatement;

    @Autowired
    @Qualifier("yahooPlayerStatPageNextPageValueExtractor")
    private String yahooPlayerStatPageNextPageSelectorStatement;

    //////////////////// statement builder implementation tests: ////////////////////
    @Test
    public void shouldBeAbleToBuildStatementsWithoutParentElements() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s]:eq(%d)", testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElements() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().otherTagName(testParentTagName).otherAttributeName("class").otherAttributeEqualityOperator(EQUALS).otherAttributeValue(testParentClassName).tagRelationship(DIRECT_CHILD).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElementsWhoseClassPrefixIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().otherTagName(testParentTagName).otherAttributeName("class").otherAttributeEqualityOperator(STARTS_WITH).otherAttributeValue(testParentClassName).tagRelationship(DIRECT_CHILD).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class ^= %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElementsWhoseClassSuffixIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().otherTagName(testParentTagName).otherAttributeName("class").otherAttributeEqualityOperator(ENDS_WITH).otherAttributeValue(testParentClassName).tagRelationship(DIRECT_CHILD).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class $= %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElementsWhoseClassSubstringIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().otherTagName(testParentTagName).otherAttributeName("class").otherAttributeEqualityOperator(CONTAINS).otherAttributeValue(testParentClassName).tagRelationship(DIRECT_CHILD).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class *= %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithParentElementsWhoseClassNameEqualsSpecifiedValue() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().otherTagName(testParentTagName).otherAttributeName("class").otherAttributeEqualityOperator(EQUALS).otherAttributeValue(testParentClassName).tagRelationship(DIRECT_CHILD).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class = %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithElementsWhoseClassPrefixIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().otherTagName(testParentTagName).otherAttributeName("class").otherAttributeEqualityOperator(EQUALS).otherAttributeValue(testParentClassName).tagRelationship(DIRECT_CHILD).tagName(testTagName).className(testClassName).classnameEqualityOperator(STARTS_WITH).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class ^= %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithElementsWhoseClassSuffixIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().otherTagName(testParentTagName).otherAttributeName("class").otherAttributeEqualityOperator(EQUALS).otherAttributeValue(testParentClassName).tagRelationship(DIRECT_CHILD).tagName(testTagName).className(testClassName).classnameEqualityOperator(ENDS_WITH).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class $= %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithElementsWhoseClassSubstringIsSpecified() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().otherTagName(testParentTagName).otherAttributeName("class").otherAttributeEqualityOperator(EQUALS).otherAttributeValue(testParentClassName).tagRelationship(DIRECT_CHILD).tagName(testTagName).className(testClassName).classnameEqualityOperator(CONTAINS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s] > %s[class *= %s]:eq(%d)", testParentTagName, testParentClassName, testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeAbleToBuildStatementsWithElementsWhoseClassNameEqualsSpecifiedValue() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().otherTagName(testParentTagName).otherAttributeName("class").otherAttributeEqualityOperator(EQUALS).otherAttributeValue(testParentClassName).tagRelationship(DIRECT_CHILD).tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
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
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().tagName(testTagName).className(testClassName).attributeName(testAttributeName).attributeNameEqualityOperator(STARTS_WITH).attributeValue(testAttributeValue).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[%s ^= %s].%s", testTagName, testAttributeName, testAttributeValue, testClassName))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnIllegalArgumentExceptionsWhenAttemptingToBuildStatementsWithBothAttributeAndClassEqualityOperators() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().tagName(testTagName).className(testClassName).classnameEqualityOperator(STARTS_WITH).attributeName(testAttributeName).attributeNameEqualityOperator(STARTS_WITH).attributeValue(testAttributeValue).build();
        testStatement.getObject();
    }

    //////////////////// Targeted selector statement builder tests: ////////////////////
    @Test
    public void shouldBeAbleToConstructPlayerRowSelectorStatementCorrectly() {
        assertThat(playerRowSelectorStatement, is(equalTo("tr[class ^= pncPlayerRow]")));
    }

    @Test
    public void shouldBeAbleToConstructEspnNextPageSelectorStatementCorrectly() {
        assertThat(espnNextPageSelectorStatement, is(equalTo("a:contains(NEXT)")));
    }

    @Test
    public void shouldBeAbleToConstructEspnPlayerRankSelectorStatementCorrectly() {
        assertThat(espnRankSelectorStatement, is(equalTo("td[class = playertableData]")));
    }

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
    public void shouldBeAbleToConstructRunningBacksRushingAttemptsSelectorStatementCorrectly() {
        assertThat(playerStatPageRunningBacksRushingAttemptsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(8)")));
    }

    @Test
    public void shouldBeAbleToConstructQuarterbackRushingAttemptsSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageQuarterbacksRushingAttemptsSelectorStatement, is(equalTo("td[class = yspscores]:eq(14)")));

    }

    @Test
    public void shouldBeAbleToConstructRushingYardsSelectorStatementCorrectly() {
        assertThat(espnPlayerProjectionsPageRushingYardsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(9)")));
    }

    @Test
    public void shouldBeAbleToConstructQuarterbackRushingYardsSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageQuarterbacksRushingYardsSelectorStatement, is(equalTo("td[class = yspscores]:eq(15)")));
    }

    @Test
    public void shouldBeAbleToConstructRunningBacksRushingTouchdownsSelectorStatementCorrectly() {
        assertThat(playerStatsPageRunningBacksRushingTouchdownsSelectorStatement, is(equalTo("td[class = playertableStat]:eq(10)")));
    }

    @Test
    public void shouldBeAbleToConstructQuarterbackRushingTouchdownsSelectorStatementCorrectly() {
        assertThat(playerStatPageQuarterbacksRushingTouchdownsSelectorStatement, is(equalTo("td[class = yspscores]:eq(18)")));
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
    public void shouldBeAbleToConstructYahooPlayerRowSelectorStatementCorrectly() {
        assertThat(yahooPlayerRowSelectorStatement, is(equalTo("tr[class ^= ysprow]")));
    }

    @Test
    public void shouldBeAbleToConstructYahooNameSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageNameSelectorStatement, is(equalTo("a[href ^= /nfl/players/]")));
    }

    @Test
    public void shouldBeAbleToConstructYahooTeamSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageTeamSelectorStatement, is(equalTo("a[href ^= /nfl/teams/]")));
    }

    @Test
    public void shouldBeAbleToConstructYahooGamesPlayedSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageGamesPlayedSelectorStatement, is(equalTo("td[class = yspscores]:eq(2)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooPassingCompletionsSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatsPagePassingCompletionsSelectorStatement, is(equalTo("td[class = yspscores]:eq(5)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooPassingAttemptsSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPagePassingAttemptsSelectorStatement, is(equalTo("td[class = yspscores]:eq(6)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooCompletionPercentageSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageCompletionPercentageSelectorStatement, is(equalTo("td[class = yspscores]:eq(7)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooPassingYardsSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPagePassingYardsSelectorStatement, is(equalTo("td[class = yspscores]:eq(8)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooPassingYardsPerGameSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPagePassingYardsPerGameSelectorStatement, is(equalTo("td[class = yspscores]:eq(9)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooPassingYardsPerAttemptSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPagePassingYardsPerAttemptSelectorStatement, is(equalTo("td[class = yspscores]:eq(10)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooPassingTouchdownsSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPagePassingTouchdownsSelectorStatement, is(equalTo("td[class = yspscores]:eq(11)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooInterceptionsSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageInterceptionsSelectorStatement, is(equalTo("td[class = yspscores]:eq(12)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooSacksTakenSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageSacksTakenSelectorStatement, is(equalTo("td[class = yspscores]:eq(20)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooSackYardsLostSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageSacksYardsLostSelectorStatement, is(equalTo("td[class = yspscores]:eq(21)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooRushingAttemptsSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageRushingAttemptsSelectorStatement, is(equalTo("td[class = yspscores]:eq(4)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooRushingYardsSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageRushingYardsSelectorStatement, is(equalTo("span[class = yspscores]")));
    }

    @Test
    public void shouldBeAbleToConstructYahooRunningBacksRushingYardsPerAttemptSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageRunningBacksRushingYardsPerAttemptSelectorStatement, is(equalTo("td[class = yspscores]:eq(7)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooQuarterbacksRushingYardsPerAttemptSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageQuarterbacksRushingYardsPerAttemptSelectorStatement, is(equalTo("td[class = yspscores]:eq(17)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooRunningBacksRushingYardsPerGameSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageRunningBacksRushingYardsPerGameSelectorStatement, is(equalTo("td[class = yspscores]:eq(6)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooQuarterbacksRushingYardsPerGameSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageQuarterbacksRushingYardsPerGameSelectorStatement, is(equalTo("td[class = yspscores]:eq(16)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooRushingTouchdownsStatementCorrectly() {
        assertThat(yahooPlayerStatPageRushingTouchdownsSelectorStatement, is(equalTo("td[class = yspscores]:eq(8)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooReceptionsSelectorStatementCorrectly() {
        assertThat(yahooReceptionsSelectorStatement, is(equalTo("td[class = yspscores]:eq(10)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooReceivingYardsPerGameSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageReceivingYardsPerGameSelectorStatement, is(equalTo("td[class = yspscores]:eq(13)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooTargetsSelectorStatementCorrectly() {
        assertThat(yahooTargetsSelectorStatement, is(equalTo("td[class = yspscores]:eq(11)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooReceivingYardsSelectorStatementCorrectly() {
        assertThat(yahooReceivingYardsSelectorStatement, is(equalTo("td[class = yspscores]:eq(12)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooReceivingYardsPerReceptionSelectorStatementCorrectly() {
        assertThat(yahooReceivingYardsPerReceptionSelectorStatement, is(equalTo("td[class = yspscores]:eq(14)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooYardsAfterCatchSelectorStatementCorrectly() {
        assertThat(yahooYardsAfterCatchSelectorStatement, is(equalTo("td[class = yspscores]:eq(16)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooReceivingTouchdownsSelectorStatementCorrectly() {
        assertThat(yahooReceivingTouchdownsSelectorStatement, is(equalTo("td[class = yspscores]:eq(18)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooRunningBacksFumblesSelectorStatementCorrectly() {
        assertThat(yahooRunningBacksFumblesSelectorStatement, is(equalTo("td[class = yspscores]:eq(20)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooQuarterbacksFumblesSelectorStatementCorrectly() {
        assertThat(yahooQuarterbacksFumblesSelectorStatement, is(equalTo("td[class = yspscores]:eq(23)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooRunningBacksFumblesLostSelectorStatementCorrectly() {
        assertThat(yahooRunningBacksFumblesLostSelectorStatement, is(equalTo("td[class = yspscores]:eq(21)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooQuarterbacksFumblesLostSelectorStatementCorrectly() {
        assertThat(yahooQuarterbacksFumblesLostSelectorStatement, is(equalTo("td[class = yspscores]:eq(24)")));
    }

    @Test
    public void shouldBeAbleToConstructYahooNextPageSelectorStatementCorrectly() {
        assertThat(yahooPlayerStatPageNextPageSelectorStatement, is(equalTo("option[selected]:contains(Season) ~ option:contains(Season)")));
    }
}
