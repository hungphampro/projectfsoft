package com.example.root.projectfsoft.model;

import io.realm.RealmObject;

/**
 * Created by root on 23/12/2016.
 */

public class ReviewPlace extends RealmObject {


    public ReviewPlace(){

    }
    private String id;

    private String authorName;

    private byte[] imgPath;

    private String content;

    private float rating;

    private String timeComment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public byte[] getImgPath() {
        return imgPath;
    }

    public void setImgPath(byte[] imgPath) {
        this.imgPath = imgPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTimeComment() {
        return timeComment;
    }

    public void setTimeComment(String timeComment) {
        this.timeComment = timeComment;
    }

    public ReviewPlace(String id, String authorName, byte[] imgPath, String content, float rating, String timeComment) {
        this.id = id;
        this.authorName = authorName;
        this.imgPath = imgPath;
        this.content = content;
        this.rating = rating;
        this.timeComment = timeComment;
    }
}
