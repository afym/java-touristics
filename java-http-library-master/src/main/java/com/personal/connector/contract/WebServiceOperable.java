package com.personal.connector.contract;

import com.personal.connector.entity.basic.RequestEntity;
import com.personal.connector.entity.basic.ResponseEntity;
import com.personal.connector.entity.WebServiceEntity;

public interface WebServiceOperable {
    void addWebServiceEntity(WebServiceEntity webServiceEntity);
    ResponseEntity callAction(RequestEntity requestEntity) throws BaseWebServiceException;
}