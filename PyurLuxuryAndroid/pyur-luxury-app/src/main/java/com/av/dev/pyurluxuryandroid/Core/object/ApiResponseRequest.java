package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public class ApiResponseRequest {
    @SerializedName("_id")
    private String id;
    @SerializedName("life_style_manager_firstname")
    private String lifestyleFirstname;
    @SerializedName("life_style_manager_lastname")
    private String lifestyleLastname;
    @SerializedName("life_style_manager_mobile")
    private String lifeStyleMobile;
    @SerializedName("life_style_manager_photo")
    private String lifestylePhoto;
    @SerializedName("service_category")
    private String serviceCategory;
    @SerializedName("date_added")
    private String dateAdded;
    @SerializedName("request_status")
    private String requestStatus;
    @SerializedName("is_done")
    private String isDone;


    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    public String getRequestStatus() {

        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getDateAdded() {

        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getServiceCategory() {

        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getLifestylePhoto() {

        return lifestylePhoto;
    }

    public void setLifestylePhoto(String lifestylePhoto) {
        this.lifestylePhoto = lifestylePhoto;
    }

    public String getLifeStyleMobile() {

        return lifeStyleMobile;
    }

    public void setLifeStyleMobile(String lifeStyleMobile) {
        this.lifeStyleMobile = lifeStyleMobile;
    }

    public String getLifestyleLastname() {

        return lifestyleLastname;
    }

    public void setLifestyleLastname(String lifestyleLastname) {
        this.lifestyleLastname = lifestyleLastname;
    }

    public String getLifestyleFirstname() {

        return lifestyleFirstname;
    }

    public void setLifestyleFirstname(String lifestyleFirstname) {
        this.lifestyleFirstname = lifestyleFirstname;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
