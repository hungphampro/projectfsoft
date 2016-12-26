package com.example.root.projectfsoft.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.service.response.Place;
import com.example.root.projectfsoft.service.services.PlaceFamousImpl;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by root on 26/12/2016.
 */

public class MapPlace extends Fragment implements View.OnClickListener{
    private EditText money;
    private Context mContext;
    PlaceFamousImpl plceFamous;
    ArrayList<Place> dsplace;
    private AutoCompleteTextView place;
    String chuoitim;
    String[] topic=new String[]{"lãng mạn","cổ kính","khám phá phiêu lưu","thám hiểm","đẹp","vui chơi giải trí","thiên nhiên hoang dã","trò chơi","du lịch sinh thái","làng đặc sản","khám phá ẩm thực"};
    Button search;
    MapView mapView;
    GoogleMap map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=inflater.getContext();
        View view = LayoutInflater.from(inflater.getContext()).inflate(R.layout.goi_y_duong_di, container, false);

        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        money= (EditText) view.findViewById(R.id.money);
        place= (AutoCompleteTextView) view.findViewById(R.id.autoText);
        search=(Button) view.findViewById(R.id.search);
        ArrayAdapter example=new ArrayAdapter(inflater.getContext(),android.R.layout.simple_list_item_1,topic);
        place.setAdapter(example);

        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        if (ActivityCompat.checkSelfPermission(inflater.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(inflater.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
              return view;
        }
        map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
        map.animateCamera(cameraUpdate);
        search.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.search){
            if(place.getText().toString().trim().isEmpty()){
                chuoitim="địa điểm du lịch nổi tiếng";
            }else{
                chuoitim=place.getText().toString();
            }

            chuoitim=chuoitim.replace(" ","+");
        }
    }
}
