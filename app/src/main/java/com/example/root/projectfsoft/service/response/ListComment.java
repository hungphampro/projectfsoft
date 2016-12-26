package com.example.root.projectfsoft.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 24/12/2016.
 */

public class ListComment  {
    @SerializedName("reviews")
    ArrayList<Reviews> image;

    public ArrayList<Reviews> getImage() {
        return image;
    }

    public void setImage(ArrayList<Reviews> image) {
        this.image = image;
    }
}
