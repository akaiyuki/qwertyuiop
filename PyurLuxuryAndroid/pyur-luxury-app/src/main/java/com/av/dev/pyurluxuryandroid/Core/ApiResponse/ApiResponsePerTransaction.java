package com.av.dev.pyurluxuryandroid.Core.ApiResponse;

import com.av.dev.pyurluxuryandroid.Core.object.ApiPerTransactionObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public class ApiResponsePerTransaction {

    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private ApiPerTransactionObject data;
    @SerializedName("msg")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiPerTransactionObject getData() {

        return data;
    }

    public void setData(ApiPerTransactionObject data) {
        this.data = data;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
