package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class PostCinemaBookObject {
    final String service_category;
    final PostCinemaDetailsObject service_details;

    public PostCinemaBookObject(String service_category, PostCinemaDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }
}
