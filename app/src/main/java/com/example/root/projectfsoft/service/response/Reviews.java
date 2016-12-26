package com.example.root.projectfsoft.service.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 21/12/2016.
 */

public class Reviews {
    @SerializedName("author_name")
    String nameAuthor;

    @SerializedName("rating")
    float rating;

    @SerializedName("relative_time_description")
    String time;

    @SerializedName("text")
    String content;

    @SerializedName("profile_photo_url")
    String photo_url;

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
