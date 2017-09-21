package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 21/09/2017.
 */

public class PostFlightDetailsObject {

    final String origin;
    final String destination;
    final String flight_trip;
    final String dep_date;
    final String return_date;
    final String class_type;
    final String pax;
    final String airline;
    final String notes;

    public PostFlightDetailsObject(String origin, String destination, String flight_trip, String dep_date,
                                   String return_date, String class_type, String pax, String airline,
                                   String notes){
        this.origin = origin;
        this.destination = destination;
        this.flight_trip = flight_trip;
        this.dep_date = dep_date;
        this.return_date = return_date;
        this.class_type = class_type;
        this.pax = pax;
        this.airline = airline;
        this.notes = notes;
    }

}
