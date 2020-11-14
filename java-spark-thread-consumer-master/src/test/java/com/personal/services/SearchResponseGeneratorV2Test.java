package com.personal.services;

import com.personal.discovery.SupplierServices;
import com.personal.services.process.*;
import com.personal.suppliers.ProviderAMockServer;
import com.personal.suppliers.ProviderCMockServer;
import com.personal.suppliers.ProviderBMockServer;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class SearchResponseGeneratorV2Test extends TestCase {
    private ProviderBMockServer s1;
    private ProviderAMockServer s2;
    private ProviderCMockServer s3;
    private SearchMock searchMock = new SearchMock();

    @Test
    public void testSearchServiceOneSupplier() throws Exception {
        this.setUpMockServers();
        SupplierServices sup = this.searchMock.getSupplierServices();
        DecoderOperator ser = this.getDecoratorOperator();
        SearchRequestGeneratorV1 requestGeneratorV2 = new SearchRequestGeneratorV1(ser, sup);
        Map<String, RequestUrl> requests = requestGeneratorV2.getRequests();
        SearchResponseGeneratorV2 responseGeneratorV2 = new SearchResponseGeneratorV2(requests);
        String responses = responseGeneratorV2.getResponse();
        assertEquals(true, responses.contains("1062669"));
        assertEquals(true, responses.contains("3106532"));
        this.downMockServers();
    }

    private void setUpMockServers() throws IOException {
        this.s1 = new ProviderBMockServer("providerB-service", 30000);
        this.s2 = new ProviderAMockServer("providerA-service", 30001);
        this.s3 = new ProviderCMockServer("providerC-service", 30002);
        this.s1.buildDefinition();
        this.s2.buildDefinition();
        this.s3.buildDefinition();
        this.s1.start();
        this.s2.start();
        this.s3.start();
    }

    private void downMockServers() {
        this.s1.stop();
        this.s2.stop();
        this.s3.stop();
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

/*ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
Future<InputStream> response1 = executor.submit(new Request("http://localhost:30001"));
Future<InputStream> response2 = executor.submit(new Request("http://localhost:30002"));

assertEquals("", IOUtils.toString(response1.get(), "UTF-8") + IOUtils.toString(response2.get(), "UTF-8"));
executor.shutdown();
class Request implements Callable<InputStream> {

    private String url;

    public Request(String url) {
        this.url = url;
    }

    @Override
    public InputStream call() throws Exception {
        return new URL(url).openStream();
    }

}*/