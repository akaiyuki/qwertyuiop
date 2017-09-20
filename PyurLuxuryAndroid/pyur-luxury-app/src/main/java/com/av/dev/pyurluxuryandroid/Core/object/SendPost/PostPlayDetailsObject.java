package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostPlayDetailsObject {

    final String sub_category;
    final String play;
    final String date;
    final String time;
    final String pax;
    final String notes;

    public PostPlayDetailsObject(String sub_category, String play, String date, String time,
                                 String pax, String notes){
        this.sub_category = sub_category;
        this.play = play;
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.notes = notes;
    }

}
