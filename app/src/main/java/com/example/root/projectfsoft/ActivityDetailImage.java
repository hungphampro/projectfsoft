package com.example.root.projectfsoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.root.projectfsoft.adapter.ItemComment;
import com.example.root.projectfsoft.adapter.ItemImagePlace;
import com.example.root.projectfsoft.dataBase.RealmController;
import com.example.root.projectfsoft.model.ReviewPlace;
import com.example.root.projectfsoft.model.ShowImage;
import com.example.root.projectfsoft.service.response.ListComment;
import com.example.root.projectfsoft.service.response.Reviews;
import com.example.root.projectfsoft.service.services.ImagePlaceImpl;
import com.example.root.projectfsoft.service.services.Review;
import com.example.root.projectfsoft.service.services.ReviewPlacesImpl;
import com.example.root.projectfsoft.service.util.CallBack;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by root on 24/12/2016.
 */

public class ActivityDetailImage extends AppCompatActivity {

    private RecyclerView rcImage;

    private RecyclerView rcComent;

    private static int t;

    private static String id;

    private Realm realm;

    private ArrayList<com.example.root.projectfsoft.service.response.ShowImage> mangInternet;

    private ArrayList<Reviews> moi;

    private ItemImagePlace itemImagePlace;

    private ItemComment diep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_place);
        realm= RealmController.with(this).getRealm();
        rcImage= (RecyclerView) findViewById(R.id.image);
        LinearLayoutManager ln=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcImage.setLayoutManager(ln);
        rcImage.setNestedScrollingEnabled(false);
        rcImage.setItemAnimator(new DefaultItemAnimator());
        rcComent= (RecyclerView) findViewById(R.id.comment);
        LinearLayoutManager li=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcComent.setLayoutManager(li);
        rcComent.setNestedScrollingEnabled(false);
        rcComent.setItemAnimator(new DefaultItemAnimator());
        Intent i=getIntent();
        t=i.getIntExtra("from",0);
        id=i.getStringExtra("id");
        System.out.println("Gia tri cua id:"+id);
        if(t==1)
        {
            ArrayList<ShowImage> moi=new ArrayList<>(realm.where(ShowImage.class).equalTo("idPlace",id).findAll());
            ItemImagePlace thedo=new ItemImagePlace(this,moi,t);
            rcImage.setAdapter(thedo);
            ArrayList<ReviewPlace> review=new ArrayList<>(realm.where(ReviewPlace.class).equalTo("id",id).findAll());
            ItemComment nua=new ItemComment(this,review,t);
            rcComent.setAdapter(nua);
        }else{

            ImagePlaceImpl impl=new ImagePlaceImpl(this);
            impl.getImagePlcae(id, new CallBack<ArrayList<com.example.root.projectfsoft.service.response.ShowImage>>() {
                @Override
                public void next(ArrayList<com.example.root.projectfsoft.service.response.ShowImage> data) {
                    mangInternet=data;
                    itemImagePlace=new ItemImagePlace(mangInternet,getApplication(),t);
                    rcImage.setAdapter(itemImagePlace);
                }
            });
            ReviewPlacesImpl rv=new ReviewPlacesImpl(this);
            rv.getReviewPlace(id, new CallBack<ListComment>() {
                @Override
                public void next(ListComment data) {
                    moi=data.getImage();
                    diep=new ItemComment(moi,getApplication(),t);
                    rcComent.setAdapter(diep);
                }
            });
        }

    }
}
