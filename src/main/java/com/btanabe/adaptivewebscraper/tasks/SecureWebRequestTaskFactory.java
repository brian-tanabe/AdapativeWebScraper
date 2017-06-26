package com.btanabe.adaptivewebscraper.tasks;

import com.btanabe.adaptivewebscraper.utilities.WebCredentialProvider;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by Brian on 10/29/16.
 * <p>
 * Check out this guy's implementation and make it less disgusting:
 * https://github.com/cgunduz/espn/blob/master/src/main/java/com/cemgunduz/espn/matchup/Scraper.java
 */
public class SecureWebRequestTaskFactory {
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:32.0) Gecko/20100101 Firefox/32.0";

    private final WebCredentialProvider credentialProvider;

    private HttpContext localContext = new BasicHttpContext();
    private CookieStore cookieStore = new BasicCookieStore();
    private HttpClient client = HttpClientBuilder.create().build();

    public SecureWebRequestTaskFactory(WebCredentialProvider credentialProvider) throws IOException {
        this.credentialProvider = credentialProvider;
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

        getLoginCookiesFromServer();
    }

    public Callable<Document> createWebRequestTask(String urlToDownload) {

        return () -> {
            HttpGet request = new HttpGet(urlToDownload);
            request.setHeader("User-Agent", USER_AGENT);
            request.setHeader("Accept", "*/*");
            request.setHeader("Accept-Language", "en-US,en;q=0.8");

            HttpResponse response = client.execute(request, localContext);
            Document pageDocument = Jsoup.parse(response.getEntity().getContent(), "UTF8", urlToDownload);
            request.releaseConnection();

            return pageDocument;
        };
    }

    private void getLoginCookiesFromServer() throws IOException {
        HttpPost post = new HttpPost(credentialProvider.getLoginUrl());

        post.setHeader("Host", "registerdisney.go.com");
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Accept", "*/*");
        post.setHeader("Accept-Language", "en-US,en;q=0.8");
        post.setHeader("Accept-Encoding", "gzip, deflate, br");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Accept-Encoding", "gzip,deflate");
        post.setHeader("Origin", "https://cdn.registerdisney.go.com");
        post.setHeader("content-type", "application/json");
        post.setHeader("conversation-id", "46baa428-8e22-40d6-8c23-54ec61ba436d, e4e43789-a6e4-4796-b676-63c44287f013");
        post.setHeader("correlation-id", "191eb8f5-0671-4477-a8df-6175d01aec43");
        post.setHeader("authorization", "APIKEY 3lO++vACicBhj1C8ty/KHVr3cUX9z9ffBYLIguBvD0Ag3hZM1lGV9lcifdetGC59H7A+ziHR/iU/d7X9ZlwHICo=");
        post.setHeader("Referer", "https://cdn.registerdisney.go.com/v2/ESPN-ESPNCOM-PROD/en-US?include=config,l10n,js,html&scheme=http&postMessageOrigin=http%3A%2F%2Fwww.espn.com%2F&cookieDomain=www.espn.com&config=PROD&logLevel=INFO&topHost=www.espn.com&ageBand=ADULT&countryCode=US&cssOverride=https%3A%2F%2Fsecure.espncdn.com%2Fcombiner%2Fc%3Fcss%3Ddisneyid%2Fcore.css&responderPage=https%3A%2F%2Fwww.espn.com%2Flogin%2Fresponder%2Findex.html&buildId=157599bfa88");

        Gson gson = new Gson();
        String requestParameters = gson.toJson(credentialProvider);
        post.setEntity(new StringEntity(requestParameters));

        client.execute(post, localContext);
        post.releaseConnection();

        System.out.println("Store size=[" + cookieStore.getCookies().size() + "]");
        for (Cookie cookie : cookieStore.getCookies()) {
            System.out.println(String.format("cookie=[%s]", cookie.toString()));
        }
    }
}
