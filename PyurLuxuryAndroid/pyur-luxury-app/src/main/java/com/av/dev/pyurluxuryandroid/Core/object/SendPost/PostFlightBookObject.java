package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 21/09/2017.
 */

public class PostFlightBookObject {

    final String service_category;
    final PostFlightDetailsObject service_details;

    public PostFlightBookObject(String service_category, PostFlightDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }


}
