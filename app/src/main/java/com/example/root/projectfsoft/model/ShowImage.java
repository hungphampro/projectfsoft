package com.example.root.projectfsoft.model;

import io.realm.RealmObject;

/**
 * Created by root on 23/12/2016.
 */

public class ShowImage extends RealmObject {

    public ShowImage(){
    }
    private String idPlace;

    private byte[] detailImage;

    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }

    public byte[] getDetailImage() {
        return detailImage;
    }

    public void setDetailImage(byte[] detailImage) {
        this.detailImage = detailImage;
    }

    public ShowImage(String idPlace, byte[] detailImage) {
        this.idPlace = idPlace;
        this.detailImage = detailImage;
    }
}
