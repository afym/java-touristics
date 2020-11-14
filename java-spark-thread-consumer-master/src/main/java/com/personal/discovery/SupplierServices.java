package com.personal.discovery;

import java.util.Map;

public class SupplierServices {
    private Map<String, Map<String, String>> services;

    public Map<String, Map<String, String>> getServices() {
        return services;
    }

    public void setServices(Map<String, Map<String, String>> services) {
        this.services = services;
    }
}
