package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostEventBookObject {

    final String service_category;
    final PostEventDetailsObject service_details;

    public PostEventBookObject(String service_category, PostEventDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }

}
