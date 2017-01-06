package com.example.root.projectfsoft.service.response;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/12/2016.
 */

public class Location {

    @SerializedName("location")
    Vitri location;


    public  Vitri getLocation() {
        return location;
    }

    public void setLocation(Vitri location) {
        this.location = location;
    }


}
