package com.example.root.projectfsoft.service.services;

import com.example.root.projectfsoft.service.response.APIResponse;
import com.example.root.projectfsoft.service.response.APIResponseDetail;
import com.example.root.projectfsoft.service.response.PlaceDeatail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 24/12/2016.
 */

public interface ImagePlace {
    @GET("details/json")
    Call<APIResponseDetail<PlaceDeatail>> getImagePlace(@Query("place_id") String placeID, @Query("key") String apikey);
}
