package com.btanabe.adaptivewebscraper.secrets;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class EspnCredentialProvider implements CredentialProvider {

    @Override
    public String getUserName() {
        return "dusty1955";
    }

    @Override
    public String getPassword() {
        return "charlie1986";
    }

    @Override
    public String getLoginUrl() {
        return "https://ha.registerdisney.go.com/jgc/v5/client/ESPN-ESPNCOM-PROD/guest/login?langPref=en-US";
    }

    @Override
    public HttpPost getLoginPostRequest() throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(getLoginUrl());

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

        String rawData = String.format("{\"loginValue\":\"%s\",\"password\":\"%s\"}", getUserName(), getPassword());
        StringEntity entity = new StringEntity(rawData);
        post.setEntity(entity);

        return post;
    }

}
