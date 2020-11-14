package com.personal.connector.entity.basic;

import com.personal.connector.entity.saver.HttpSaverEntity;
import com.personal.connector.variable.HttpMethods;

import java.util.HashMap;

public class RequestEntity {
    private HashMap<String, String> headers;
    private String action;
    private HttpMethods method;
    private HashMap<String, String> formBody;
    private String rawBody;
    private int timeout;
    private HttpSaverEntity requestSaver;
    private HttpSaverEntity responseSaver;

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HttpMethods getMethod() {
        return method;
    }

    public void setMethod(HttpMethods method) {
        this.method = method;
    }

    public HashMap<String, String> getFormBody() {
        return formBody;
    }

    public void setFormBody(HashMap<String, String> formBody) {
        this.formBody = formBody;
    }

    public String getRawBody() {
        return rawBody;
    }

    public void setRawBody(String rawBody) {
        this.rawBody = rawBody;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public HttpSaverEntity getRequestSaver() {
        return requestSaver;
    }

    public void setRequestSaver(HttpSaverEntity requestSaver) {
        this.requestSaver = requestSaver;
    }

    public HttpSaverEntity getResponseSaver() {
        return responseSaver;
    }

    public void setResponseSaver(HttpSaverEntity responseSaver) {
        this.responseSaver = responseSaver;
    }
}
