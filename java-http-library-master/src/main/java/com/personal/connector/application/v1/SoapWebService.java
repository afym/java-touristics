package com.personal.connector.application.v1;

import com.personal.connector.contract.BaseWebServiceException;
import com.personal.connector.entity.WebServiceEntity;
import com.personal.connector.entity.basic.RequestEntity;
import com.personal.connector.contract.WebServiceOperable;
import com.personal.connector.entity.basic.ResponseEntity;

public final class SoapWebService implements WebServiceOperable {
    private WebServiceEntity webServiceEntity;

    public void addWebServiceEntity(WebServiceEntity webServiceEntity) {
        this.webServiceEntity = webServiceEntity;
    }

    public ResponseEntity callAction(RequestEntity requestEntity) throws BaseWebServiceException {
        SoapBuilder soapBuilder = this.getSoapBuilder(requestEntity);
        WebServiceOperable service = new RestWebService();
        service.addWebServiceEntity(soapBuilder.getSoapWebServiceEntity());

        return service.callAction(soapBuilder.getSoapRequest());
    }

    private SoapBuilder getSoapBuilder(RequestEntity requestEntity) throws BaseWebServiceException {
        return new SoapBuilder(this.webServiceEntity, requestEntity);
    }

}
