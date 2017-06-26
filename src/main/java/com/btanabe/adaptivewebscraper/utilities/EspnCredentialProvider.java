package com.btanabe.adaptivewebscraper.utilities;

/**
 * Created by Brian on 10/29/16.
 */
public class EspnCredentialProvider implements WebCredentialProvider {
    private String loginValue = "dusty1955";
    private String password = "snickers";

    @Override
    public String getLoginUrl() {
        return "https://registerdisney.go.com/jgc/v5/client/ESPN-ESPNCOM-PROD/guest/login?langPref=en-US";
    }

    @Override
    public String getUsername() {
        return loginValue;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
