package com.av.dev.pyurluxuryandroid.Core.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 18/09/2017.
 */

public class ApiResponse {

    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private ApiObject data;
    @SerializedName("code")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public ApiObject getData() {
        return data;
    }

}
