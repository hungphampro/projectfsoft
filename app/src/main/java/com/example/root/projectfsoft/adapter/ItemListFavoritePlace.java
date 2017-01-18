package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.projectfsoft.ActivityDetailImage;
import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.dataBase.RealmController;
import com.example.root.projectfsoft.model.PlaceFavorite;
import com.example.root.projectfsoft.model.ReviewPlace;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by root on 23/12/2016.
 */

public class ItemListFavoritePlace extends RecyclerView.Adapter<ItemListFavoritePlace.viewHolder> {
    public ArrayList<PlaceFavorite> mPlaces;
    private Context mContext;
    private Realm realm;
    public ItemListFavoritePlace(Context context,ArrayList<PlaceFavorite> places){
        this.mPlaces=places;
        mContext=context;
        realm= RealmController.with(mContext).getRealm();
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list_fragment,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
         final PlaceFavorite place=mPlaces.get(position);
         Bitmap bitmap = BitmapFactory.decodeByteArray(place.getImgPath(), 0, place.getImgPath().length);
         holder.avatarPlace.setImageBitmap(bitmap);
         holder.titlePlace.setText(place.getName());
         holder.rating.setText("Rating:"+String.valueOf(place.getRating()));
         holder.Address.setText(place.getAddress());
         holder.favorite.setImageResource(R.drawable.starfavorite);
         holder.favorite.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 mPlaces.remove(position);
                 notifyDataSetChanged();
                 RealmResults<PlaceFavorite> results = realm.where(PlaceFavorite.class).equalTo("id", place.getId()).findAll();
                 RealmResults<ReviewPlace> resultsReview = realm.where(ReviewPlace.class).equalTo("id", place.getId()).findAll();
                 RealmResults<com.example.root.projectfsoft.model.ShowImage> resultsImage = realm.where(com.example.root.projectfsoft.model.ShowImage.class).equalTo("idPlace", place.getId()).findAll();
                 realm.beginTransaction();
                 results.clear();
                 resultsReview.clear();
                 resultsImage.clear();
                 realm.commitTransaction();
             }
         });
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder{
        private ImageView avatarPlace;
        CardView card;
        TextView titlePlace,Address,rating;
        ImageButton favorite;
        public viewHolder(View itemView) {
            super(itemView);
            card= (CardView) itemView.findViewById(R.id.item);
            avatarPlace= (ImageView) itemView.findViewById(R.id.avatarPlace);
            titlePlace= (TextView) itemView.findViewById(R.id.title);
            Address= (TextView) itemView.findViewById(R.id.address);
            rating= (TextView) itemView.findViewById(R.id.rating);
            favorite= (ImageButton) itemView.findViewById(R.id.favorite);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mContext, ActivityDetailImage.class);
                    i.putExtra("from",1);
                    i.putExtra("id",mPlaces.get(getAdapterPosition()).getId());
                    mContext.startActivity(i);
                }
            });
        }
    }
}
