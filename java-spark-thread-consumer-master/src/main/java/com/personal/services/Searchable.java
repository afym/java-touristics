package com.personal.services;

import com.personal.response.dto.SearchResponse;
import com.personal.services.process.DecoderOperator;

public interface Searchable {
    SearchResponse search(DecoderOperator request);
}
