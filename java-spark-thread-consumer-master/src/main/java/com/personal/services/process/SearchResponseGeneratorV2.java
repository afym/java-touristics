package com.personal.services.process;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SearchResponseGeneratorV2 implements ResponseGenerator{
    private Map<String, RequestUrl> requets;
    private  List<String> response;

    public SearchResponseGeneratorV2(Map<String, RequestUrl> requets) {
        this.requets = requets;
        this.response =  new ArrayList<>();
    }

    public String getResponse() {
        try {
            this.callUrlsInParallel();
        }catch (SearchException e) {
            // TODO : save logs result
        }

        return this.response.toString();
    }

    private void callUrlsInParallel() throws SearchException {
        List<String> urls = this.buildInlineUrls();
        // TODO: refactor this iterator. Is necessary two times?
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int index = 0; index < urls.size(); index++) {
            Future<InputStream> response = executor.submit(new Request(urls.get(index)));
            try {
                this.response.add(cleanBrackets(IOUtils.toString(response.get(), "UTF-8")));
            } catch (Exception e) {
                // TODO : catch debug log
            }
        }

        executor.shutdown();
    }

    private List<String> buildInlineUrls() throws SearchException{
        if (this.requets != null) {
            List<String> urls = new ArrayList<>();
            for (String supplier : this.requets.keySet()) {
                if (this.requets.get(supplier).getSegments() != null) {
                    StringBuilder buffer = new StringBuilder();
                    buffer.append(this.requets.get(supplier).getBaseHost());

                    for (String segment : this.requets.get(supplier).getSegments()) {
                        urls.add(buffer.append(segment).toString());
                    }
                }
            }

            return urls;
        }

        throw new SearchException("The requests urls have not been formed!");
    }

    public static String cleanBrackets(String response){
        response = response.substring(1);
        response = response.substring(0, response.length() - 1);
        return response;
    }
}

class Request implements Callable<InputStream> {

    private String url;

    public Request(String url) {
        this.url = url;
    }

    @Override
    public InputStream call() throws Exception {
        return new URL(url).openStream();
    }

}