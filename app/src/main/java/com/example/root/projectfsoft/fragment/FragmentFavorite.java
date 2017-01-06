package com.example.root.projectfsoft.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.projectfsoft.ActivityDetailImage;
import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.adapter.ItemListAapter;
import com.example.root.projectfsoft.adapter.ItemListFavoritePlace;
import com.example.root.projectfsoft.adapter.RecycleViewClicker;
import com.example.root.projectfsoft.dataBase.RealmController;
import com.example.root.projectfsoft.model.PlaceFavorite;
import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by root on 22/12/2016.
 */

public class FragmentFavorite extends Fragment{
    ArrayList<PlaceFavorite> mPlaces;
    ItemListFavoritePlace itemListFavoritePlace;
    LayoutInflater mInflater;
    RecyclerView rc;
    private Realm realm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          mInflater=inflater;
        View view  =inflater.inflate(R.layout.list_fragment,container,false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView mImg= (ImageView) toolbar.findViewById(R.id.style);
        mImg.setVisibility(View.INVISIBLE);
        TextView title= (TextView) toolbar.findViewById(R.id.title);
        title.setText("Favorite Place");
        rc= (RecyclerView) view.findViewById(R.id.recyclerView);
          LinearLayoutManager ln=new LinearLayoutManager(mInflater.getContext(),LinearLayoutManager.VERTICAL,false);
         rc.setLayoutManager(ln);
         realm= RealmController.with(this).getRealm();
         mPlaces=new ArrayList<>(realm.where(PlaceFavorite.class).findAll());
         itemListFavoritePlace=new ItemListFavoritePlace(inflater.getContext(),mPlaces);
         rc.setAdapter(itemListFavoritePlace);
         rc.setItemAnimator(new DefaultItemAnimator());
        /* rc.addOnItemTouchListener(new RecycleViewClicker(mInflater.getContext(), rc, new RecycleViewClicker.click() {
             @Override
             public void onClick(View view, int position) {
                 Intent i=new Intent(mInflater.getContext(), ActivityDetailImage.class);
                 i.putExtra("from",1);
                 i.putExtra("id",mPlaces.get(position).getId());
                 startActivity(i);
             }

             @Override
             public void onLongClick(View view, int position) {

             }
         }));*/
         return view;
    }
}
