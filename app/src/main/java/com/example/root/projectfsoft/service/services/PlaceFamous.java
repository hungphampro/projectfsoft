package com.example.root.projectfsoft.service.services;

import com.example.root.projectfsoft.service.response.APIResponse;
import com.example.root.projectfsoft.service.response.Place;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 21/12/2016.
 */

public interface PlaceFamous {
    @GET("textsearch/json")
    Call<APIResponse<ArrayList<Place>>> getPlaceFamous(@Query("query") String text,@Query("key") String apikey);

}
