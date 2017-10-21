package com.btanabe.adaptivewebscraper.secrets;

import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;

public interface CredentialProvider {

    String getUserName();

    String getPassword();

    String getLoginUrl();

    HttpPost getLoginPostRequest() throws UnsupportedEncodingException;

}
