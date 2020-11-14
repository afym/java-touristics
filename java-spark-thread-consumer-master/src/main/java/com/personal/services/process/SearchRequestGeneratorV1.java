package com.personal.services.process;

import com.personal.discovery.SupplierServices;
import com.personal.repository.DestinyCodes;
import com.personal.request.dto.SearchRequest;
import com.personal.util.ParamOperation;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by angel on 7/7/17.
 */
public class SearchRequestGeneratorV1 implements RequestGenerator{
    private static String SEARCH_URL = "/v1/search/:partner/:search";
    private DecoderOperator decoderOperator;
    private SupplierServices supplierServices;
    private Map<String, RequestUrl> requestsUrls;

    public SearchRequestGeneratorV1(DecoderOperator decoderOperator, SupplierServices supplierServices){
        this.decoderOperator = decoderOperator;
        this.decoderOperator.unbindRequest();
        this.supplierServices = supplierServices;
        this.requestsUrls = new HashMap<>();
    }

    public Map<String, RequestUrl> getRequests() {
        this.generateBaseHost();
        this.generateUrlByDestiny();

        return requestsUrls;
    }

    private void generateBaseHost() {
        Map<String, Map<String, String>> services = supplierServices.getServices();

        for (String supplier : this.decoderOperator.getSuppliers()) {
            String url = "http://:host::port";
            RequestUrl requestUrl = new RequestUrl();
            url = url.replaceAll(":host", services.get(supplier).get("host"));
            url = url.replace(":port", services.get(supplier).get("port"));
            requestUrl.setHost(services.get(supplier).get("host"));
            requestUrl.setPort(services.get(supplier).get("port"));
            requestUrl.setBaseHost(url);
            this.requestsUrls.put(supplier, requestUrl);
        }
    }

    private void generateUrlByDestiny() {
        for (String supplier : this.requestsUrls.keySet()) {
            String[] destinies = DestinyCodes.getDestiny(supplier, decoderOperator.getSearch().getDestination());
            String searchJson = (new Gson()).toJson(decoderOperator.getSearch());

            if (destinies != null) {
                List<String> segments = new ArrayList<>();

                for (String destiny : destinies) {
                    SearchRequest searchRequest = (new Gson()).fromJson(searchJson, SearchRequest.class);
                    searchRequest.setDestination(destiny);
                    String segment = SEARCH_URL;
                    segment = segment.replaceAll(":partner", this.decoderOperator.getPartner());
                    segment = segment.replaceAll(":search", ParamOperation.encrypt(searchRequest));
                    segments.add(segment);
                }

                this.requestsUrls.get(supplier).setSegments(segments);
            }
        }
    }
}
