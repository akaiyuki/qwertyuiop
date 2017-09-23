package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by CodeSyaona on 23/09/2017.
 */

public class ApiResponseClientRequestObject {
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private ArrayList<ApiClientDetailsObject> data;
    @SerializedName("msg")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<ApiClientDetailsObject> getData() {

        return data;
    }

    public void setData(ArrayList<ApiClientDetailsObject> data) {
        this.data = data;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
