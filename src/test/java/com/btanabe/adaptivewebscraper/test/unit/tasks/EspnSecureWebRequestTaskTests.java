package com.btanabe.adaptivewebscraper.test.unit.tasks;

import com.btanabe.adaptivewebscraper.secrets.EspnCredentialProvider;
import com.btanabe.adaptivewebscraper.tasks.SecureWebRequestTask;
import org.junit.Test;

public class EspnSecureWebRequestTaskTests {

    private final String playersPageUrl = "http://games.espn.com/fba/freeagency?leagueId=22603&teamId=4&seasonId=2018#&seasonId=2018&avail=-1&view=stats&context=freeagency";
    private final String projectionsPageUrl = "http://games.espn.com/fba/tools/projections?leagueId=22603&avail=1";

    @Test
    public void shouldBeAbleToAccessPasswordProtectedEspnPages() throws Exception {
        SecureWebRequestTask webRequestTask = new SecureWebRequestTask(new EspnCredentialProvider(), projectionsPageUrl);
        System.out.println(webRequestTask.call().html());
    }
}
