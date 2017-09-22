package com.av.dev.pyurluxuryandroid.Core.ApiResponse;

import com.av.dev.pyurluxuryandroid.Core.object.ApiResponseRequest;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public class ApiResponseTransaction {

    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private ArrayList<ApiResponseRequest> data;
    @SerializedName("code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<ApiResponseRequest> getData() {

        return data;
    }

    public void setData(ArrayList<ApiResponseRequest> data) {
        this.data = data;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
