package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostAirDetailsObject {

    final String sub_category;
    final String origin;
    final String destination;
    final String vehicle;
    final String departure;
    final String return_date;
    final String pickup_time;
    final String return_time;
    final String pax;
    final String notes;

    public PostAirDetailsObject(String sub_category, String origin, String destination, String vehicle,
                                String departure, String return_date, String pickup_time,
                                String return_time, String pax, String notes){

        this.sub_category = sub_category;
        this.origin = origin;
        this.destination = destination;
        this.vehicle = vehicle;
        this.departure = departure;
        this.return_date = return_date;
        this.pickup_time = pickup_time;
        this.return_time = return_time;
        this.pax = pax;
        this.notes = notes;

    }

}
