package com.example.root.projectfsoft.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 23/12/2016.
 */

public class PlaceFavorite extends RealmObject{

    @PrimaryKey
    private String id;

    private String address;
    private String name;
    private byte[] imgPath;
    private float rating;

    public PlaceFavorite(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImgPath() {
        return imgPath;
    }

    public void setImgPath(byte[] imgPath) {
        this.imgPath = imgPath;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public PlaceFavorite(String id, String address, String name, byte[] imgPath, float rating) {
        this.id=id;
        this.address = address;
        this.name = name;
        this.imgPath = imgPath;
        this.rating = rating;
    }
}
