package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public class PostLandDetailsObject {

    final String sub_category;
    final String date;
    final String pick_up_time;
    final String return_time;
    final String location;
    final String passengers;
    final String vehicle_type;
    final String car_brand;
    final String notes;

    public PostLandDetailsObject(String sub_category, String date, String pick_up_time, String return_time, String location,
                                 String passengers, String vehicle_type, String car_brand, String notes){

        this.sub_category = sub_category;
        this.date = date;
        this.pick_up_time = pick_up_time;
        this.return_time = return_time;
        this.location = location;
        this.passengers = passengers;
        this.vehicle_type = vehicle_type;
        this.car_brand = car_brand;
        this.notes = notes;

    }

}
