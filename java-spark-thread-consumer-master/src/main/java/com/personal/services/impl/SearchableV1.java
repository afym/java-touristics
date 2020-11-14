package com.personal.services.impl;

import com.personal.response.dto.SearchResponse;
import com.personal.services.Searchable;
import com.personal.services.process.DecoderOperator;

public class SearchableV1 implements Searchable {
    private static String SEARCH_URL = "/v1/:partner/:search";

    public SearchResponse search(DecoderOperator serveSearchRequest) {

        return null;
    }


}