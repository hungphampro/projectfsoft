package com.example.root.projectfsoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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

public class DialogChoicePlace extends Activity {
    RecyclerView rcGoiY,rcChoice;
    Button trave;
    ArrayList<Place> mPlaces,mPlaceChoice;
    ItemListChoiceAdapter itemListAapter;
    ItemListChoiceAdapter itemListAapterChoice;
    PlaceFamousImpl placeFamous;
    String chuoitim;
    Intent i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choice_place);
        trave= (Button) findViewById(R.id.trave);
        i=getIntent();
        chuoitim=i.getStringExtra("chuoitim");
        rcGoiY= (RecyclerView) findViewById(R.id.dsgoiy);
        LinearLayoutManager ln=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcGoiY.setLayoutManager(ln);
        mPlaces=new ArrayList<>();
        mPlaceChoice=new ArrayList<>();
        placeFamous=new PlaceFamousImpl(this);
        placeFamous.getPlaceFamous(chuoitim,new CallBack<ArrayList<Place>>() {
            @Override
            public void next(ArrayList<Place> data) {
                mPlaces=data;
                itemListAapter=new ItemListChoiceAdapter(mPlaces,mPlaceChoice,DialogChoicePlace.this,1);
                rcGoiY.setAdapter(itemListAapter);
            }
        });
        rcGoiY.setItemAnimator(new DefaultItemAnimator());
        rcChoice=(RecyclerView) findViewById(R.id.dsdiadiemchon);
        LinearLayoutManager ln1=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcChoice.setLayoutManager(ln1);
        itemListAapterChoice=new ItemListChoiceAdapter(mPlaces,mPlaceChoice,DialogChoicePlace.this,2);
        rcChoice.setAdapter(itemListAapterChoice);
        rcChoice.setItemAnimator(new DefaultItemAnimator());
        trave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> mangs=new ArrayList<>();
                for(int i=0;i<mPlaceChoice.size();i++){
                    mangs.add(mPlaceChoice.get(i).getId());
                }
                Intent k=new Intent(getApplicationContext(),DuongDiActivity.class);
                k.putStringArrayListExtra("mang",mangs);
                startActivity(k);
                finish();
            }
        });

    }
    public ItemListChoiceAdapter getItemListAapter(){
        return itemListAapter;
    }
    public ItemListChoiceAdapter getItemListAapterChoice(){
        return itemListAapterChoice;
    }
}
