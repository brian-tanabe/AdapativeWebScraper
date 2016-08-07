package com.btanabe.adaptivewebscraper.test.utilities;


import com.btanabe.adaptivewebscraper.processors.HtmlTidier;
import org.apache.commons.io.IOUtils;

import java.net.URL;

import static org.apache.commons.io.Charsets.UTF_8;

/**
 * Created by Brian on 8/6/16.
 */
public class TestFileDownloader {

//    @Test
    public void downloadEspnPlayerProjectionsPageOne() throws Exception {
        String urlFormat = "http://games.espn.com/ffl/tools/projections?leagueId=84978&startIndex=%d";
        String fileNameFormat = "espn-player-projections-page-%d.dirty.html";
        for (int index = 0; index < 1; index += 1) {
            String url = String.format(urlFormat, index * 40);
            String fileName = String.format(fileNameFormat, index + 1);

            System.out.println(url);
            System.out.println(fileName);

            String pageContents = HtmlTidier.tidyHtmlAndConvertToXhtml(IOUtils.toString(new URL(url).openStream(), UTF_8));
            System.out.println(pageContents);
//            File newFile = new File("/Users/Brian/IdeaProjects/Adapative Web Scraper/src/test/resources/test-webpages/espn/projection-pages/" + fileName);
//            FileUtils.write(newFile, pageContents);
        }
    }
}
