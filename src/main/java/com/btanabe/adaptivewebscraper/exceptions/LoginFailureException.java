package com.btanabe.adaptivewebscraper.exceptions;

public class LoginFailureException extends HttpRequestException {

    public LoginFailureException(String url, int statusCode) {
        super(String.format("Failed to login to url=[%s].  Received response code: %d", url, statusCode));
    }
}
