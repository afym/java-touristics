package com.personal.connector.application.v1;

import com.personal.connector.contract.HttpConfiguration;

import java.util.HashMap;

public class SoapConfiguration implements HttpConfiguration {
    public static String WSDL_DISK = "0";
    public static String WSDL_ENDPOINT = "1";

    private String wsdlType;
    private String wsdlSegment;

    public static SoapConfiguration getInstance(String wsdlType, String wsdlSegment){
        return new SoapConfiguration(wsdlType, wsdlSegment);
    }

    public SoapConfiguration(String wsdlType, String wsdlSegment) {
        this.wsdlSegment = wsdlSegment;
        this.wsdlType = wsdlType;
    }

    public String getWsdlType() {
        return wsdlType;
    }

    public void setWsdlType(String wsdlType) {
        this.wsdlType = wsdlType;
    }

    public String getWsdlSegment() {
        return wsdlSegment;
    }

    public void setWsdlSegment(String wsdlSegment) {
        this.wsdlSegment = wsdlSegment;
    }

    @Override
    public HashMap<String, String> getConfiguration() {
        HashMap<String, String> configuration = new HashMap<>();

        configuration.put("wsdlType", this.wsdlType);
        configuration.put("wsdlSegment", this.wsdlSegment);

        return configuration;
    }
}