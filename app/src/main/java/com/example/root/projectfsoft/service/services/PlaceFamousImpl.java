package com.example.root.projectfsoft.service.services;

import android.content.Context;

import com.example.root.projectfsoft.service.Configuration;
import com.example.root.projectfsoft.service.response.APIResponse;
import com.example.root.projectfsoft.service.response.Place;
import com.example.root.projectfsoft.service.util.CallBack;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 21/12/2016.
 */

public class PlaceFamousImpl {
    private Context mContext;
    private static String apikey="AIzaSyD-Di7PSbAJ1gVFEVgpo2jyRxWNpm9-BjU";
    String text="địa+điểm+du+lịch+nổi+tiếng+đà+nẵng";
    public PlaceFamousImpl(Context context){
        mContext=context;
    }
    public void getPlaceFamous(final CallBack<ArrayList<Place>> data){
        PlaceFamous placeFamous= Configuration.getClient().create(PlaceFamous.class);
        Call<APIResponse<ArrayList<Place>>> call= placeFamous.getPlaceFamous(text,apikey);
        call.enqueue(new Callback<APIResponse<ArrayList<Place>>>() {
            @Override
            public void onResponse(Call<APIResponse<ArrayList<Place>>> call, Response<APIResponse<ArrayList<Place>>> response) {
               data.next(response.body().getDsPlace());
            }

            @Override
            public void onFailure(Call<APIResponse<ArrayList<Place>>> call, Throwable t) {

            }
        });
    }
}
