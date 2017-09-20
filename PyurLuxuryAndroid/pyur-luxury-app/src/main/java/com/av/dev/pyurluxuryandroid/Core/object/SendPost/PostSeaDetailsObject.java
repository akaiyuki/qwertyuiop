package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostSeaDetailsObject {

    final String sub_category;
    final String cruise;
    final String origin;
    final String destination;
    final String dep_date;
    final String return_date;
    final String pick_up;
    final String return_time;
    final String passengers;
    final String notes;

    public PostSeaDetailsObject(String sub_category,String cruise, String origin, String destination, String dep_date,
                                String return_date, String pick_up, String return_time, String passengers,
                                String notes){
        this.sub_category = sub_category;
        this.cruise = cruise;
        this.origin = origin;
        this.destination = destination;
        this.dep_date = dep_date;
        this.return_date = return_date;
        this.pick_up = pick_up;
        this.return_time = return_time;
        this.passengers = passengers;
        this.notes = notes;
    }

}
