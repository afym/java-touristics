package com.personal.connector.variable;

public enum HttpMethods {
    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH");

    private String value;

    HttpMethods(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
