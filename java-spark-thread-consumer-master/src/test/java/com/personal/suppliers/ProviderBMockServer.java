package com.personal.suppliers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ProviderBMockServer extends BaseMockServer {
    public ProviderBMockServer(String hostname, int port) throws IOException{
        super(hostname, port);
    }

    @Override
    public void buildDefinition() {
        this.addContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                Headers responseHeaders = httpExchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "application/json");
                String response = "{\"ProviderBV1MockServer\" : true}";
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
                String response = base.getResponseFromFile("test/services/v1/providerB/search.json");
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });
    }
}