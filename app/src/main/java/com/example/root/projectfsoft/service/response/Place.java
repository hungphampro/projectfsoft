package com.example.root.projectfsoft.service.response;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/12/2016.
 */

public class Place {
     @SerializedName("formatted_address")
     String address;

     @SerializedName("icon")
     String urlHinh;

     @SerializedName("name")
     String name;

     @SerializedName("rating")
     float rating;

     @SerializedName("geometry")
     Location location;

     @SerializedName("place_id")
     String id;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrlHinh() {
        return urlHinh;
    }

    public void setUrlHinh(String urlHinh) {
        this.urlHinh = urlHinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
