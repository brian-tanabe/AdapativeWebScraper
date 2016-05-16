package com.btanabe.adaptivewebscraper.test.integration.collectors;

import com.btanabe.adaptivewebscraper.collectors.RecordCollector;
import com.btanabe.adaptivewebscraper.models.EspnNflProjectionModel;
import com.btanabe.adaptivewebscraper.test.integration.MockWebRequestTaskBase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Brian on 5/1/16.
 */
@Ignore
public class EspnProjectionsPageRecordCollectorTests extends MockWebRequestTaskBase {

    @Autowired
    @Qualifier("espnNflPlayerProjectionsRecordCollector")
    private RecordCollector<EspnNflProjectionModel> espnNflProjectionsPageRecordCollector;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageEddieLacy")
    private EspnNflProjectionModel expectedEddieLacyModel;

    private List<EspnNflProjectionModel> collectedRecords;

    @Before
    public void performRecordCollection() throws Exception {
        if (collectedRecords == null) {
            collectedRecords = espnNflProjectionsPageRecordCollector.getAllRecordsAsList();
        }
    }

    @Test
    public void shouldBeAbleToFindFortyPlayersOnPageOne() throws Exception {
        assertThat(collectedRecords.size(), is(equalTo(1776)));
    }

    @Test
    public void shouldBeAbleToCorrectlyParseASinglePlayerCorrectly() throws Exception {
        EspnNflProjectionModel playerFromRecordCollector = collectedRecords.stream().filter(player -> player.getName().equals(expectedEddieLacyModel.getName())).findFirst().get();
        assertThat(playerFromRecordCollector, is(equalTo(expectedEddieLacyModel)));
    }
}
