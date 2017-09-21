package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostBespokeBookObject {
    final String service_category;
    final PostBespokeDetailsObject service_details;

    public PostBespokeBookObject(String service_category, PostBespokeDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }
}
