package com.personal.connector.application;

import com.personal.connector.application.servers.SoapMockArithmeticServer;
import com.personal.connector.application.v1.RestWebService;
import com.personal.connector.application.v1.SoapBuilder;
import com.personal.connector.application.v1.SoapConfiguration;
import com.personal.connector.application.v1.SoapWebService;
import com.personal.connector.contract.BaseWebServiceException;
import com.personal.connector.contract.WebServiceOperable;
import com.personal.connector.entity.RequestEntityFactory;
import com.personal.connector.entity.WebServiceEntity;
import com.personal.connector.entity.basic.RequestEntity;
import com.personal.connector.entity.basic.ResponseEntity;
import com.personal.connector.entity.saver.HttpSaverEntity;
import com.personal.connector.variable.HttpHeaders;
import com.personal.connector.variable.HttpMethods;
import com.personal.connector.variable.HttpProtocol;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class SoapWebServiceTest {

    final WireMockServer serverSoap1 = new WireMockServer(8597);

    @Before
    public void setUpMockApi() {
        serverSoap1.start();

        final WireMock wireMock1 = new WireMock(serverSoap1.port());

        // Arithmetic soap services

        wireMock1.register(WireMock.get(WireMock.urlEqualTo("/soap?wsdl"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml; charset=utf-8")
                        .withBody(SoapMockArithmeticServer.getWsdl())));

        wireMock1.register(WireMock.post(WireMock.urlEqualTo("/soap/action"))
                .withHeader("SOAPAction", equalTo("\"http://tempuri.org/Add\""))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml; charset=utf-8")
                        .withBody(SoapMockArithmeticServer.addResponse())));

        wireMock1.register(WireMock.post(WireMock.urlEqualTo("/soap/action"))
                .withHeader("SOAPAction", equalTo("\"http://tempuri.org/Divide\""))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withFixedDelay(5 * 1000)
                        .withHeader("Content-Type", "text/xml; charset=utf-8")
                        .withBody(SoapMockArithmeticServer.divideResponse())));

        wireMock1.register(WireMock.post(WireMock.urlEqualTo("/soap/action"))
                .withHeader("SOAPAction", equalTo("\"http://tempuri.org/Multiply\""))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml; charset=utf-8")
                        .withBody(SoapMockArithmeticServer.multiplyResponse())));

        wireMock1.register(WireMock.post(WireMock.urlEqualTo("/soap/action"))
                .withHeader("SOAPAction", equalTo("\"http://tempuri.org/Subtract\""))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml; charset=utf-8")
                        .withBody(SoapMockArithmeticServer.substractResponse())));
    }

    @Test
    public void validateSoapWsdl() {
        WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, "localhost", "8597");
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity response;
        RequestEntity request = RequestEntityFactory.buildBasicRequest();
        request.setMethod(HttpMethods.GET);
        request.setAction("/soap?wsdl");

        try {
            response = baseWebService.callAction(request);
            assertEquals(true, response.getResponse().contains("Add"));
            assertEquals(true, response.getResponse().contains("Subtract"));
            assertEquals(true, response.getResponse().contains("Divide"));
            assertEquals(true, response.getResponse().contains("Multiply"));
            assertEquals(false, response.getResponse().contains("Calculus"));
        } catch (BaseWebServiceException e) {
            fail("Fail the builder constructor " + e.getMessage());
        }
    }

    @Test
    public void validateSoapWebServiceForAddWithSoapBuilder() {
        SoapConfiguration soapConfiguration = SoapConfiguration.getInstance(SoapConfiguration.WSDL_ENDPOINT, SoapMockArithmeticServer.WSDL_SEGMENT);

        WebServiceEntity entity = new WebServiceEntity(HttpProtocol.HTTP, SoapMockArithmeticServer.HOST);
        entity.setHttpConfiguration(soapConfiguration);

        RequestEntity requestEntity = RequestEntityFactory.buildSoapRequest();
        requestEntity.setAction("Add");
        requestEntity.setRawBody(SoapMockArithmeticServer.addRequest());

        try {

            SoapBuilder builder = new SoapBuilder(entity, requestEntity);
            RequestEntity soapRequest = builder.getSoapRequest();
            WebServiceEntity soapEntity = builder.getSoapWebServiceEntity();

            assertEquals("", soapRequest.getAction());
            assertEquals("gzip,deflate", soapRequest.getHeaders().get(HttpHeaders.ACCEPT_ENCODING));
            assertEquals("text/xml;charset=UTF-8", soapRequest.getHeaders().get(HttpHeaders.CONTENT_TYPE));
            assertEquals("Keep-Alive", soapRequest.getHeaders().get(HttpHeaders.CONNECTION));
            assertEquals("Apache-HttpClient/4.1.1 (java 1.8)", soapRequest.getHeaders().get(HttpHeaders.USER_AGENT));
            assertEquals("\"http://tempuri.org/Add\"", soapRequest.getHeaders().get(HttpHeaders.SOAP_ACTION));
            assertEquals("localhost", soapRequest.getHeaders().get(HttpHeaders.HOST));

            assertEquals(SoapMockArithmeticServer.ACTION, soapEntity.getUrl());

            WebServiceOperable service = new RestWebService();
            service.addWebServiceEntity(soapEntity);
            String response = service.callAction(soapRequest).getResponse();
            assertEquals(true, response.contains("AddResponse"));
            assertEquals(true, response.contains("AddResult"));
            assertEquals(true, response.contains("3"));
        } catch (BaseWebServiceException e) {
            fail("Fail the builder constructor " + e.getMessage());
        }
    }

    @Test
    public void validateSoapWebServiceForAdd() {
        SoapConfiguration soapConfiguration = SoapConfiguration.getInstance(SoapConfiguration.WSDL_ENDPOINT, SoapMockArithmeticServer.WSDL_SEGMENT);
        WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, SoapMockArithmeticServer.HOST);
        serviceEntity.setHttpConfiguration(soapConfiguration);

        WebServiceOperable soapWebService = new SoapWebService();
        soapWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseEntity = null;
        RequestEntity entity = RequestEntityFactory.buildSoapRequest();
        entity.setAction("Add");
        entity.setRawBody(SoapMockArithmeticServer.addRequest());

        try {
            responseEntity = soapWebService.callAction(entity);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        String response = responseEntity.getResponse();

        assertEquals(true, response.contains("AddResponse"));
        assertEquals(true, response.contains("AddResult"));
        assertEquals(true, response.contains("3"));
    }

    @Test
    public void validateSoapWebServiceForSubtract() {
        SoapConfiguration soapConfiguration = SoapConfiguration.getInstance(SoapConfiguration.WSDL_ENDPOINT, SoapMockArithmeticServer.WSDL_SEGMENT);
        WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, SoapMockArithmeticServer.HOST);
        serviceEntity.setHttpConfiguration(soapConfiguration);

        WebServiceOperable soapWebService = new SoapWebService();
        soapWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseEntity = null;
        RequestEntity entity = RequestEntityFactory.buildSoapRequest();
        entity.setAction("Subtract");
        entity.setRawBody(SoapMockArithmeticServer.substractRequest());

        try {
            responseEntity = soapWebService.callAction(entity);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        String response = responseEntity.getResponse();

        assertEquals(true, response.contains("SubtractResponse"));
        assertEquals(true, response.contains("SubtractResult"));
        assertEquals(true, response.contains("3"));
    }

    @Test
    public void validateSoapWebServiceForMultiply() {
        SoapConfiguration soapConfiguration = SoapConfiguration.getInstance(SoapConfiguration.WSDL_ENDPOINT, SoapMockArithmeticServer.WSDL_SEGMENT);
        WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, SoapMockArithmeticServer.HOST);
        serviceEntity.setHttpConfiguration(soapConfiguration);

        WebServiceOperable soapWebService = new SoapWebService();
        soapWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseEntity = null;
        RequestEntity entity = RequestEntityFactory.buildSoapRequest();
        entity.setAction("Multiply");
        entity.setRawBody(SoapMockArithmeticServer.multiplyRequest());

        try {
            responseEntity = soapWebService.callAction(entity);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        String response = responseEntity.getResponse();

        assertEquals(true, response.contains("MultiplyResponse"));
        assertEquals(true, response.contains("MultiplyResult"));
        assertEquals(true, response.contains("3"));
    }

    @Test
    public void validateSoapWebServiceForDivide() {
        SoapConfiguration soapConfiguration = SoapConfiguration.getInstance(SoapConfiguration.WSDL_ENDPOINT, SoapMockArithmeticServer.WSDL_SEGMENT);
        WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, SoapMockArithmeticServer.HOST);
        serviceEntity.setHttpConfiguration(soapConfiguration);

        WebServiceOperable soapWebService = new SoapWebService();
        soapWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseEntity = null;
        RequestEntity entity = RequestEntityFactory.buildSoapRequest();
        entity.setAction("Divide");
        entity.setRawBody(SoapMockArithmeticServer.divideRequest());

        try {
            responseEntity = soapWebService.callAction(entity);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        String response = responseEntity.getResponse();

        assertEquals(true, response.contains("DivideResponse"));
        assertEquals(true, response.contains("DivideResult"));
        assertEquals(true, response.contains("3"));
    }

    @Test
    public void validateSoapWebServiceForDivideSaveOnDisk() {
        SoapConfiguration soapConfiguration = SoapConfiguration.getInstance(SoapConfiguration.WSDL_ENDPOINT, SoapMockArithmeticServer.WSDL_SEGMENT);
        WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, SoapMockArithmeticServer.HOST);
        serviceEntity.setHttpConfiguration(soapConfiguration);

        HttpSaverEntity requestSaver = new HttpSaverEntity("/tmp/log/request/files/", "soap-request-85963.xml");
        HttpSaverEntity responseSaver = new HttpSaverEntity("/tmp/log/request/files/", "soap-response-85963.xml");

        WebServiceOperable soapWebService = new SoapWebService();
        soapWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseEntity = null;
        RequestEntity entity = RequestEntityFactory.buildSoapRequest();
        entity.setAction("Divide");
        entity.setResponseSaver(responseSaver);
        entity.setRequestSaver(requestSaver);
        entity.setRawBody(SoapMockArithmeticServer.divideRequest());

        try {
            responseEntity = soapWebService.callAction(entity);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        assertEquals(null, responseEntity.getResponse());

        File requestFile = new File(requestSaver.getFilePath());
        assertEquals("/tmp/log/request/files/soap-request-85963.xml", requestSaver.getFilePath());
        assertEquals(true, requestFile.exists());
        requestFile.delete();

        File responseFile = new File(responseSaver.getFilePath());
        assertEquals("/tmp/log/request/files/soap-response-85963.xml", responseSaver.getFilePath());
        assertEquals(true, responseFile.exists());
        responseFile.delete();
    }

    @Test
    public void validateSoapWebServiceForDivideUsingTimeout() {
        SoapConfiguration soapConfiguration = SoapConfiguration.getInstance(SoapConfiguration.WSDL_ENDPOINT, SoapMockArithmeticServer.WSDL_SEGMENT);
        WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, SoapMockArithmeticServer.HOST);
        serviceEntity.setHttpConfiguration(soapConfiguration);

        WebServiceOperable soapWebService = new SoapWebService();
        soapWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseEntity = null;
        RequestEntity entity = RequestEntityFactory.buildSoapRequest();
        entity.setAction("Divide");
        entity.setTimeout(2);
        entity.setRawBody(SoapMockArithmeticServer.divideRequest());

        Date init = new Date();
        String error = null;

        try {
            responseEntity = soapWebService.callAction(entity);
        } catch (BaseWebServiceException e) {
            error = e.getMessage();
        }

        Date end = new Date();
        double seconds = (end.getTime() - init.getTime()) / 1000;

        assertEquals(true, seconds >= 1.0 && seconds <= 3.0);
        assertNull(responseEntity);
        assertEquals(true, error.contains("Read timed out"));
    }

    @Test
    public void validateSoapWebServiceForMultiplyOnDiskWsdl() throws IOException {
        File file = this.createWsdlOnDisk();
        SoapConfiguration soapConfiguration = SoapConfiguration.getInstance(SoapConfiguration.WSDL_DISK, "/tmp/soap/wsdl/arithmetic-wsdl.xml");
        WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, SoapMockArithmeticServer.HOST);
        serviceEntity.setHttpConfiguration(soapConfiguration);

        WebServiceOperable soapWebService = new SoapWebService();
        soapWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseEntity = null;
        RequestEntity entity = RequestEntityFactory.buildSoapRequest();
        entity.setAction("Multiply");
        entity.setRawBody(SoapMockArithmeticServer.multiplyRequest());

        try {
            responseEntity = soapWebService.callAction(entity);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        String response = responseEntity.getResponse();

        assertEquals(true, response.contains("MultiplyResponse"));
        assertEquals(true, response.contains("MultiplyResult"));
        assertEquals(true, response.contains("3"));

        file.delete();
    }

    @Test
    public void validateSoapWebServiceForAddOnDiskWsdl() throws IOException {
        File file = this.createWsdlOnDisk();
        SoapConfiguration soapConfiguration = SoapConfiguration.getInstance(SoapConfiguration.WSDL_DISK, "/tmp/soap/wsdl/arithmetic-wsdl.xml");
        WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, SoapMockArithmeticServer.HOST);
        serviceEntity.setHttpConfiguration(soapConfiguration);

        WebServiceOperable soapWebService = new SoapWebService();
        soapWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseEntity = null;
        RequestEntity entity = RequestEntityFactory.buildSoapRequest();
        entity.setAction("Add");
        entity.setRawBody(SoapMockArithmeticServer.addRequest());

        try {
            responseEntity = soapWebService.callAction(entity);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        String response = responseEntity.getResponse();

        assertEquals(true, response.contains("AddResponse"));
        assertEquals(true, response.contains("AddResult"));
        assertEquals(true, response.contains("3"));

        file.delete();
    }

    private File createWsdlOnDisk() throws IOException {
        File file = new File("/tmp/soap/wsdl/arithmetic-wsdl.xml");
        FileUtils.writeStringToFile(file, SoapMockArithmeticServer.getWsdl(), CharEncoding.UTF_8);
        return  file;
    }

    @After
    public void downMockApi() {
        serverSoap1.stop();
    }
}
