package com.personal.suppliers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

abstract public class BaseMockServer {
    private HttpServer server;
    private String hostname;
    private int port;

    public BaseMockServer(String hostname, int port) throws IOException  {
        this.hostname = hostname;
        this.port = port;
        this.server = HttpServer.create(new InetSocketAddress(this.port), 0);
    }

    protected void addContext(String path, HttpHandler handler) {
        this.server.createContext(path, handler);
    }

    public void start(){
        this.server.setExecutor(null);
        this.server.start();
    }

    public void stop(){
        this.server.stop(0);
    }

    public static String dnsResolution(String hostname) {
        hostname = "127.0.0.1";
        return hostname;
    }

    abstract public void buildDefinition();
}