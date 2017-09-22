package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public class ApiServiceDetailsObject {

    @SerializedName("room_type")
    private String roomType;
    @SerializedName("number_of_rooms")
    private String numRooms;
    @SerializedName("number_of_pax")
    private String pax;
    @SerializedName("notes")
    private String notes;
    @SerializedName("hotelName")
    private String hotelName;
    @SerializedName("city")
    private String city;
    @SerializedName("check_out_date")
    private String checkOut;
    @SerializedName("check_in_date")
    private String checkIn;

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {

        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotelName() {

        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getNotes() {

        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPax() {

        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getNumRooms() {

        return numRooms;
    }

    public void setNumRooms(String numRooms) {
        this.numRooms = numRooms;
    }

    public String getRoomType() {

        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
