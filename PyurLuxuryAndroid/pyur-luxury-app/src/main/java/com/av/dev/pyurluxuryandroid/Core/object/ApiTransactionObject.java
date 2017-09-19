package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 18/09/2017.
 */

public class ApiTransactionObject {

    @SerializedName("city")
    private String city;
    @SerializedName("hotel Name")
    private String hotelName;
    @SerializedName("check_in_date")
    private String checkinDate;
    @SerializedName("check_out_date")
    private String checkouDate;
    @SerializedName("room_type")
    private String roomType;
    @SerializedName("number_of_rooms")
    private String numberRooms;
    @SerializedName("number_of_pax")
    private String pax;
    @SerializedName("notes")
    private String notes;

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

    public String getNumberRooms() {

        return numberRooms;
    }

    public void setNumberRooms(String numberRooms) {
        this.numberRooms = numberRooms;
    }

    public String getRoomType() {

        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCheckouDate() {

        return checkouDate;
    }

    public void setCheckouDate(String checkouDate) {
        this.checkouDate = checkouDate;
    }

    public String getCheckinDate() {

        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getHotelName() {

        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
