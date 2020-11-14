package com.personal.connector.application.v1;

import com.personal.connector.contract.BaseWebService;
import com.personal.connector.contract.BaseWebServiceException;
import com.personal.connector.entity.basic.RequestEntity;
import com.personal.connector.entity.basic.ResponseEntity;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.personal.connector.variable.HttpMethods.*;
import static com.personal.connector.variable.HttpMethods.PATCH;
import static org.apache.http.Consts.UTF_8;

public class BaseWebServiceV1 extends BaseWebService {
    public ResponseEntity callAction(RequestEntity requestEntity) throws BaseWebServiceException {
        HttpResponse response = this.buildClient(requestEntity);
        ResponseEntity responseEntity = new ResponseEntity();

        try {

            if (requestEntity.getResponseSaver() == null) {
                InputStream input = response.getEntity().getContent();
                responseEntity.setResponse(IOUtils.toString(input, UTF_8));
                responseEntity.setStatusCode(response.getStatusLine().getStatusCode());
                IOUtils.closeQuietly(input);
            }

            if (requestEntity.getRequestSaver() != null) {
                this.saveRequestAction(requestEntity);
            }

            if (requestEntity.getResponseSaver() != null) {
                this.saveResponseAction(requestEntity);
            }

            return responseEntity;
        } catch (IOException e) {
            throw new BaseWebServiceException("Some error happened in reading the input stream. Problem " + e.getMessage());
        }
    }

    private void saveRequestAction(RequestEntity requestEntity) throws BaseWebServiceException {
        try {
            String filePath = requestEntity.getRequestSaver().getFilePath();
            File file = new File(filePath);

            if (file.exists()) {
                throw new IOException("The file for is request already saved on the disk (" + filePath + ")");
            }

            FileUtils.writeStringToFile(file, requestEntity.getRawBody(), CharEncoding.UTF_8);
        } catch (IOException e) {
            throw new BaseWebServiceException("Some error happened in reading the input stream. Problem " + e.getMessage());
        }
    }

    private void saveResponseAction(RequestEntity requestEntity) throws BaseWebServiceException {
        HttpResponse response = this.buildClient(requestEntity);

        try {
            String filePath = requestEntity.getResponseSaver().getFilePath();

            if ((new File(filePath).exists())) {
                throw new IOException("The file for response is already saved on the disk (" + filePath + ")");
            }

            response.getEntity().writeTo(requestEntity.getResponseSaver().getOutputStream());
        } catch (IOException e) {
            throw new BaseWebServiceException("Some error happened in reading the input stream. Problem " + e.getMessage());
        }
    }

    private HttpResponse buildClient(RequestEntity requestEntity) throws BaseWebServiceException {
        HttpClient httpClient = this.buildHttpClient(requestEntity);
        HttpRequestBase requestBase = this.getHttpRequest(requestEntity);

        try {
            return httpClient.execute(requestBase);
        } catch (Exception e) {
            throw new BaseWebServiceException("A problem happened in execute your request. Problem : " + e.getMessage());
        }
    }

    private HttpRequestBase getHttpRequest(RequestEntity requestEntity) throws BaseWebServiceException {

        if (requestEntity.getMethod() == GET || requestEntity.getMethod() == DELETE) {
            return this.buildRequestBase(requestEntity);
        } else if (requestEntity.getMethod() == POST || requestEntity.getMethod() == PUT || requestEntity.getMethod() == PATCH) {
            return this.buildHttpEntity(requestEntity);
        }

        throw new BaseWebServiceException("It was not possible match your HTTP method. Check HttpMethods.");
    }

    private String getUrl(RequestEntity requestEntity) {
        StringBuilder builder = new StringBuilder();
        builder.append(this.webServiceEntity.getUrl())
                .append(requestEntity.getAction());

        return builder.toString();
    }

    private void setHttpHeaders(HttpRequestBase requestBase, RequestEntity requestEntity) {
        HashMap<String, String> headers = requestEntity.getHeaders();

        if (headers != null && !headers.isEmpty()) {
            for (String header : headers.keySet()) {
                requestBase.addHeader(header, headers.get(header));
            }
        }
    }

    private HttpRequestBase buildRequestBase(RequestEntity requestEntity) throws BaseWebServiceException {
        switch (requestEntity.getMethod()) {
            case GET:
                HttpGet get = new HttpGet(this.getUrl(requestEntity));
                this.setHttpHeaders(get, requestEntity);
                return get;
            case DELETE:
                HttpDelete delete = new HttpDelete(this.getUrl(requestEntity));
                this.setHttpHeaders(delete, requestEntity);
                return delete;
            default:
                throw new BaseWebServiceException("Not HTTP base method supported. Use GET or DELETE.");
        }
    }

    private HttpEntityEnclosingRequestBase buildHttpEntity(RequestEntity requestEntity) throws BaseWebServiceException {
        switch (requestEntity.getMethod()) {
            case POST:
                HttpPost post = new HttpPost(this.getUrl(requestEntity));
                this.setHttpHeaders(post, requestEntity);
                this.buildBody(post, requestEntity);
                return post;
            case PUT:
                HttpPut put = new HttpPut(this.getUrl(requestEntity));
                this.setHttpHeaders(put, requestEntity);
                this.buildBody(put, requestEntity);
                return put;
            case PATCH:
                HttpPatch patch = new HttpPatch(this.getUrl(requestEntity));
                this.setHttpHeaders(patch, requestEntity);
                this.buildBody(patch, requestEntity);
                return patch;
            default:
                throw new BaseWebServiceException("Not HTTP entity closing method supported. Use POST, PUT or PATCH.");
        }
    }

    private void buildBody(HttpEntityEnclosingRequestBase requestBase, RequestEntity requestEntity) throws BaseWebServiceException {
        HashMap<String, String> values = requestEntity.getFormBody();

        if (values != null && !values.isEmpty()) {
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

            for (String key : values.keySet()) {
                urlParameters.add(new BasicNameValuePair(key, values.get(key)));
            }

            try {
                requestBase.setEntity(new UrlEncodedFormEntity(urlParameters));
            } catch (UnsupportedEncodingException e) {
                throw new BaseWebServiceException("Not supported encoding form values. Problem : " + e.getMessage());
            }
        } else if (requestEntity.getRawBody() != null && requestEntity.getRawBody().length() > 0) {
            try {
                StringEntity stringEntity = new StringEntity(requestEntity.getRawBody());
                requestBase.setEntity(stringEntity);
            } catch (UnsupportedEncodingException e) {
                throw new BaseWebServiceException("Not supported encoding in raw body. Problem : " + e.getMessage());
            }
        }
    }

    private HttpClient buildHttpClient(RequestEntity requestEntity) {
        if (requestEntity.getTimeout() > 0) {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(requestEntity.getTimeout() * 1000)
                    .setConnectionRequestTimeout(requestEntity.getTimeout() * 1000)
                    .setSocketTimeout(requestEntity.getTimeout() * 1000).build();

            return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        }

        return HttpClientBuilder.create().build();
    }
}
