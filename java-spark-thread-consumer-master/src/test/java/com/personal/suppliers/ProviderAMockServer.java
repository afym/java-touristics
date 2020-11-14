package com.personal.suppliers;


import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class ProviderAMockServer extends BaseMockServer {
    public ProviderAMockServer(String hostname, int port)throws IOException {
        super(hostname, port);
    }

    @Override
    public void buildDefinition() {
        this.addContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                Headers responseHeaders = httpExchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "application/json");
                String response = "{\"ProviderAV1MockServer\" : true}";
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        this.addContext("/v1/search", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                BaseHandler base = new BaseHandler();
                Headers responseHeaders = httpExchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "application/json");
                String response = base.getResponseFromFile("test/services/v1/providerA/search.json");
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
    }
}
