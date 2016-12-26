package com.example.root.projectfsoft.service.services;

import android.content.Context;

import com.example.root.projectfsoft.service.Configuration;
import com.example.root.projectfsoft.service.response.APIResponse;
import com.example.root.projectfsoft.service.response.APIResponseDetail;
import com.example.root.projectfsoft.service.response.ListComment;
import com.example.root.projectfsoft.service.response.Reviews;
import com.example.root.projectfsoft.service.util.CallBack;
import com.example.root.projectfsoft.service.util.Contants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 24/12/2016.
 */

public class ReviewPlacesImpl {
    private Context mContext;
    public ReviewPlacesImpl(Context context){
        mContext=context;
    }
    public void getReviewPlace(String PlaceID,final CallBack<ListComment> moi){
        Review rvp= Configuration.getClient().create(Review.class);
        Call<APIResponseDetail<ListComment>> chido=rvp.getReviewsPlace(PlaceID, Contants.key);
        chido.enqueue(new Callback<APIResponseDetail<ListComment>>() {
            @Override
            public void onResponse(Call<APIResponseDetail<ListComment>> call, Response<APIResponseDetail<ListComment>> response) {
                ListComment tiep=response.body().getDsPlace();
                moi.next(tiep);
            }

            @Override
            public void onFailure(Call<APIResponseDetail<ListComment>> call, Throwable t) {

            }
        });
    }

}
