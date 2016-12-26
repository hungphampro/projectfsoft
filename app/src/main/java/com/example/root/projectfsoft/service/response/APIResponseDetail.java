package com.example.root.projectfsoft.service.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 24/12/2016.
 */

public class APIResponseDetail<T> {
@SerializedName("result")
T dsPlace;
@SerializedName("status")
String status;

public T getDsPlace() {
        return dsPlace;
        }

public void setDsPlace(T dsPlace) {
        this.dsPlace = dsPlace;
        }

public String getStatus() {
        return status;
        }

public void setStatus(String status) {
        this.status = status;
        }
        }

