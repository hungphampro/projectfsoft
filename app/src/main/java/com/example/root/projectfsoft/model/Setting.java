package com.example.root.projectfsoft.model;

import java.util.Comparator;

import io.realm.RealmObject;

/**
 * Created by root on 06/01/2017.
 */

public class Setting extends RealmObject {

    private String categoryName;

    private double rating;

    private boolean sortByRating;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isSortByRating() {
        return sortByRating;
    }

    public void setSortByRating(boolean sortByRating) {
        this.sortByRating = sortByRating;
    }

    public Setting(String categoryName, double rating, boolean sortByRating) {
        this.categoryName = categoryName;
        this.rating = rating;
        this.sortByRating = sortByRating;
    }
    public Setting(){

    }



}
