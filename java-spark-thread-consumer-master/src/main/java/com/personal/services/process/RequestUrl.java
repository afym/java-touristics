package com.personal.services.process;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angel on 7/7/17.
 */
public class RequestUrl {
    private String baseHost;
    private String host;
    private String port;
    private List<String> segments;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBaseHost() {
        return baseHost;
    }

    public void setBaseHost(String baseHost) {
        this.baseHost = baseHost;
    }

    public List<String> getSegments() {
        return segments;
    }

    public void setSegments(List<String> segments) {
        this.segments = segments;
    }

    public List<String> getRequests(){
        List<String> requests = new ArrayList<>();

        for (String segment : this.segments) {
            requests.add(this.baseHost + segment);
        }

        return requests;
    }
}
