package com.btanabe.adaptivewebscraper.tasks;

import com.btanabe.adaptivewebscraper.exceptions.HttpRequestException;
import com.btanabe.adaptivewebscraper.exceptions.LoginFailureException;
import com.btanabe.adaptivewebscraper.secrets.CredentialProvider;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.UUID;

/**
 * Useful links:
 * <p>
 * https://stackoverflow.com/questions/40352670/post-http-request-with-json-object (likely the most useful)
 * https://stackoverflow.com/questions/46107473/jsoup-authentication-with-espn
 * <p>
 * https://stackoverflow.com/questions/44659739/using-r-to-log-into-espn-fantasy-leagues-directing-rselenium-to-iframe-eleme?rq=1
 * https://stackoverflow.com/questions/25713366/using-r-as-my-browser-how-can-i-log-into-http-games-espn-go-com-ffl-signin-and
 * <p>
 * Probably outdated:
 * https://stackoverflow.com/questions/29016827/using-jsoup-to-login-to-espn-fantasy-football-league-and-scrape-stats
 */
public class SecureWebRequestTask extends WebRequestTask {
    private HttpContext localContext = new BasicHttpContext();
    private CookieStore cookieStore = new BasicCookieStore();
    private HttpClient client = HttpClientBuilder.create().build();

    public SecureWebRequestTask(CredentialProvider credentialProvider, String urlToDownload) throws IOException {
        super(urlToDownload);

        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        login(credentialProvider);
    }

    @Override
    public Document call() throws Exception {
//        HttpGet request = new HttpGet("https://cdn.registerdisney.go.com/v2/ESPN-FANTASYLM-PROD/en-US?include=config,l10n,js,html&scheme=http&postMessageOrigin=http%3A%2F%2Fgames.espn.com%2Ffba%2Ffreeagency%3FleagueId%3D22603%26teamId%3D4%26seasonId%3D2018%23%26seasonId%3D2018%26avail%3D-1&cookieDomain=espn.com&config=PROD&logLevel=INFO&topHost=games.espn.com&cssOverride=https%3A%2F%2Fsecure.espncdn.com%2Fcombiner%2Fc%3Fcss%3Ddisneyid%2Fcore.css%2Cdisneyid%2Ffantasy.css&responderPage=https%3A%2F%2Fwww.espn.com%2Flogin%2Fresponder%2F&buildId=15e813b8b01");
        HttpGet request = new HttpGet(urlToDownload);
        request.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        request.setHeader("Accept-Language", "en-US,en;q=0.8");
        request.setHeader("Accept-Encoding", "gzip, deflate");
        request.setHeader("Connection", "keep-alive");
        request.setHeader("Host", "games.espn.com");
        request.setHeader("DNT", "1");
        request.setHeader("Upgrade-Insecure-Requests", "1");
//        request.setHeader("Referer", urlToDownload);

        HttpResponse response = client.execute(request, localContext);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new HttpRequestException(urlToDownload, statusCode);
        }

        Document pageDocument = Jsoup.parse(response.getEntity().getContent(), "UTF8", urlToDownload);
        request.releaseConnection();

        return pageDocument;
    }

    private void login(CredentialProvider credentialProvider) throws IOException {


        HttpPost post = new HttpPost(credentialProvider.getLoginUrl());

        post.setHeader("Host", "ha.registerdisney.go.com");
        post.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        post.setHeader("Accept", "*/*");
        post.setHeader("Accept-Encoding", "gzip, deflate, br");
        post.setHeader("Accept-Language", "en-US,en;q=0.8");
        post.setHeader("DNT", "1");
        post.setHeader("Authorization", "APIKEY lVTC8EeclBAaxD4wV0CKq7EcRq/OIzyYaXsYnlZwNFyHKaCkfvEgkrzVvOCU4vM5cRkT2rlUfIFTtYjuxiYZ5dc=");
        post.setHeader("Cache-Control", "no-cache");
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("expires", "-1");
        post.setHeader("Host", "registerdisney.go.com");
        post.setHeader("Origin", "https://cdn.registerdisney.go.com");
        post.setHeader("pragma", "no-cache");
        post.setHeader("Referer", "https://cdn.registerdisney.go.com/v2/ESPN-ESPNCOM-PROD/en-US?include=config,l10n,js,html&scheme=http&postMessageOrigin=http%3A%2F%2Fwww.espn.com%2F&cookieDomain=www.espn.com&config=PROD&logLevel=INFO&topHost=www.espn.com&ageBand=ADULT&countryCode=US&cssOverride=https%3A%2F%2Fsecure.espncdn.com%2Fcombiner%2Fc%3Fcss%3Ddisneyid%2Fcore.css&responderPage=https%3A%2F%2Fwww.espn.com%2Flogin%2Fresponder%2Findex.html&buildId=15e813b8b01");
        post.setHeader("conversation-id", String.format("%s, %s", UUID.randomUUID().toString(), UUID.randomUUID().toString()));
        post.setHeader("correlation-id", UUID.randomUUID().toString());
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");

        String rawData = String.format("{\"loginValue\":\"%s\",\"password\":\"%s\"}", credentialProvider.getUserName(), credentialProvider.getPassword());
        StringEntity entity = new StringEntity(rawData);
        post.setEntity(entity);

        HttpResponse response = client.execute(post, localContext);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new LoginFailureException(credentialProvider.getLoginUrl(), statusCode);
        }

        post.releaseConnection();
    }
}
