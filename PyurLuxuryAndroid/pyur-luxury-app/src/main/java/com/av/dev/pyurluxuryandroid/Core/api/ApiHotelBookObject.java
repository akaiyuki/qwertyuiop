package com.av.dev.pyurluxuryandroid.Core.api;

import com.av.dev.pyurluxuryandroid.Core.object.ApiTransactionObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 18/09/2017.
 */

public class ApiHotelBookObject {

    @SerializedName("service_category")
    private String serviceCategory;
    @SerializedName("service_details")
    private ApiTransactionObject details;

    public ApiTransactionObject getDetails() {
        return details;
    }

    public void setDetails(ApiTransactionObject details) {
        this.details = details;
    }

    public String getServiceCategory() {

        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }
}
