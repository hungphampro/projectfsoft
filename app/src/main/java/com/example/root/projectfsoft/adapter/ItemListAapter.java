package com.example.root.projectfsoft.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.root.projectfsoft.service.response.ListComment;
import com.example.root.projectfsoft.service.response.Place;
import com.example.root.projectfsoft.service.response.Reviews;
import com.example.root.projectfsoft.service.response.ShowImage;
import com.example.root.projectfsoft.service.services.ImagePlaceImpl;
import com.example.root.projectfsoft.service.services.ReviewPlacesImpl;
import com.example.root.projectfsoft.service.util.CallBack;
import com.example.root.projectfsoft.service.util.Contants;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by root on 21/12/2016.
 */

public class ItemListAapter extends RecyclerView.Adapter<ItemListAapter.ViewHolder> {

    ArrayList<Place> mPlaces;
    Context mContext;
    private Realm realm;
    private ReviewPlacesImpl mReviewPlaces;
    private ImagePlaceImpl imagePlace;
    private static String path="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
    public ItemListAapter(ArrayList<Place> places,Context context){
        mPlaces=places;
        mContext=context;
        realm= RealmController.with(mContext).getRealm();
        mReviewPlaces=new ReviewPlacesImpl(mContext);
        imagePlace=new ImagePlaceImpl(mContext);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate( R.layout.item_list_fragment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
          final Place place=mPlaces.get(position);
          Picasso.with(mContext).load(place.getUrlHinh()).into(holder.avatarPlace);
          holder.titlePlace.setText(place.getName());
          holder.Address.setText(place.getAddress());

          holder.rating.setText("Rating:"+place.getRating());
          if(realm.where(PlaceFavorite.class).equalTo("id",place.getId()).findFirst()!=null) {
              holder.favorite.setImageResource(R.drawable.starfavorite);
              holder.favorite.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      if(realm.where(PlaceFavorite.class).equalTo("id",place.getId()).findAll()!=null) {
                          RealmResults<PlaceFavorite> results = realm.where(PlaceFavorite.class).equalTo("id", place.getId()).findAll();
                          RealmResults<ReviewPlace> resultsReview = realm.where(ReviewPlace.class).equalTo("id", place.getId()).findAll();
                          RealmResults<com.example.root.projectfsoft.model.ShowImage> resultsImage = realm.where(com.example.root.projectfsoft.model.ShowImage.class).equalTo("idPlace", place.getId()).findAll();
                          holder.favorite.setImageResource(R.drawable.star);
                          realm.beginTransaction();
                          results.clear();
                          resultsReview.clear();
                          resultsImage.clear();
                          realm.commitTransaction();
                      }
                  }
              });
          }else{
              holder.favorite.setImageResource(R.drawable.star);
              holder.favorite.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      /*if(realm.where(PlaceFavorite.class).equalTo("id",place.getId()).findAll()==null) {*/
                          holder.favorite.setImageResource(R.drawable.starfavorite);
                          PlaceFavorite placeStartFavorite = new PlaceFavorite(place.getId(), place.getAddress(), place.getName(), ImageView_To_Byte(holder.avatarPlace), place.getRating());
                          mReviewPlaces.getReviewPlace(place.getId(), new CallBack<ListComment>() {
                              @Override
                              public void next(ListComment data) {
                                  for (int i = 0; i < data.getImage().size(); i++) {

                                      if (null != data.getImage().get(i).getPhoto_url()) {
                                          ImageView img = new ImageView(mContext);
                                          while (img.getDrawable() == null) {
                                              Picasso.with(mContext).load("https://" + data.getImage().get(i).getPhoto_url().substring(2, data.getImage().get(i).getPhoto_url().length())).into(img);
                                          }

                                          ReviewPlace rv = new ReviewPlace(place.getId(), data.getImage().get(i).getNameAuthor(), ImageView_To_Byte(img), data.getImage().get(i).getContent(), data.getImage().get(i).getRating(), data.getImage().get(i).getTime());
                                          realm.beginTransaction();
                                          realm.copyToRealm(rv);
                                          realm.commitTransaction();
                                      } else {
                                          holder.face.setImageResource(R.drawable.avatar);
                                          ReviewPlace rv = new ReviewPlace(place.getId(), data.getImage().get(i).getNameAuthor(), ImageView_To_Byte(holder.face), data.getImage().get(i).getContent(), data.getImage().get(i).getRating(), data.getImage().get(i).getTime());
                                          realm.beginTransaction();
                                          realm.copyToRealm(rv);
                                          realm.commitTransaction();
                                      }

                                  }
                              }
                          });
                          imagePlace.getImagePlcae(place.getId(), new CallBack<ArrayList<ShowImage>>() {
                              @Override
                              public void next(ArrayList<ShowImage> data) {
                                  for (int i = 0; i < data.size(); i++) {
                                      //System.out.println("cai gia phai tra:"+path + data.get(i).getPhoto());
                                      ImageView moi = new ImageView(mContext);
                                      while (moi.getDrawable() == null) {
                                          Picasso.with(mContext).load(path + data.get(i).getPhoto() + "&key=" + Contants.key).into(moi);
                                      }
                                      if (moi.getDrawable() != null) {
                                          byte[] dovao = ImageView_To_Byte(moi);
                                          com.example.root.projectfsoft.model.ShowImage noi = new com.example.root.projectfsoft.model.ShowImage(place.getId(), dovao);
                                          realm.beginTransaction();
                                          realm.copyToRealm(noi);
                                          realm.commitTransaction();
                                      }
                                  }
                              }
                          });
                          realm.beginTransaction();
                          realm.copyToRealm(placeStartFavorite);
                          realm.commitTransaction();
                      /*}*/
                  }
              });
          }

    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView avatarPlace;
        CardView card;
        TextView titlePlace,Address,rating;
        ImageButton favorite;
        ImageView face;
        public ViewHolder(View itemView) {
            super(itemView);
            card= (CardView) itemView.findViewById(R.id.item);
            avatarPlace= (ImageView) itemView.findViewById(R.id.avatarPlace);
            titlePlace= (TextView) itemView.findViewById(R.id.title);
            Address= (TextView) itemView.findViewById(R.id.address);
            rating= (TextView) itemView.findViewById(R.id.rating);
            favorite= (ImageButton) itemView.findViewById(R.id.favorite);
            face= (ImageView) itemView.findViewById(R.id.face);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(mContext, ActivityDetailImage.class);
                    i.putExtra("from",2);
                    i.putExtra("id",mPlaces.get(getAdapterPosition()).getId());
                    mContext.startActivity(i);
                }
            });
        }
    }
    public byte[] ImageView_To_Byte(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
