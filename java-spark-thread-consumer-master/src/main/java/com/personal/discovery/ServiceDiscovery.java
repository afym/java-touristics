package com.personal.discovery;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class ServiceDiscovery {
    private String propertiesJson;

    public String getPropertiesJson() {
        return propertiesJson;
    }

    public void setPropertiesJson(String propertiesJson) {
        this.propertiesJson = propertiesJson;
    }

    public <T> T getServicesFrom(Class<T> service){
        return new Gson().fromJson(this.propertiesJson, (Type)service);
    }
}
