package com.av.dev.pyurluxuryandroid.Core.object.SendPost;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 25/09/2017.
 */

public class PostRestaurantDetailsObject {
    @SerializedName("time")
    private String time;
    @SerializedName("res_name")
    private String resName;
    @SerializedName("numPax")
    private String numPax;
    @SerializedName("notes")
    private String notes;
    @SerializedName("date")
    private String date;
    @SerializedName("city")
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {

        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNumPax() {

        return numPax;
    }

    public void setNumPax(String numPax) {
        this.numPax = numPax;
    }

    public String getResName() {

        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
