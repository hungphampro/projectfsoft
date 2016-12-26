package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.service.response.Place;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 22/12/2016.
 */

public class RecycleViewItemAdapterGril extends RecyclerView.Adapter<RecycleViewItemAdapterGril.ViewHolder> {

    ArrayList<Place> mPlaces;
    Context mContext;

    public RecycleViewItemAdapterGril(ArrayList<Place> places, Context context) {
        mPlaces = places;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_grilview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Place place = mPlaces.get(position);
        Picasso.with(mContext).load(place.getUrlHinh()).into(holder.avatarPlace);
        holder.titlePlace.setText(place.getName());
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatarPlace;
        TextView titlePlace;

        public ViewHolder(View itemView) {
            super(itemView);
            avatarPlace = (ImageView) itemView.findViewById(R.id.photo);
            titlePlace = (TextView) itemView.findViewById(R.id.place_name);

        }
    }
}
