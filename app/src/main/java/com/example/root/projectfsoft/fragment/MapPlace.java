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
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 26/12/2016.
 */

public class MapPlace extends Fragment implements View.OnClickListener,OnMapReadyCallback {
    private EditText money;
    private Context mContext;
    PlaceFamousImpl plceFamous;
    ArrayList<Place> dsplace;
    private List<Marker> originMarkers = new ArrayList<>();
    private AutoCompleteTextView place;
    String chuoitim;
    String[] topic = new String[]{"lãng mạn", "cổ kính", "khám phá phiêu lưu", "thám hiểm", "đẹp", "vui chơi giải trí", "thiên nhiên hoang dã", "trò chơi", "du lịch sinh thái", "làng đặc sản", "khám phá ẩm thực"};
    Button search;
    SupportMapFragment mMapFragment;
    GoogleMap mMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = inflater.getContext();
        View view = LayoutInflater.from(inflater.getContext()).inflate(R.layout.goi_y_duong_di, container, false);

        mMapFragment = (SupportMapFragment) this.getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

        money = (EditText) view.findViewById(R.id.money);
        place = (AutoCompleteTextView) view.findViewById(R.id.autoText);
        search = (Button) view.findViewById(R.id.search);
        ArrayAdapter example = new ArrayAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, topic);
        place.setAdapter(example);
        search.setOnClickListener(this);
        return view;
    }

    private void setupMap() {
         mMap.getUiSettings().setZoomControlsEnabled(false);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.search) {
            if (place.getText().toString().trim().isEmpty()) {
                chuoitim = "địa điểm du lịch nổi tiếng";
            } else {
                chuoitim = place.getText().toString();
            }

            chuoitim = chuoitim.replace(" ", "+");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng hcmus = new LatLng(10.762963, 106.682394);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 18));
        originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("Đại học Khoa học tự nhiên")
                .position(hcmus)));

        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

}
