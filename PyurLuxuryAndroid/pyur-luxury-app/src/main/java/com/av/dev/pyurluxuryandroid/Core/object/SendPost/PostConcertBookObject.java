package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostConcertBookObject {

    final String service_category;
    final PostConcertDetailsObject service_details;

    public PostConcertBookObject(String service_category, PostConcertDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }

}
