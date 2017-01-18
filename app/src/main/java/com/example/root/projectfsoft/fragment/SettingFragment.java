package com.example.root.projectfsoft.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.dataBase.RealmController;
import com.example.root.projectfsoft.model.Setting;

import io.realm.Realm;

/**
 * Created by root on 29/12/2016.
 */

public class SettingFragment extends Fragment implements View.OnClickListener{
    LinearLayout category,placeRate,sortBy;
    TextView categoryName,numberRatePlace;
    CheckBox check;
    Realm realm;
    private Context mContext;
    Setting set;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.setting,container,false);
        mContext=inflater.getContext();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView mImg= (ImageView) toolbar.findViewById(R.id.style);
        mImg.setVisibility(View.INVISIBLE);
        realm= RealmController.with(this).getRealm();
        TextView title= (TextView) toolbar.findViewById(R.id.title);
        title.setText("Cài đặt");
        category= (LinearLayout) v.findViewById(R.id.category);
        placeRate= (LinearLayout) v.findViewById(R.id.placeRate);
        sortBy= (LinearLayout) v.findViewById(R.id.sortBy);
        categoryName= (TextView) v.findViewById(R.id.categoryName);
        numberRatePlace= (TextView) v.findViewById(R.id.NumberRatePlace);
        check=(CheckBox) v.findViewById(R.id.check);
        set=realm.where(Setting.class).findFirst();
        if(set!=null){
            categoryName.setText(set.getCategoryName());
            if(set.getRating()>-1)
                numberRatePlace.setText(String.valueOf(set.getRating()));
            else numberRatePlace.setText("Chưa chọn rating ");
            if(set.isSortByRating()) check.setChecked(true);
            else check.setChecked(false);
        }
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(set!=null){
                    if(set.isSortByRating()!=check.isChecked()){
                        realm.beginTransaction();
                        set.setSortByRating(check.isChecked());
                        realm.copyToRealm(set);
                        realm.commitTransaction();
                    }
                }else{
                    set=new Setting();
                    set.setCategoryName("Địa điểm du lịch nổi tiếng ");
                    set.setRating(-1);
                    set.setSortByRating(check.isChecked());
                    realm.beginTransaction();
                    realm.copyToRealm(set);
                    realm.commitTransaction();
                }
            }
        });
        category.setOnClickListener(this);
        placeRate.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.category:
                eventCategory();
                break;
            case R.id.placeRate:
                eventRate();
                break;
        }
    }
    public void eventCategory(){
        final AlertDialog.Builder dialogBuilderCategory = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.category, null);
        dialogBuilderCategory.setView(dialogView);
        final RadioButton rp= (RadioButton) dialogView.findViewById(R.id.popular);
        final RadioButton re= (RadioButton) dialogView.findViewById(R.id.ecological);
        final RadioButton ra= (RadioButton) dialogView.findViewById(R.id.adventure);
        final RadioButton rb= (RadioButton) dialogView.findViewById(R.id.beautiful);
        dialogBuilderCategory.setPositiveButton("CanCel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(rp.isChecked()) categoryName.setText(rp.getText().toString());
                else if(re.isChecked()) categoryName.setText(re.getText().toString());
                else if(ra.isChecked()) categoryName.setText(ra.getText().toString());
                else if(rb.isChecked()) categoryName.setText(rb.getText().toString());
                if(set!=null){
                    if(!set.getCategoryName().equals(categoryName.getText().toString())){
                        realm.beginTransaction();
                        set.setCategoryName(categoryName.getText().toString());
                        realm.copyToRealm(set);
                        realm.commitTransaction();
                    }
                }else{
                    set=new Setting();
                    set.setCategoryName(categoryName.getText().toString());
                    set.setRating(-1);
                    set.setSortByRating(false);
                    realm.beginTransaction();
                    realm.copyToRealm(set);
                    realm.commitTransaction();
                }
                dialogInterface.dismiss();
            }
        });
        final AlertDialog alertDialog = dialogBuilderCategory.create();

        alertDialog.show();
        Button bq = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        bq.setTextColor(Color.GREEN);
        Button bqN = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        bqN.setTextColor(Color.GREEN);

    }
    public void eventRate(){
        final AlertDialog.Builder dialogBuilderRate = new AlertDialog.Builder(getActivity());
        LayoutInflater inflaterRate = getActivity().getLayoutInflater();
        View dialogViewRate = inflaterRate.inflate(R.layout.movie_with_rate, null);
        dialogBuilderRate.setView(dialogViewRate);
        final SeekBar bar= (SeekBar) dialogViewRate.findViewById(R.id.seekBarRate);
        bar.setMax(5);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        dialogBuilderRate.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                numberRatePlace.setText(String.valueOf(bar.getProgress()));
                if(set!=null){
                    if(set.getRating()!=Double.parseDouble(numberRatePlace.getText().toString())){
                        realm.beginTransaction();
                        set.setRating(Double.parseDouble(numberRatePlace.getText().toString()));
                        realm.copyToRealm(set);
                        realm.commitTransaction();
                    }
                }else{
                    set=new Setting();
                    set.setCategoryName("Địa điểm du lịch nổi tiếng");
                    set.setRating(-1);
                    set.setSortByRating(false);
                    set.setRating(Double.parseDouble(numberRatePlace.getText().toString()));
                    realm.beginTransaction();
                    realm.copyToRealm(set);
                    realm.commitTransaction();
                }
                dialogInterface.dismiss();
            }
        });
        dialogBuilderRate.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        final AlertDialog alertDialogRate = dialogBuilderRate.create();
        alertDialogRate.show();
        Button bq = alertDialogRate.getButton(DialogInterface.BUTTON_POSITIVE);
        bq.setTextColor(Color.GREEN);
        Button bqN = alertDialogRate.getButton(DialogInterface.BUTTON_NEGATIVE);
        bqN.setTextColor(Color.GREEN);
    }
}
