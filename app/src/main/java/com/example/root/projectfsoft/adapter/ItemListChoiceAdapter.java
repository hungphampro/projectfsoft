package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.dataBase.RealmController;
import com.example.root.projectfsoft.service.response.Place;
import com.example.root.projectfsoft.service.services.ImagePlaceImpl;
import com.example.root.projectfsoft.service.services.ReviewPlacesImpl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;

import static com.example.root.projectfsoft.R.drawable.place;

/**
 * Created by root on 27/12/2016.
 */

public class ItemListChoiceAdapter extends RecyclerView.Adapter<ItemListChoiceAdapter.ViewHolder> {

    ArrayList<Place> mPlaces;
    Context mContext;
    private Realm realm;
    private ReviewPlacesImpl mReviewPlaces;
    private ImagePlaceImpl imagePlace;
    private click mClick;
    public void setmClick(click mClick){
        this.mClick=mClick;
    }
   /* private static String path="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";*/
    public ItemListChoiceAdapter(ArrayList<Place> places, click mClick,Context context){
        mPlaces=places;
        mContext=context;
        this.mClick=mClick;
        /*realm= RealmController.with(mContext).getRealm();
        mReviewPlaces=new ReviewPlacesImpl(mContext);
        imagePlace=new ImagePlaceImpl(mContext);*/
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate( R.layout.item_list_choice,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Place p=mPlaces.get(position);
        holder.title.setText(p.getName());
        holder.rating.setText("Rating:"+p.getRating());
        Picasso.with(mContext).load(p.getUrlHinh()).into(holder.avatarPlace);
        holder.xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.onClick(view,position);
            }
        });
        holder.action.setText("Chọn");
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClick.onClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatarPlace;
        private TextView title;
        private TextView rating;
        private TextView xem;
        private TextView action;
        public ViewHolder(View itemView) {
            super(itemView);
            avatarPlace= (ImageView) itemView.findViewById(R.id.avatarPlace);
            title= (TextView) itemView.findViewById(R.id.title);
            rating=(TextView)itemView.findViewById(R.id.rating);
            xem=(TextView)itemView.findViewById(R.id.xem);
            action=(TextView)itemView.findViewById(R.id.chon);
        }
    }
    public interface click{
        void onClick(View view,int position);
        void onLongClick(View view,int position);
    }
}
