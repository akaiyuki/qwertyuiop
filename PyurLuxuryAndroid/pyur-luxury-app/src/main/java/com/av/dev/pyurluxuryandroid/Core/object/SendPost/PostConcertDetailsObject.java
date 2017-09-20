package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostConcertDetailsObject {

    final String sub_category;
    final String concert_title;
    final String ticket_type;
    final String num_pax;
    final String notes;

    public PostConcertDetailsObject(String sub_category, String concert_title, String ticket_type, String num_pax,
                                    String notes){
        this.sub_category = sub_category;
        this.concert_title = concert_title;
        this.ticket_type = ticket_type;
        this.num_pax = num_pax;
        this.notes = notes;
    }

}
