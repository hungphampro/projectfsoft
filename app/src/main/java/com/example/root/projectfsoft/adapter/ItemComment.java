package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.model.ReviewPlace;
import com.example.root.projectfsoft.service.response.Reviews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by root on 24/12/2016.
 */

public class ItemComment extends RecyclerView.Adapter<ItemComment.ViewHoler> {

    private ArrayList<ReviewPlace> mReviewPlaces;
    private ArrayList<Reviews> mReviewses;
    private Context mContext;
    private static int t;

    public ItemComment(Context context,ArrayList<ReviewPlace> mReviewPlaces,int t){
        this.mContext=context;
        this.mReviewPlaces=mReviewPlaces;
        this.t=t;
    }
    public ItemComment(ArrayList<Reviews> mReviewses,Context context,int t){
        this.mContext=context;
        this.mReviewses=mReviewses;
        this.t=t;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_comment_place,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
           if(t==1){
               ReviewPlace rvp=mReviewPlaces.get(position);
               Bitmap bitmap= BitmapFactory.decodeByteArray(rvp.getImgPath(),0,rvp.getImgPath().length);
               holder.avatar.setImageBitmap(bitmap);
               holder.authorName.setText(rvp.getAuthorName());
               holder.rating.setText(String.valueOf(rvp.getRating()));
               holder.content.setText(rvp.getContent());
           }else{
               Reviews rvs=mReviewses.get(position);
               Picasso.with(mContext).load(rvs.getPhoto_url().substring(2,rvs.getPhoto_url().length())).into(holder.avatar);
               holder.authorName.setText(rvs.getNameAuthor());
               holder.rating.setText(String.valueOf(rvs.getRating()));
               holder.content.setText(rvs.getContent());
           }
    }

    @Override
    public int getItemCount() {
        if(t==1){
            return mReviewPlaces.size();
        }else return mReviewses.size();
    }

    public static class ViewHoler extends RecyclerView.ViewHolder{
        private CircleImageView avatar;
        private TextView authorName;
        private TextView rating;
        private TextView content;
        public ViewHoler(View itemView) {
            super(itemView);
            avatar= (CircleImageView) itemView.findViewById(R.id.avatarAuthor);
            authorName= (TextView) itemView.findViewById(R.id.authorName);
            rating= (TextView) itemView.findViewById(R.id.rating);
            content= (TextView) itemView.findViewById(R.id.comment);
        }
    }
}
