package com.av.dev.pyurluxuryandroid.Core.object;

import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostRestaurantDetailsObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public class ApiResCategoryObject {

    @SerializedName("manager")
    private ApiManagerObject manager;
    @SerializedName("service_category")
    private String serviceCategory;
    @SerializedName("service_details")
    private PostRestaurantDetailsObject data;

    public ApiManagerObject getManager() {
        return manager;
    }

    public void setManager(ApiManagerObject manager) {
        this.manager = manager;
    }

    public PostRestaurantDetailsObject getData() {
        return data;
    }

    public void setData(PostRestaurantDetailsObject data) {
        this.data = data;
    }

    public String getServiceCategory() {

        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }


}
