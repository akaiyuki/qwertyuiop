package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class PostResDetailsObject {

    final String city;
    final String res_name;
    final String date;
    final String time;
    final String numPax;
    final String notes;

    public PostResDetailsObject(String city, String res_name, String date, String time, String numPax, String notes) {
        this.city = city;
        this.res_name = res_name;
        this.date = date;
        this.time = time;
        this.numPax = numPax;
        this.notes = notes;
    }

}
