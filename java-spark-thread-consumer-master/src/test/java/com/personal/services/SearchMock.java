package com.personal.services;

import com.personal.discovery.ServiceDiscovery;
import com.personal.discovery.SupplierServices;
import com.personal.util.ParamOperation;
import com.personal.util.PropertiesException;
import com.personal.util.PropertiesJson;

/**
 * Created by angel on 7/7/17.
 */
public class SearchMock {
    public SupplierServices getSupplierServices() throws PropertiesException {
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery();
        serviceDiscovery.setPropertiesJson(this.getProperties());
        return serviceDiscovery.getServicesFrom(SupplierServices.class);
    }

    public String getProperties() throws PropertiesException {
        PropertiesJson propertiesJson = new PropertiesJson();
        return propertiesJson.getService("supplier.test");
    }

    public String getSuppliers() {
        String[] suppliers = new String[]{"HTP", "EZL", "FTV"};
        return ParamOperation.encrypt(suppliers);
    }

    public String getSearch(){
        // see the original request in resources/test/services/v1/request.json
        return "eyJuYXRpb25hbGl0eSI6IjEzNiIsImxhbmd1YWdlIjoiZXMiLCJ0b2tlbiI6IjVsamkyNm02ajluNXNhcDc2NXY2bHE0cTFxOHVrNnBwIiwiZGVzdGluYXRpb24iOjUwMDAxLCJjaGVja2luIjoiMzEtMDgtMjAxNyIsImNoZWNrb3V0IjoiMDEtMDktMjAxNyIsIm5pZ2h0cyI6IjEiLCJyb29tcyI6IjIiLCJsaXN0cm9vbXMiOlt7ImNvdW50IjoiMSIsImFkdWx0cyI6IjIiLCJjaGlsZHJlbiI6IjEiLCJjaGlsZHJlbmFnZXMiOlt7ImFnZSI6IjIifV19LHsiY291bnQiOiIxIiwiYWR1bHRzIjoiMSIsImNoaWxkcmVuIjoiMiIsImNoaWxkcmVuYWdlcyI6W3siYWdlIjoiNSJ9LHsiYWdlIjoiNCJ9XX1dLCJvbmx5YXZhaWxhYmxlIjpmYWxzZX0,";
    }
}
