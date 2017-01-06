package com.example.root.projectfsoft.service.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 03/01/2017.
 */

public class Vitri {
    @SerializedName("lat")
    String lat;
    @SerializedName("lng")
    String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
