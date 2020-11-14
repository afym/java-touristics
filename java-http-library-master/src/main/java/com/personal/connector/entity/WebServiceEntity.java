package com.personal.connector.entity;

import com.personal.connector.contract.HttpConfiguration;
import com.personal.connector.variable.HttpProtocol;

public class WebServiceEntity {
    private HttpProtocol protocol;
    private String host;
    private String port;
    private HttpConfiguration httpConfiguration;

    public WebServiceEntity(HttpProtocol protocol, String host) {
        this.protocol = protocol;
        this.host = host;
        this.port = "80";
    }

    public WebServiceEntity(HttpProtocol protocol, String host, String port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }

    public String getUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.protocol.value())
                .append(this.host);

        if (!this.port.equals("80")) {
            builder.append(":").append(this.port);
        }

        return builder.toString();
    }

    public HttpProtocol getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public void setProtocol(HttpProtocol protocol) {
        this.protocol = protocol;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public HttpConfiguration getHttpConfiguration() {
        return httpConfiguration;
    }

    public void setHttpConfiguration(HttpConfiguration httpConfiguration) {
        this.httpConfiguration = httpConfiguration;
    }
}
