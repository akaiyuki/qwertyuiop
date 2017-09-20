package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostPlayBookObject {

    final String service_category;
    final PostPlayDetailsObject service_details;

    public PostPlayBookObject(String service_category, PostPlayDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }

}
