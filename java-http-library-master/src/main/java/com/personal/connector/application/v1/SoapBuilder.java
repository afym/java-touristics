package com.personal.connector.application.v1;

import com.personal.connector.contract.BaseWebServiceException;
import com.personal.connector.entity.WebServiceEntity;
import com.personal.connector.entity.basic.RequestEntity;
import com.personal.connector.variable.HttpHeaders;
import com.predic8.wsdl.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public final class SoapBuilder {
    private WebServiceEntity webServiceEntity;
    private WebServiceEntity soapWebServiceEntity;
    private RequestEntity requestEntity;
    private RequestEntity soapRequestEntity;
    private Definitions definitions;

    public SoapBuilder(WebServiceEntity webServiceEntity, RequestEntity requestEntity) throws BaseWebServiceException {
        this.webServiceEntity = webServiceEntity;
        this.requestEntity = requestEntity;
        this.setWsdlDefinitions();
        this.setSoapWebServiceEntity();
        this.setSoapBuildRequest();
    }

    public WebServiceEntity getSoapWebServiceEntity() {
        return this.soapWebServiceEntity;
    }

    public RequestEntity getSoapRequest() {
        return this.soapRequestEntity;
    }

    public void setSoapWebServiceEntity() throws BaseWebServiceException {
        this.soapWebServiceEntity = new WebServiceEntity(this.webServiceEntity.getProtocol(), this.getSoapService(this.definitions), this.webServiceEntity.getPort());
    }

    private void setWsdlDefinitions() throws BaseWebServiceException {
        WSDLParser parser = new WSDLParser();
        this.definitions = parser.parse(this.getDefinition());
    }

    private void setSoapBuildRequest() throws BaseWebServiceException {
        this.soapRequestEntity = this.buildNewRequestEntity(this.requestEntity);
        this.setSoapAction(this.soapRequestEntity, this.definitions);
        this.setHost(this.soapRequestEntity, this.soapWebServiceEntity);
        this.soapRequestEntity.setAction("");
    }

    private String getSoapService(Definitions definition) throws BaseWebServiceException {
        for (Service service : definition.getServices()) {
            for (Port port : service.getPorts()) {
                try {
                    URL url = new URL(port.getAddress().getLocation());
                    String location = port.getAddress().getLocation();
                    location = location.replace(url.getProtocol() + "://", "");
                    return location;
                } catch (MalformedURLException e) {
                    throw new BaseWebServiceException("Something wrong happened ");
                }
            }
        }

        throw new BaseWebServiceException("The port address location is not found!. Is not possible continue.");
    }

    private void setSoapAction(RequestEntity requestEntity, Definitions definition) {
        for (Binding binding : definition.getBindings()) {
            for (BindingOperation operation : binding.getOperations()) {
                if (operation.getName().trim().equals(requestEntity.getAction().trim())) {
                    if(binding.getBinding() instanceof AbstractSOAPBinding) {
                        requestEntity.getHeaders().put(HttpHeaders.SOAP_ACTION, "\"" + operation.getOperation().getSoapAction() + "\"");
                        break;
                    }
                }
            }
        }

        if (requestEntity.getHeaders().get(HttpHeaders.SOAP_ACTION) == null) {
            requestEntity.getHeaders().put(HttpHeaders.SOAP_ACTION, "defaultSoapAction");
        }
    }

    private void setHost(RequestEntity requestEntity, WebServiceEntity webServiceEntity) throws  BaseWebServiceException{
        try {
            requestEntity.getHeaders().put(HttpHeaders.HOST, new URL(webServiceEntity.getUrl()).getHost().toString());
        } catch (MalformedURLException e) {
            throw new BaseWebServiceException("The URL is malformed. Problem : " + e.getMessage());
        }
    }

    private String getDefinition() throws BaseWebServiceException {
        HashMap<String, String> configuration = this.webServiceEntity.getHttpConfiguration().getConfiguration();

        if (configuration.get("wsdlType").equals(SoapConfiguration.WSDL_DISK)) {
            File file = new File(configuration.get("wsdlSegment"));

            if (!file.exists()) {
                throw new BaseWebServiceException("The file with the wsdl content, ¡was not found!");
            }

            return configuration.get("wsdlSegment");
        }

        StringBuilder builder = new StringBuilder();
        builder.append(this.webServiceEntity.getProtocol().value())
                .append(this.webServiceEntity.getHost())
                .append(configuration.get("wsdlSegment"));

        return builder.toString();
    }

    private RequestEntity buildNewRequestEntity(RequestEntity requestEntity) {
        RequestEntity cleanRequest = new RequestEntity();
        cleanRequest.setFormBody(requestEntity.getFormBody());
        cleanRequest.setAction(requestEntity.getAction());
        cleanRequest.setRequestSaver(requestEntity.getRequestSaver());
        cleanRequest.setResponseSaver(requestEntity.getResponseSaver());
        cleanRequest.setMethod(requestEntity.getMethod());
        cleanRequest.setTimeout(requestEntity.getTimeout());
        cleanRequest.setHeaders(requestEntity.getHeaders());
        cleanRequest.setRawBody(requestEntity.getRawBody());

        return cleanRequest;
    }
}
