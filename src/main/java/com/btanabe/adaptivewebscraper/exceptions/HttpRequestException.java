package com.btanabe.adaptivewebscraper.exceptions;

public class HttpRequestException extends RuntimeException {

    public HttpRequestException(String url, int statusCode) {
        super(String.format("Failed to download to url=[%s].  Received response code: %d", url, statusCode));
    }

    public HttpRequestException(String message) {
        super(message);
    }
}
