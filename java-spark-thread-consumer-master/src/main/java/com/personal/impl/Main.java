package com.personal.impl;


import com.personal.discovery.ServiceDiscovery;
import com.personal.discovery.SupplierServices;
import com.personal.services.process.*;
import com.personal.util.PropertiesJson;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {
        get("/", (request, response) -> {
            response.type("application/json");
            return "{\"service\" : \"b2b.search.centralizer\"}";
        });

        get("/help", (request, response) -> {
            response.type("application/json");
            Map<String, String> envs = new HashMap<>();
            envs.put("ENVIRONMENT", System.getenv().get("ENVIRONMENT"));
            envs.put("LOG_PATH", System.getenv().get("LOG_PATH"));

            PropertiesJson propertiesJson = new PropertiesJson();
            envs.put("SUPPLIER_SERVICE", propertiesJson.getService("supplier"));

            response.type("application/json");
            return envs.toString();
        });

       get("/v1/search/:partner/:suppliers/:search", (request, response) -> {
           response.type("application/json");

           try {
               DecoderOperator decoder = new DecoderOperator();
               decoder.setPartner(request.params(":partner"));
               decoder.setSuppliers(request.params(":suppliers"));
               decoder.setSearch(request.params(":search"));

               PropertiesJson propertiesJson = new PropertiesJson();
               String properties = propertiesJson.getService("supplier");

               ServiceDiscovery serviceDiscovery = new ServiceDiscovery();
               serviceDiscovery.setPropertiesJson(properties);
               SupplierServices supplier = serviceDiscovery.getServicesFrom(SupplierServices.class);

               SearchRequestGeneratorV1 requestGeneratorV1 = new SearchRequestGeneratorV1(decoder, supplier);
               Map<String, RequestUrl> requests = requestGeneratorV1.getRequests();
               ResponseGenerator responseGeneratorV2 = new SearchResponseGeneratorV2(requests);

               return responseGeneratorV2.getResponse();
           } catch (Exception e) {
                return  "{\"error\" : \"" + e.getMessage() + "\"}";
           }
        });
    }
}