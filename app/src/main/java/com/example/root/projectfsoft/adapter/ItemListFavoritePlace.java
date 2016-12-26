package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.model.PlaceFavorite;

import java.util.ArrayList;

/**
 * Created by root on 23/12/2016.
 */

public class ItemListFavoritePlace extends RecyclerView.Adapter<ItemListFavoritePlace.viewHolder> {
    public ArrayList<PlaceFavorite> mPlaces;
    private Context mContext;
    public ItemListFavoritePlace(Context context,ArrayList<PlaceFavorite> places){
        this.mPlaces=places;
        mContext=context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list_fragment,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
         PlaceFavorite place=mPlaces.get(position);
         Bitmap bitmap = BitmapFactory.decodeByteArray(place.getImgPath(), 0, place.getImgPath().length);
         holder.avatarPlace.setImageBitmap(bitmap);
         holder.titlePlace.setText(place.getName());
         holder.rating.setText("Rating:"+String.valueOf(place.getRating()));
         holder.Address.setText(place.getAddress());
         holder.favorite.setImageResource(R.drawable.starfavorite);

    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private ImageView avatarPlace;
        TextView titlePlace,Address,rating;
        ImageButton favorite;
        public viewHolder(View itemView) {
            super(itemView);
            avatarPlace= (ImageView) itemView.findViewById(R.id.avatarPlace);
            titlePlace= (TextView) itemView.findViewById(R.id.title);
            Address= (TextView) itemView.findViewById(R.id.address);
            rating= (TextView) itemView.findViewById(R.id.rating);
            favorite= (ImageButton) itemView.findViewById(R.id.favorite);
        }
    }
}
