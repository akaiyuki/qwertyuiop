package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class PostCinemaDetailsObject {

    final String sub_category;
    final String movie;
    final String date;
    final String time;
    final String num_pax;
    final String notes;

    public PostCinemaDetailsObject(String sub_category,String movie, String date, String time, String num_pax, String notes){
        this.sub_category = sub_category;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.num_pax = num_pax;
        this.notes = notes;
    }
}
