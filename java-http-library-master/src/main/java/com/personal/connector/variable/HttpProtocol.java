package com.personal.connector.variable;

public enum HttpProtocol {
    HTTP("http://"),
    HTTPS("https://");

    private String value;

    HttpProtocol(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
