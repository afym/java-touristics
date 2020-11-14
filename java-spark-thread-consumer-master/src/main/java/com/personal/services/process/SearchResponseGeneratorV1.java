package com.personal.services.process;

import io.parallec.core.ParallelClient;
import io.parallec.core.ResponseOnSingleTask;
import io.parallec.core.bean.StrStrMap;
import java.util.*;

/**
 * Created by angel on 7/10/17.
 */
public class SearchResponseGeneratorV1 implements ResponseGenerator{
    private Map<String, RequestUrl> requets;
    private ParallelClient parallelClient;
    private  List<String> response;
    private StringBuilder stringHosts;

    public SearchResponseGeneratorV1(Map<String, RequestUrl> requets) {
        this.requets = requets;
        this.parallelClient = new ParallelClient();
        this.response =  new ArrayList<>();
        this.stringHosts = new StringBuilder();
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
        Map<String, StrStrMap> urls = this.buildInlineUrls();

        if (urls != null) {
            String hosts = this.stringHosts.toString();
            hosts = hosts.substring(0, hosts.length() - 1);

            this.parallelClient.prepareHttpGet("$SEGMENT")
                    .setHttpPortReplaceable("$PORT")
                    .setConcurrency(1700)
                    .setTargetHostsFromString(hosts)
                    .setReplacementVarMapNodeSpecific(urls)
                    .execute((ResponseOnSingleTask res, Map<String, Object> responseContext) -> {
                        response.add(SearchResponseGeneratorV1.cleanBrackets(res.getResponseContent()));
                    });
        }
    }

    private Map<String, StrStrMap> buildInlineUrls() throws SearchException{
        if (this.requets != null) {
            Map<String, StrStrMap> mapper = new HashMap<>();
            for (String supplier : this.requets.keySet()) {
                if (this.requets.get(supplier).getSegments() != null) {
                    String host = this.requets.get(supplier).getHost();
                    String port = this.requets.get(supplier).getPort();
                    this.stringHosts.append(host).append(" ");
                    for (String segment : this.requets.get(supplier).getSegments()) {
                        mapper.put(host, new StrStrMap().addPair("PORT", port).addPair("SEGMENT", segment));
                    }
                }
            }

            return mapper;
        }

        throw new SearchException("The requests urls have not been formed!");
    }

    public static String cleanBrackets(String response){
        response = response.substring(1);
        response = response.substring(0, response.length() - 1);
        return response;
    }
}
