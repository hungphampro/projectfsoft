package com.example.root.projectfsoft.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.projectfsoft.R;

/**
 * Created by root on 29/12/2016.
 */

public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.about_layout,container,false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView mImg= (ImageView) toolbar.findViewById(R.id.style);
        mImg.setVisibility(View.INVISIBLE);
        TextView title= (TextView) toolbar.findViewById(R.id.title);

        title.setText("About");
       return v;
    }
}
