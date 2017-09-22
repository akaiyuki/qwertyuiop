package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public class ApiDetailsBespokeObject {

    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private ApiBeSpokeObject data;
    @SerializedName("msg")
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiBeSpokeObject getData() {

        return data;
    }

    public void setData(ApiBeSpokeObject data) {
        this.data = data;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
