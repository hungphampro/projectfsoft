package com.example.root.projectfsoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.root.projectfsoft.adapter.DivideRecycleview;
import com.example.root.projectfsoft.adapter.ItemListAapter;
import com.example.root.projectfsoft.adapter.ItemListChoiceAdapter;
import com.example.root.projectfsoft.service.response.Place;
import com.example.root.projectfsoft.service.services.PlaceFamousImpl;
import com.example.root.projectfsoft.service.util.CallBack;

import java.util.ArrayList;

/**
 * Created by root on 27/12/2016.
 */

public class DialogChoicePlace extends AppCompatActivity implements ItemListChoiceAdapter.click{
    RecyclerView rcGoiY,rcChoice;
    ArrayList<Place> mPlaces;
    ItemListAapter itemListAapter;
    PlaceFamousImpl placeFamous;
    String chuoitim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choice_place);
        Intent i=getIntent();
        chuoitim=i.getStringExtra("chuoitim");
        rcGoiY= (RecyclerView) findViewById(R.id.dsgoiy);
        LinearLayoutManager ln=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcGoiY.setLayoutManager(ln);
        mPlaces=new ArrayList<>();
        placeFamous=new PlaceFamousImpl(this);

        placeFamous.getPlaceFamous(chuoitim,new CallBack<ArrayList<Place>>() {
            @Override
            public void next(ArrayList<Place> data) {
                mPlaces=data;
                itemListAapter=new ItemListAapter(mPlaces,getApplication());
                rcGoiY.setAdapter(itemListAapter);
            }
        });
        rcGoiY.setItemAnimator(new DefaultItemAnimator());
        rcGoiY.addItemDecoration(new DivideRecycleview(this));

    }

    @Override
    public void onClick(View view, int position) {
        switch (view.getId()){

        }
    }

    @Override
    public void onLongClick(View view,int position) {

    }
}
