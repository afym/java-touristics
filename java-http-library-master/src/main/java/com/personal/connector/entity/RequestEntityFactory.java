package com.personal.connector.entity;

import com.personal.connector.entity.basic.RequestEntity;
import com.personal.connector.variable.HttpHeaders;
import com.personal.connector.variable.HttpMethods;

import java.util.HashMap;

public class RequestEntityFactory {
    public static RequestEntity buildBasicRequest() {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setHeaders(new HashMap<String, String>());
        return requestEntity;
    }

    public static RequestEntity buildSoapRequest() {
        HashMap<String ,String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
        headers.put(HttpHeaders.CONTENT_TYPE, "text/xml;charset=UTF-8");
        headers.put(HttpHeaders.CONNECTION, "Keep-Alive");
        headers.put(HttpHeaders.USER_AGENT, "Apache-HttpClient/4.1.1 (java 1.8)");
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setHeaders(headers);
        requestEntity.setMethod(HttpMethods.POST);

        return requestEntity;
    }
}