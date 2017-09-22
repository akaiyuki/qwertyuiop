package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public class ApiBespokeDetailsObject {
    @SerializedName("request")
    private String request;
    @SerializedName("isUrgent")
    private String isUrgent;

    public String getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(String isUrgent) {
        this.isUrgent = isUrgent;
    }

    public String getRequest() {

        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
