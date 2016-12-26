package com.example.root.projectfsoft.service.services;

import android.content.Context;

import com.example.root.projectfsoft.service.response.APIResponseDetail;
import com.example.root.projectfsoft.service.response.ShowImage;
import com.example.root.projectfsoft.service.Configuration;
import com.example.root.projectfsoft.service.response.APIResponse;
import com.example.root.projectfsoft.service.response.PlaceDeatail;
import com.example.root.projectfsoft.service.util.CallBack;
import com.example.root.projectfsoft.service.util.Contants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by root on 24/12/2016.
 */

public class ImagePlaceImpl {
    private Context mContext;
    public ImagePlaceImpl(Context context){
        mContext=context;
    }
    public void getImagePlcae(String placeID,final CallBack<ArrayList<ShowImage>> trave){
        ImagePlace moi= Configuration.getClient().create(ImagePlace.class);
        Call<APIResponseDetail<PlaceDeatail>> chido =moi.getImagePlace(placeID, Contants.key);
        chido.enqueue(new Callback<APIResponseDetail<PlaceDeatail>>() {
            @Override
            public void onResponse(Call<APIResponseDetail<PlaceDeatail>> call, Response<APIResponseDetail<PlaceDeatail>> response) {
                System.out.println("toi muon biet:"+response.body().getStatus());
                ArrayList<ShowImage> nua=response.body().getDsPlace().getImage();
                trave.next(nua);
            }

            @Override
            public void onFailure(Call<APIResponseDetail<PlaceDeatail>> call, Throwable t) {

            }
        });
    }
}
