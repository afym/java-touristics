package com.personal.services;

import com.personal.request.dto.AgesRequest;
import com.personal.request.dto.RoomRequest;
import com.personal.request.dto.SearchRequest;
import com.personal.services.process.DecoderOperator;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by angel on 7/7/17.
 */
public class DecoderOperatorTest extends TestCase {
    private SearchMock searchMock = new SearchMock();

    @Test
    public void testServeSearchRequest() {
        DecoderOperator serve = new DecoderOperator();
        serve.setPartner("2");
        serve.setSearch(this.searchMock.getSearch());
        serve.setSuppliers(this.searchMock.getSuppliers());
        serve.unbindRequest();

        SearchRequest searchRequest = serve.getSearch();

        assertEquals("136", searchRequest.getNationality());
        assertEquals("es", searchRequest.getLanguage());
        assertEquals("50001", searchRequest.getDestination());
        assertEquals("01-09-2017", searchRequest.getCheckout());
        assertEquals("1", searchRequest.getNights());
        assertEquals("2", searchRequest.getRooms());

        RoomRequest[] roomRequest = serve.getSearch().getListrooms();

        assertEquals("1", roomRequest[1].getAdults());
        assertEquals("1", roomRequest[1].getCount());
        assertEquals("2", roomRequest[1].getChildren());

        AgesRequest[] agesRequests = roomRequest[1].getChildrenages();

        assertEquals("5", agesRequests[0].getAge());
        assertEquals("4", agesRequests[1].getAge());

        String[] suppliers = serve.getSuppliers();

        assertEquals("HTP", suppliers[0]);
        assertEquals("EZL", suppliers[1]);
        assertEquals("FTV", suppliers[2]);
    }
}
