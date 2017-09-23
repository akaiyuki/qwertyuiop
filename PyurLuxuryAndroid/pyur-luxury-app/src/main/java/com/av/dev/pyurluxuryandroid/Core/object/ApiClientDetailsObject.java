package com.av.dev.pyurluxuryandroid.Core.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 23/09/2017.
 */

public class ApiClientDetailsObject {
    @SerializedName("_id")
    private String id;
    @SerializedName("life_style_client_firstname")
    private String clientFirstName;
    @SerializedName("life_style_client_lastname")
    private String clientLastName;
    @SerializedName("life_style_client_mobile")
    private String clientMobile;
    @SerializedName("life_style_client_photo")
    private String clientPhoto;
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

    public String getClientPhoto() {

        return clientPhoto;
    }

    public void setClientPhoto(String clientPhoto) {
        this.clientPhoto = clientPhoto;
    }

    public String getClientMobile() {

        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    public String getClientLastName() {

        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
