package com.example.root.projectfsoft.service.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/12/2016.
 */

public class Location {
    @SerializedName("lat")
    double lat;
    @SerializedName("lng")
    double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
