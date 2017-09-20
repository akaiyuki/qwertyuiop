package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostAirBookObject {
    final String service_category;
    final PostAirDetailsObject service_details;

    public PostAirBookObject(String service_category, PostAirDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }
}
