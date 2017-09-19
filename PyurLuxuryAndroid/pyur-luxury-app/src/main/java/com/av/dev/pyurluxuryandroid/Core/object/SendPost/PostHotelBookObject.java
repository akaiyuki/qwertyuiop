package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class PostHotelBookObject {
    final String service_category;
    final PostHotelDetailsObject service_details;

    public PostHotelBookObject(String service_category, PostHotelDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }

}
