package com.btanabe.adaptivewebscraper.utilities;

/**
 * Created by Brian on 10/29/16.
 */
public interface WebCredentialProvider {
    String getLoginUrl();

    String getUsername();

    String getPassword();
}
