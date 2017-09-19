package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class PostCinemaDetailsObject {

    final String movie;
    final String date;
    final String time;
    final String numPax;
    final String notes;

    public PostCinemaDetailsObject(String movie, String date, String time, String numPax, String notes){
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.numPax = numPax;
        this.notes = notes;
    }
}
