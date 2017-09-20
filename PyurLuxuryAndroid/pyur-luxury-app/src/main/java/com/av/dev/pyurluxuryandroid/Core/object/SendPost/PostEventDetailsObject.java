package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostEventDetailsObject {

    final String sub_category;
    final String event;
    final String ticket;
    final String pax;
    final String notes;

    public PostEventDetailsObject(String sub_category, String event, String ticket, String pax,
                                  String notes){
        this.sub_category = sub_category;
        this.event = event;
        this.ticket = ticket;
        this.pax = pax;
        this.notes = notes;
    }

}
