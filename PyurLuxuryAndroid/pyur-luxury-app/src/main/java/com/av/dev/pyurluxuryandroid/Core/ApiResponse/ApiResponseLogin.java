package com.av.dev.pyurluxuryandroid.Core.ApiResponse;

import com.av.dev.pyurluxuryandroid.Core.api.ApiObject;
import com.av.dev.pyurluxuryandroid.Core.object.LoginObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class ApiResponseLogin {

    @SerializedName("code")
    private String status;


    @SerializedName("data")
    private LoginObject data;

    @SerializedName("msg")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public LoginObject getData() {
        return data;
    }

}
