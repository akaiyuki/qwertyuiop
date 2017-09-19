package com.av.dev.pyurluxuryandroid.Core.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 18/09/2017.
 */

public class ApiObject {

    @SerializedName("token")
    private String token;
    @SerializedName("user_info")
    private String data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getData() {
        return data;
    }

}
