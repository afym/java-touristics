package com.personal;

import com.personal.util.PropertiesException;
import com.personal.util.PropertiesJson;
import junit.framework.TestCase;
import org.junit.Test;

public class PropertiesJsonTest extends TestCase {
    @Test
    public void testInvalidService() {
        PropertiesJson propertiesJson = new PropertiesJson();
        String someService;

        try {
            someService = propertiesJson.getService("notfound");
        } catch (PropertiesException e) {
            someService = null;
        }

        assertNull(someService);
    }

    @Test
    public void testSupplierService() {
        PropertiesJson propertiesJson = new PropertiesJson();
        String suppliers;

        try {
            suppliers = propertiesJson.getService("supplier");
        } catch (PropertiesException e) {
            suppliers = null;
        }

        assertEquals(true, suppliers != null);
    }
}