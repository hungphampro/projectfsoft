package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.projectfsoft.ActivityDetailImage;
import com.example.root.projectfsoft.DialogChoicePlace;
import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.dataBase.RealmController;
import com.example.root.projectfsoft.service.response.Place;
import com.example.root.projectfsoft.service.services.ImagePlaceImpl;
import com.example.root.projectfsoft.service.services.ReviewPlacesImpl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 27/12/2016.
 */

public class ItemListChoiceAdapter extends RecyclerView.Adapter<ItemListChoiceAdapter.ViewHolder> {

    ArrayList<Place> mPlaces;
    ArrayList<Place> mPlacesChoice;
    int t;
    Context mContext;
    DialogChoicePlace dl;
    public ItemListChoiceAdapter(ArrayList<Place> places,ArrayList<Place> placeChoice,Context context,int t){
        mPlaces=places;
        mPlacesChoice=placeChoice;
        mContext=context;
        dl= (DialogChoicePlace) mContext;
        this.t=t;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate( R.layout.item_list_choice,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Place p;
        if(t==1) {
            p = mPlaces.get(position);
        }else p=mPlacesChoice.get(position);
        holder.title.setText(p.getName());
        holder.rating.setText("Rating:"+p.getRating());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext, ActivityDetailImage.class);
                i.putExtra("from",2);
                i.putExtra("id",p.getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(i);

            }
        });
        Picasso.with(mContext).load(p.getUrlHinh()).into(holder.avatarPlace);
            if(t==1) {
                holder.action.setText("Choice");
                holder.action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPlacesChoice.add(p);
                        mPlaces.remove(position);
                        dl.getItemListAapter().notifyDataSetChanged();
                        dl.getItemListAapterChoice().notifyDataSetChanged();
                    }
                });
            }else {
                holder.action.setText("Remove");
                holder.action.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPlaces.add(p);
                        mPlacesChoice.remove(position);
                        dl.getItemListAapter().notifyDataSetChanged();
                        dl.getItemListAapterChoice().notifyDataSetChanged();
                    }
                });
            }

    }

    @Override
    public int getItemCount() {
        if(t==1) return mPlaces.size();
        else return mPlacesChoice.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatarPlace;
        private TextView title;
        private TextView rating;
        private TextView action;
        CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            avatarPlace= (ImageView) itemView.findViewById(R.id.avatarPlace);
            title= (TextView) itemView.findViewById(R.id.title);
            rating=(TextView)itemView.findViewById(R.id.rating);
            action=(TextView)itemView.findViewById(R.id.chon);
            card= (CardView) itemView.findViewById(R.id.card);
        }
    }
}
