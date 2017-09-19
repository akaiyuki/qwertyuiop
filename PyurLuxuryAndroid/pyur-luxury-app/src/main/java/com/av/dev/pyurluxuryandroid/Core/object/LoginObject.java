package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class LoginObject {

    @SerializedName("user_details")
    private LoginDetailObject data;

    @SerializedName("token")
    private String userToken;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public LoginDetailObject getData() {
        return data;
    }

    public void setData(LoginDetailObject data) {
        this.data = data;
    }
}
