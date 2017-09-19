package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class ApiUserLoginObject {
    final String username;
    final String password;

    public ApiUserLoginObject(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
