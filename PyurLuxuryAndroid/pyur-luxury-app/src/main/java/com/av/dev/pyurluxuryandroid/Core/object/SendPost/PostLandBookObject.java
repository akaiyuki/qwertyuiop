package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostLandBookObject {

    final String service_category;
    final PostLandDetailsObject service_details;

    public PostLandBookObject(String service_category, PostLandDetailsObject service_details) {
        this.service_category = service_category;
        this.service_details = service_details;
    }

}
