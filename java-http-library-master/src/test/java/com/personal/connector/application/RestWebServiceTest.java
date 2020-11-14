package com.personal.connector.application;

import com.personal.connector.application.v1.RestWebService;
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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.junit.Assert.*;

public class RestWebServiceTest {
    final WireMockServer server = new WireMockServer(8596);
    WebServiceEntity serviceEntity = new WebServiceEntity(HttpProtocol.HTTP, "localhost", "8596");

    @Before
    public void setUpMockApi() {
        server.start();
        final WireMock wireMock = new WireMock(server.port());

        wireMock.register(WireMock.get(WireMock.urlEqualTo("/v1/numbers"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody("[1, 2, 3, 4, 5, 6, 7]")));

        wireMock.register(WireMock.get(WireMock.urlEqualTo("/v1/numbers/1"))
                .willReturn(WireMock.aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody("{\"message\" : \"error\"}")));

        wireMock.register(WireMock.post(WireMock.urlEqualTo("/v1/letters/5"))
                .withHeader("Authorization", equalTo("Bare: ab1cf45c1fzx"))
                .withRequestBody(equalTo("{\"letter\" : \"abcd\" }"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody("{\"message\" : \"error\"}")));

        wireMock.register(WireMock.post(WireMock.urlEqualTo("/login.do"))
                .withHeader("Authorization", equalTo("Bare: a1b2c3d4f5"))
                .withHeader("Content-Type", equalTo("application/x-www-form-urlencoded"))
                .withRequestBody(equalTo("a=1&b=2&c=3"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody("{\"message\" : \"done\"}")));

        wireMock.register(WireMock.post(WireMock.urlEqualTo("/book/12/do"))
                .withHeader("Authorization", equalTo("Bare: 1a82u2s90a918a1011010au7"))
                .withHeader("Content-Type", equalTo("application/json; charset=utf-8"))
                .withRequestBody(equalTo("{\"book\" : \"192020\", \"make\" : false}"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody("{\"message\" : \"done\", \"detail\": \"Your book is correct done\"}")));

        wireMock.register(WireMock.put(WireMock.urlEqualTo("/v2/shop/52"))
                .withHeader("Authorization", equalTo("some hard token"))
                .withHeader("Content-Type", equalTo("application/json; y=y y"))
                .withRequestBody(equalTo("{\"some\" : \"values\"}"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody("{\"message\" : \"put done\"}")));

        wireMock.register(WireMock.delete(WireMock.urlEqualTo("/v2/games/5856"))
                .withHeader("Authorization", equalTo("a1890f6b85abed58"))
                .withHeader("User-Agent", equalTo("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:54.0)"))
                .withHeader("Accept", equalTo("*/*"))
                .withHeader("Accept-Encoding", equalTo("gzip, deflate, br"))
                .withHeader("Accept-Language", equalTo("en-US,en;q=0.5"))
                .withHeader("Cache-Control", equalTo("max-age=0"))
                .withHeader("Connection", equalTo("keep-alive"))
                .withHeader("Host", equalTo("personal.com"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody("{\"message\" : \"delete done\"}")));

        wireMock.register(WireMock.get(WireMock.urlEqualTo("/v1/products/ids"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody("[1029, 1221, 2322, 2123, 122, 122, 12334, 89009]")));

        wireMock.register(WireMock.patch(WireMock.urlEqualTo("/v1/patch/timeout/20"))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(20 * 1000)
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody("[3.122, 525.55, 7595.55, 2.91]")));
    }

    @Test
    public void validateSimpleGetRequest() {
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity response = null;
        RequestEntity request = RequestEntityFactory.buildBasicRequest();
        request.setMethod(HttpMethods.GET);
        request.setAction("/v1/numbers");

        try {
            response = baseWebService.callAction(request);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        assertEquals(true, response.getResponse().length() > 0);
        assertEquals("[1, 2, 3, 4, 5, 6, 7]", response.getResponse());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void validateSimpleGetRequestError() {
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity response = null;
        RequestEntity request = RequestEntityFactory.buildBasicRequest();
        request.setMethod(HttpMethods.GET);
        request.setAction("/v1/numbers/1");

        try {
            response = baseWebService.callAction(request);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        assertEquals(true, response.getResponse().length() > 0);
        assertEquals("{\"message\" : \"error\"}", response.getResponse());
        assertEquals(500, response.getStatusCode());
    }

    @Test
    public void validateSimplePostRequest() {
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity response = null;
        RequestEntity request = RequestEntityFactory.buildBasicRequest();
        request.getHeaders().put(HttpHeaders.AUTHORIZATION, "Bare: ab1cf45c1fzx");
        request.setMethod(HttpMethods.POST);
        request.setAction("/v1/letters/5");
        request.setRawBody("{\"letter\" : \"abcd\" }");

        try {
            response = baseWebService.callAction(request);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        assertEquals(true, response.getResponse().length() > 0);
        assertEquals("{\"message\" : \"error\"}", response.getResponse());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void validateSimplePostFormRequest() {
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        HashMap<java.lang.String, java.lang.String> form = new HashMap<java.lang.String, java.lang.String>();
        form.put("a", "1");
        form.put("b", "2");
        form.put("c", "3");

        ResponseEntity response = null;
        RequestEntity request = RequestEntityFactory.buildBasicRequest();
        request.getHeaders().put(HttpHeaders.AUTHORIZATION, "Bare: a1b2c3d4f5");
        request.getHeaders().put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        request.setMethod(HttpMethods.POST);
        request.setAction("/login.do");
        request.setFormBody(form);

        try {
            response = baseWebService.callAction(request);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        assertEquals(true, response.getResponse().length() > 0);
        assertEquals("{\"message\" : \"done\"}", response.getResponse());
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void validateMultipleRequestPutAndDelete() {
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseR1 = null, responseR2 = null;
        RequestEntity requestR1 = RequestEntityFactory.buildBasicRequest();
        requestR1.getHeaders().put(HttpHeaders.AUTHORIZATION, "some hard token");
        requestR1.getHeaders().put(HttpHeaders.CONTENT_TYPE, "application/json; y=y y");
        requestR1.setMethod(HttpMethods.PUT);
        requestR1.setAction("/v2/shop/52");
        requestR1.setRawBody("{\"some\" : \"values\"}");

        RequestEntity requestR2 = RequestEntityFactory.buildBasicRequest();
        requestR2.setMethod(HttpMethods.DELETE);
        requestR2.getHeaders().put(HttpHeaders.AUTHORIZATION, "a1890f6b85abed58");
        requestR2.getHeaders().put(HttpHeaders.USER_AGENT, "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:54.0)");
        requestR2.getHeaders().put(HttpHeaders.ACCEPT, "*/*");
        requestR2.getHeaders().put(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
        requestR2.getHeaders().put(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.5");
        requestR2.getHeaders().put(HttpHeaders.CACHE_CONTROL, "max-age=0");
        requestR2.getHeaders().put(HttpHeaders.CONNECTION, "keep-alive");
        requestR2.getHeaders().put(HttpHeaders.HOST, "personal.com");
        requestR2.setAction("/v2/games/5856");

        try {
            responseR1 = baseWebService.callAction(requestR1);
            responseR2 = baseWebService.callAction(requestR2);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        assertEquals(true, responseR1.getResponse().length() > 0);
        assertEquals("{\"message\" : \"put done\"}", responseR1.getResponse());
        assertEquals(200, responseR1.getStatusCode());

        assertEquals(true, responseR2.getResponse().length() > 0);
        assertEquals("{\"message\" : \"delete done\"}", responseR2.getResponse());
        assertEquals(200, responseR2.getStatusCode());
    }

    @Test
    public void validateResponseOnSave() {
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        HttpSaverEntity responseSaver = new HttpSaverEntity("/tmp/log/request/files/", "response-85963.json");

        ResponseEntity responseR1 = null;
        RequestEntity requestR1 = RequestEntityFactory.buildBasicRequest();
        requestR1.setMethod(HttpMethods.GET);
        requestR1.setAction("/v1/products/ids");
        requestR1.setResponseSaver(responseSaver);

        try {
            responseR1 = baseWebService.callAction(requestR1);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        File file = new File(responseSaver.getFilePath());
        assertEquals(null, responseR1.getResponse());
        assertEquals("/tmp/log/request/files/response-85963.json", responseSaver.getFilePath());
        assertEquals(true, file.exists());
        file.delete();
    }

    @Test
    public void validateResponseAndRequestOnSave() {
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        HttpSaverEntity requestSaver = new HttpSaverEntity("/tmp/log/request/files/", "book-request-25325.json");
        HttpSaverEntity responseSaver = new HttpSaverEntity("/tmp/log/request/files/", "book-response-25325.json");

        ResponseEntity responseR1 = null;
        RequestEntity requestR1 = RequestEntityFactory.buildBasicRequest();
        requestR1.setMethod(HttpMethods.POST);
        requestR1.getHeaders().put(HttpHeaders.AUTHORIZATION, "Bare: 1a82u2s90a918a1011010au7");
        requestR1.getHeaders().put(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        requestR1.setRawBody("{\"book\" : \"192020\", \"make\" : false}");
        requestR1.setAction("/book/12/do");
        requestR1.setRequestSaver(requestSaver);
        requestR1.setResponseSaver(responseSaver);

        try {
            responseR1 = baseWebService.callAction(requestR1);
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }

        assertEquals(null, responseR1.getResponse());

        File requestFile = new File(requestSaver.getFilePath());
        assertEquals("/tmp/log/request/files/book-request-25325.json", requestSaver.getFilePath());
        assertEquals(true, requestFile.exists());
        requestFile.delete();

        File responseFile = new File(responseSaver.getFilePath());
        assertEquals("/tmp/log/request/files/book-response-25325.json", responseSaver.getFilePath());
        assertEquals(true, responseFile.exists());
        responseFile.delete();
    }

    @Test
    public void validateRequestWithTimeOutWithoutRequestFlag() {
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseR1 = null;
        RequestEntity requestR1 = RequestEntityFactory.buildBasicRequest();
        requestR1.setMethod(HttpMethods.PATCH);
        requestR1.setAction("/v1/patch/timeout/20");

        try {
            Date init = new Date();
            responseR1 = baseWebService.callAction(requestR1);
            Date end = new Date();

            long seconds = (end.getTime() - init.getTime()) / 1000;

            assertEquals(true, seconds >= 20);
            assertEquals("[3.122, 525.55, 7595.55, 2.91]", responseR1.getResponse());
        } catch (BaseWebServiceException e) {
            fail("Some errors happened with request handler. " + e.getMessage());
        }
    }

    @Test
    public void validateRequestWithTimeOutWithRequestFlag() {
        WebServiceOperable baseWebService = new RestWebService();
        baseWebService.addWebServiceEntity(serviceEntity);

        ResponseEntity responseR1 = null;
        RequestEntity requestR1 = RequestEntityFactory.buildBasicRequest();
        requestR1.setMethod(HttpMethods.PATCH);
        requestR1.setAction("/v1/patch/timeout/20");
        requestR1.setTimeout(5);

        Date init = new Date();
        String error = null;

        try {
            responseR1 = baseWebService.callAction(requestR1);
        } catch (BaseWebServiceException e) {
            error = e.getMessage();
        }

        Date end = new Date();
        long seconds = (end.getTime() - init.getTime()) / 1000;

        assertEquals(true, seconds >= 5);
        assertNull(null, responseR1);
        assertEquals(true, error.contains("Read timed out"));
    }

    @After
    public void downMockApi(){
        server.stop();
    }
}
