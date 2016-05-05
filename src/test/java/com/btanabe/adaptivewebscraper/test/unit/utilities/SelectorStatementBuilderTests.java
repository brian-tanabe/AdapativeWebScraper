package com.btanabe.adaptivewebscraper.test.unit.utilities;

import com.btanabe.adaptivewebscraper.utilities.SelectorStatementBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private final String testClassName = "playertable";
    private final int testChildElementIndex = 6;

    @Test
    public void shouldBeAbleToBuildStatementsWithoutParentElements() throws Exception {
        SelectorStatementBuilder testStatement = SelectorStatementBuilder.builder().tagName(testTagName).className(testClassName).classnameEqualityOperator(EQUALS).childElementIndex(testChildElementIndex).build();
        assertThat(testStatement.getObject(), is(equalTo(String.format("%s[class = %s]:eq(%d)", testTagName, testClassName, testChildElementIndex))));
    }

    @Test
    public void shouldBeABleToBuildStatementsWithParentElements() throws Exception {
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
}
