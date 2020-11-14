package com.personal.connector.contract;

import com.personal.connector.entity.WebServiceEntity;

public abstract class BaseWebService {
    protected WebServiceEntity webServiceEntity;

    public WebServiceEntity getWebServiceEntity() {
        return webServiceEntity;
    }

    public void addWebServiceEntity(WebServiceEntity webServiceEntity) {
        this.webServiceEntity = webServiceEntity;
    }
}
