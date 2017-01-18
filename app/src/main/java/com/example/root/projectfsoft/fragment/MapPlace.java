package com.example.root.projectfsoft.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.root.projectfsoft.DialogChoicePlace;
import com.example.root.projectfsoft.R;

import com.example.root.projectfsoft.service.services.PlaceFamousImpl;


/**
 * Created by root on 26/12/2016.
 */

public class MapPlace extends Fragment implements View.OnClickListener{
    private EditText money;
    private Context mContext;
    private AutoCompleteTextView place;
    PlaceFamousImpl placeFamous;
    private static View view;
    String chuoitim;
    String[] topic = new String[]{"cây cầu", "khu du lich", "khám phá phiêu lưu", "hay", "đẹp", "vui chơi giải trí", "thiên nhiên hoang dã", "trò chơi", "du lịch sinh thái", "làng đặc sản", "khám phá ẩm thực"};
    Button search;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = inflater.getContext();
        view = LayoutInflater.from(inflater.getContext()).inflate(R.layout.goi_y_duong_di, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView mImg = (ImageView) toolbar.findViewById(R.id.style);
        mImg.setVisibility(View.INVISIBLE);
        TextView title = (TextView) toolbar.findViewById(R.id.title);
        title.setText("Bản đồ");

        money = (EditText) view.findViewById(R.id.money);
        place = (AutoCompleteTextView) view.findViewById(R.id.autoText);
        search = (Button) view.findViewById(R.id.search);
        search.setOnClickListener(this);
        placeFamous = new PlaceFamousImpl(mContext);
        ArrayAdapter example = new ArrayAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, topic);
        place.setAdapter(example);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.search) {
            if (place.getText().toString().trim().isEmpty()) {
                chuoitim = "địa điểm du lịch nổi tiếng";
            } else {
                chuoitim = place.getText().toString();
            }
            chuoitim+="đà nẵng";
            chuoitim = chuoitim.replace(" ", "+");
            Intent i=new Intent(mContext, DialogChoicePlace.class);
            i.putExtra("chuoitim",chuoitim);
            startActivity(i);
        }
    }


}
