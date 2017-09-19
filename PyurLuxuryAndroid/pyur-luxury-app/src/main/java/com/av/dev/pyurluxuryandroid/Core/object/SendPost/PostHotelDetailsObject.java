package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

/**
 * Created by CodeSyaona on 19/09/2017.
 */

public class PostHotelDetailsObject {

    final String city;
    final String hotelName;
    final String check_in_date;
    final String check_out_date;
    final String room_type;
    final String number_of_rooms;
    final String number_of_pax;
    final String notes;


    public PostHotelDetailsObject(String city, String hotelName, String check_in_date, String check_out_date,
                                  String room_type, String number_of_rooms, String number_of_pax, String notes) {
        this.city = city;
        this.hotelName = hotelName;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.room_type = room_type;
        this.number_of_rooms = number_of_rooms;
        this.number_of_pax = number_of_pax;
        this.notes = notes;
    }
}
