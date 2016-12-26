package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.model.ShowImage;
import com.example.root.projectfsoft.service.util.Contants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 24/12/2016.
 */

public class ItemImagePlace extends RecyclerView.Adapter<ItemImagePlace.ViewHolder> {

    private ArrayList<ShowImage> showImages;
    private ArrayList<com.example.root.projectfsoft.service.response.ShowImage> showImageFromInternet;
    private Context mContext;
    private static int t;
    private static String path="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";

    public ItemImagePlace(Context context,ArrayList<ShowImage> showImages,int t){
        this.showImages=showImages;
        mContext=context;
        this.t=t;
    }
    public ItemImagePlace(ArrayList<com.example.root.projectfsoft.service.response.ShowImage> showImageFromInternet,Context context,int t){
        this.mContext=context;
        this.showImageFromInternet=showImageFromInternet;
        this.t=t;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_image_place,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
                if(t==1){
                    ShowImage moi=showImages.get(position);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(moi.getDetailImage(), 0, moi.getDetailImage().length);
                    holder.imageView.setImageBitmap(bitmap);
                }else{
                    com.example.root.projectfsoft.service.response.ShowImage nua=showImageFromInternet.get(position);
                    Picasso.with(mContext).load(path+nua.getPhoto()+"&key="+ Contants.key).into(holder.imageView);
                }
    }

    @Override
    public int getItemCount() {
        if(t==1){
            return showImages.size();
        }
        else return showImageFromInternet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imgPlace);
        }
    }
}
