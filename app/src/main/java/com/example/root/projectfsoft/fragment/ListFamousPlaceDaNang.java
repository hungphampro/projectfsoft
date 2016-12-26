package com.example.root.projectfsoft.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.root.projectfsoft.ActivityDetailImage;
import com.example.root.projectfsoft.adapter.DivideRecycleview;
import com.example.root.projectfsoft.adapter.ItemListAapter;
import com.example.root.projectfsoft.adapter.RecycleViewClicker;
import com.example.root.projectfsoft.adapter.RecycleViewItemAdapterGril;
import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.service.response.Place;
import com.example.root.projectfsoft.service.services.PlaceFamousImpl;
import com.example.root.projectfsoft.service.util.CallBack;

import java.util.ArrayList;

/**
 * Created by root on 21/12/2016.
 */

public class ListFamousPlaceDaNang extends Fragment {
    ArrayList<Place> mPlaces;
    ItemListAapter itemListAapter;
    PlaceFamousImpl placeFamous;
    LayoutInflater mInflater;
    boolean flag=true;
    RecyclerView rc;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater=inflater;
        View view=inflater.inflate(R.layout.list_fragment,container,false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView mImg= (ImageView) toolbar.findViewById(R.id.style);
        rc= (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager ln=new LinearLayoutManager(mInflater.getContext(),LinearLayoutManager.VERTICAL,false);
        rc.setLayoutManager(ln);
        mPlaces=new ArrayList<>();
        placeFamous=new PlaceFamousImpl(inflater.getContext());
        placeFamous.getPlaceFamous(new CallBack<ArrayList<Place>>() {
            @Override
            public void next(ArrayList<Place> data) {
                mPlaces=data;
                itemListAapter=new ItemListAapter(mPlaces,inflater.getContext());
                rc.setAdapter(itemListAapter);
            }
        });
        rc.setItemAnimator(new DefaultItemAnimator());
        rc.addItemDecoration(new DivideRecycleview(inflater.getContext()));
        rc.addOnItemTouchListener(new RecycleViewClicker(mInflater.getContext(), rc, new RecycleViewClicker.click() {
            @Override
            public void onClick(View view, int position) {
               /* Intent i=new Intent(mInflater.getContext(), ActivityDetailImage.class);
                i.putExtra("from",2);
                i.putExtra("id",mPlaces.get(position).getId());
                startActivity(i);*/
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doitrangthai();
            }
        });

        return view;
    }
    public void doitrangthai(){
        if(!flag){
            LinearLayoutManager ln=new LinearLayoutManager(mInflater.getContext(),LinearLayoutManager.VERTICAL,false);
            rc.setLayoutManager(ln);
            itemListAapter=new ItemListAapter(mPlaces,mInflater.getContext());
            rc.setAdapter(itemListAapter);
            flag=true;
        }else{
            GridLayoutManager gr=new GridLayoutManager(mInflater.getContext(),2);
            rc.setLayoutManager(gr);
            RecycleViewItemAdapterGril itemGrilAapter=new RecycleViewItemAdapterGril(mPlaces,mInflater.getContext());
            rc.setAdapter(itemGrilAapter);
            flag=false;
        }
    }
}
