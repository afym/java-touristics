package com.personal;

import com.personal.discovery.ServiceDiscovery;
import com.personal.discovery.SupplierServices;
import com.personal.util.PropertiesException;
import com.personal.util.PropertiesJson;
import junit.framework.TestCase;
import org.junit.Test;

public class ServiceDiscoveryTest extends TestCase {
    @Test
    public void testServiceSupplier() throws PropertiesException{
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery();
        serviceDiscovery.setPropertiesJson(this.getProperties());
        SupplierServices services = serviceDiscovery.getServicesFrom(SupplierServices.class);

        assertEquals("providerA-service", services.getServices().get("EZL").get("host"));
        assertEquals("providerB-service", services.getServices().get("HTP").get("host"));
    }

    private String getProperties() throws PropertiesException {
        PropertiesJson propertiesJson = new PropertiesJson();
        return propertiesJson.getService("supplier");
    }
}
