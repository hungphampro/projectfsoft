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
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.projectfsoft.ActivityDetailImage;
import com.example.root.projectfsoft.adapter.DivideRecycleview;
import com.example.root.projectfsoft.adapter.ItemListAapter;
import com.example.root.projectfsoft.adapter.RecycleViewClicker;
import com.example.root.projectfsoft.adapter.RecycleViewItemAdapterGril;
import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.dataBase.RealmController;
import com.example.root.projectfsoft.model.Setting;
import com.example.root.projectfsoft.service.response.Place;
import com.example.root.projectfsoft.service.services.PlaceFamousImpl;
import com.example.root.projectfsoft.service.util.CallBack;
import com.example.root.projectfsoft.service.util.ToolsActivity;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by root on 21/12/2016.
 */

public class ListFamousPlaceDaNang extends Fragment {
    ArrayList<Place> mPlaces;
    ItemListAapter itemListAapter;
    PlaceFamousImpl placeFamous;
    LayoutInflater mInflater;
    String text="địa+điểm+du+lịch+nổi+tiếng+đà+nẵng";
    double rate;
    boolean sort;
    boolean flag=true;
    Realm realm;
    ImageView mImg;
    RecyclerView rc;
    private ToolsActivity toolsActivity = new ToolsActivity();
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater=inflater;
        View view=inflater.inflate(R.layout.list_fragment,container,false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mImg= (ImageView) toolbar.findViewById(R.id.style);
        mImg.setVisibility(View.VISIBLE);
        TextView title= (TextView) toolbar.findViewById(R.id.title);
        title.setText("Famous Place");
        rc= (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager ln=new LinearLayoutManager(mInflater.getContext(),LinearLayoutManager.VERTICAL,false);
        rc.setLayoutManager(ln);
        realm= RealmController.with(this).getRealm();
        Setting set=realm.where(Setting.class).findFirst();
        if(set!=null){
           text=set.getCategoryName();
           rate=set.getRating();
           sort=set.isSortByRating();
        }
        if(!ToolsActivity.isNetworkOnline(getActivity())){
            Toast.makeText(getActivity(),"Vui Lòng Kiểm tra Network",Toast.LENGTH_SHORT).show();
        }else {
            mPlaces = new ArrayList<>();
            toolsActivity.showLoading(getActivity());
            placeFamous = new PlaceFamousImpl(inflater.getContext());
            placeFamous.getPlaceFamous(text, new CallBack<ArrayList<Place>>() {
                @Override
                public void next(ArrayList<Place> data) {
                    toolsActivity.dismissLoading(getActivity());
                    mPlaces = data;
                    itemListAapter = new ItemListAapter(mPlaces, inflater.getContext());
                    rc.setAdapter(itemListAapter);
                }
            });
            rc.setItemAnimator(new DefaultItemAnimator());
        }
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
            mImg.setImageResource(R.drawable.grid);
            itemListAapter=new ItemListAapter(mPlaces,mInflater.getContext());
            rc.setAdapter(itemListAapter);
            flag=true;
        }else{
            GridLayoutManager gr=new GridLayoutManager(mInflater.getContext(),2);
            rc.setLayoutManager(gr);
            mImg.setImageResource(R.drawable.list);
            RecycleViewItemAdapterGril itemGrilAapter=new RecycleViewItemAdapterGril(mPlaces,mInflater.getContext());
            rc.setAdapter(itemGrilAapter);
            flag=false;
        }
    }
}
