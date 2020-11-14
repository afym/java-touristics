package com.personal.connector.entity;

import com.personal.connector.variable.HttpProtocol;

import org.junit.Test;
import static org.junit.Assert.*;

public class WebServiceEntityTest {

    @Test
    public void validateUrlConstruction() {
        WebServiceEntity ws1 = new WebServiceEntity(HttpProtocol.HTTP, "personal.com", "80");
        WebServiceEntity ws2 = new WebServiceEntity(HttpProtocol.HTTPS, "otherone.com", "8081");
        WebServiceEntity ws3 = new WebServiceEntity(HttpProtocol.HTTP, "hotels.com.pe", "8078");
        WebServiceEntity ws4 = new WebServiceEntity(HttpProtocol.HTTP, "api.other.com/4.1_test/hotel/b2bHotelSOAP?wsdl");

        assertEquals("http://personal.com", ws1.getUrl());
        assertEquals("https://otherone.com:8081", ws2.getUrl());
        assertEquals("http://hotels.com.pe:8078", ws3.getUrl());
        assertEquals("http://api.other.com/4.1_test/hotel/b2bHotelSOAP?wsdl", ws4.getUrl());
    }
}
