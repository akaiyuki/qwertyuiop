package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public class ApiBeSpokeObject {
    @SerializedName("manager")
    private ApiManagerObject manager;
    @SerializedName("service_category")
    private String serviceCategory;
    @SerializedName("service_details")
    private ApiBespokeDetailsObject serviceDetails;

    public ApiBespokeDetailsObject getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(ApiBespokeDetailsObject serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public String getServiceCategory() {

        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public ApiManagerObject getManager() {

        return manager;
    }

    public void setManager(ApiManagerObject manager) {
        this.manager = manager;
    }
}
