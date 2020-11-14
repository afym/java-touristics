package com.personal.services;

import com.personal.discovery.SupplierServices;
import com.personal.services.process.DecoderOperator;
import com.personal.services.process.SearchRequestGeneratorV1;
import com.personal.services.process.RequestUrl;
import com.personal.util.PropertiesException;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.Map;

/**
 * Created by angel on 7/7/17.
 */
public class SearchRequestGeneratorV1Test extends TestCase {
    private SearchMock searchMock = new SearchMock();

    @Test
    public void testGenerateUrlSimple() throws PropertiesException{
        SupplierServices sup = this.searchMock.getSupplierServices();
        DecoderOperator  ser = this.getDecoratorOperator();
        SearchRequestGeneratorV1 searchRequestGeneratorV1 = new SearchRequestGeneratorV1(ser, sup);

        Map<String, RequestUrl> requests = searchRequestGeneratorV1.getRequests();

        assertEquals("http://localhost:30000", requests.get("HTP").getBaseHost());
        assertEquals("http://localhost:30001", requests.get("EZL").getBaseHost());
        assertEquals("http://localhost:30002", requests.get("FTV").getBaseHost());
        assertEquals(2, requests.get("HTP").getSegments().size());
        assertEquals(3, requests.get("EZL").getSegments().size());
        assertEquals(1, requests.get("FTV").getSegments().size());

        for (String url : requests.get("HTP").getRequests()) {
            assertEquals(true, url.contains("http://"));
            assertEquals(true, url.contains("localhost:30000"));
            assertEquals(true, url.contains("/v1/search/1"));
        }

        for (String url : requests.get("EZL").getRequests()) {
            assertEquals(true, url.contains("http://"));
            assertEquals(true, url.contains("localhost:30001"));
            assertEquals(true, url.contains("/v1/search/1"));
        }

        for (String url : requests.get("FTV").getRequests()) {
            assertEquals(true, url.contains("http://"));
            assertEquals(true, url.contains("localhost:30002"));
            assertEquals(true, url.contains("/v1/search/1"));
        }
    }

    private DecoderOperator getDecoratorOperator() {
        DecoderOperator serve = new DecoderOperator();
        serve.setPartner("1");
        serve.setSearch(this.searchMock.getSearch());
        serve.setSuppliers(this.searchMock.getSuppliers());
        serve.unbindRequest();
        return serve;
    }
}
