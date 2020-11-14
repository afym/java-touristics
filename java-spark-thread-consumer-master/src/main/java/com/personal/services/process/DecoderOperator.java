package com.personal.services.process;

import com.personal.request.dto.SearchRequest;
import com.personal.util.ParamOperation;

/**
 * Created by angel on 7/7/17.
 */
public class DecoderOperator {
    private String suppliersEncrypted;
    private String searchEncrypted;
    private String[] suppliersArray;
    private String partner;
    private SearchRequest search;

    public void setSuppliers(String suppliers) {
        this.suppliersEncrypted = suppliers;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public void setSearch(String search) {
        this.searchEncrypted = search;
    }

    public void unbindRequest()
    {
        this.decodeSuppliers();
        this.decodePartner();
    }

    public String[] getSuppliers()
    {
        return this.suppliersArray;
    }

    public String getPartner() {
        return partner;
    }

    public SearchRequest getSearch(){
        return this.search;
    }

    private void decodeSuppliers() {
        this.suppliersArray = ParamOperation.unencrypt(this.suppliersEncrypted, String[].class);
    }

    private void decodePartner(){
        this.search = ParamOperation.unencrypt(this.searchEncrypted, SearchRequest.class);
    }
}
