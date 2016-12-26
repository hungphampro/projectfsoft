package com.example.root.projectfsoft.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 21/12/2016.
 */

public class PlaceDeatail {

    @SerializedName("photos")
    ArrayList<ShowImage> image;

    public ArrayList<ShowImage> getImage() {
        return image;
    }

    public void setImage(ArrayList<ShowImage> image) {
        this.image = image;
    }
}
