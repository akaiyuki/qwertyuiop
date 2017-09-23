package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public class ApiRestaurantObject {
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private ApiResCategoryObject data;
    @SerializedName("msg")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResCategoryObject getData() {

        return data;
    }

    public void setData(ApiResCategoryObject data) {
        this.data = data;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
