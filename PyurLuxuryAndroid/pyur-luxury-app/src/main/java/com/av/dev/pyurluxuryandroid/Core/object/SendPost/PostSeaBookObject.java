package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostSeaBookObject {

    final String service_category;
    final PostSeaDetailsObject service_details;

    public PostSeaBookObject(String service_category, PostSeaDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }
}
