package com.example.root.projectfsoft.service.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/12/2016.
 */

public class ShowImage {
    @SerializedName("width")
    int width;

    @SerializedName("height")
    int height;

    @SerializedName("photo_reference")
    String  photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
