package com.example.root.projectfsoft.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.projectfsoft.DialogChoicePlace;
import com.example.root.projectfsoft.Modules.DirectionFinder;
import com.example.root.projectfsoft.Modules.DirectionFinderListener;
import com.example.root.projectfsoft.Modules.Route;
import com.example.root.projectfsoft.R;
import com.example.root.projectfsoft.service.response.Place;
import com.example.root.projectfsoft.service.services.PlaceFamousImpl;
import com.example.root.projectfsoft.service.util.CallBack;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by root on 26/12/2016.
 */

public class MapPlace extends Fragment implements View.OnClickListener, OnMapReadyCallback, DirectionFinderListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private EditText money;
    private Context mContext;
    private List<Marker> originMarkers = new ArrayList<>();
    private AutoCompleteTextView place;
    String chuoitim;
    private static String TAG = "Duong Di";
    String[] topic = new String[]{"cây cầu", "khu du lich", "khám phá phiêu lưu", "hay", "đẹp", "vui chơi giải trí", "thiên nhiên hoang dã", "trò chơi", "du lịch sinh thái", "làng đặc sản", "khám phá ẩm thực"};
    Button search;
    SupportMapFragment mMapFragment;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    GoogleMap mMap;
    double latitude;
    double longitude;
    int t = 0;
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    ArrayList<String> chuoimang;
    private Location mLastLocation;
    private PlaceFamousImpl placeFamous;
    private GoogleApiClient mGoogleApiClient;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = inflater.getContext();
        View view = LayoutInflater.from(inflater.getContext()).inflate(R.layout.goi_y_duong_di, container, false);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView mImg= (ImageView) toolbar.findViewById(R.id.style);
        mImg.setVisibility(View.INVISIBLE);
        TextView title= (TextView) toolbar.findViewById(R.id.title);
        title.setText("Map");
        mMapFragment = (SupportMapFragment) this.getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
        money = (EditText) view.findViewById(R.id.money);
        place = (AutoCompleteTextView) view.findViewById(R.id.autoText);
        search = (Button) view.findViewById(R.id.search);
        search.setOnClickListener(this);
        placeFamous=new PlaceFamousImpl(mContext);
        ArrayAdapter example = new ArrayAdapter(inflater.getContext(), android.R.layout.simple_list_item_1, topic);
        place.setAdapter(example);
        /*if(checkPlayServices()){
            buildGoogleApiClient();
        }
        displayLocation();*/
        return view;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(mContext);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode,getActivity(),
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(mContext,
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
            }
            return false;
        }
        return true;
    }

    private void displayLocation() {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();



        } else {
              Toast.makeText(mContext,"Couldn't get the location. Make sure location is enabled on the device",Toast.LENGTH_LONG).show();
        }
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
            chuoitim+="đà nẵng";
            chuoitim = chuoitim.replace(" ", "+");
            Intent i=new Intent(mContext, DialogChoicePlace.class);
            i.putExtra("chuoitim",chuoitim);
            startActivityForResult(i,1);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng hcmus = new LatLng(10.762963, 106.682394);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 5));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            if(resultCode==RESULT_OK) {
                chuoimang = data.getStringArrayListExtra("mang");

                for (int i = 0; i < chuoimang.size() - 1; i++)
                    try {
                        new DirectionFinder(this, chuoimang.get(i), chuoimang.get(i + 1)).execute();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
            }
        }
    }


    @Override
    public void onDirectionFinderStart() {
        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        t++;
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 8));

            if(t==1)
            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));

            placeFamous.getPlaceNeccessary(route.startLocation.latitude + "," + route.startLocation.longitude, "restaurant", new CallBack<ArrayList<Place>>() {
                @Override
                public void next(ArrayList<Place> data) {
                    for(int i=0;i<data.size();i++){
                        originMarkers.add(mMap.addMarker(new MarkerOptions()
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.restaurant_marker))
                                .title(data.get(i).getName())
                                .position(new LatLng(Double.parseDouble(data.get(i).getLocation().getLocation().getLat()),Double.parseDouble(data.get(i).getLocation().getLocation().getLng())))));
                    }
                }
            });

            placeFamous.getPlaceNeccessary(route.startLocation.latitude + "," + route.startLocation.longitude, "ATM", new CallBack<ArrayList<Place>>() {
                @Override
                public void next(ArrayList<Place> data) {
                    for(int i=0;i<data.size();i++){
                        originMarkers.add(mMap.addMarker(new MarkerOptions()
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.atm_maker))
                                .title(data.get(i).getName())
                                .position(new LatLng(Double.parseDouble(data.get(i).getLocation().getLocation().getLat()),Double.parseDouble(data.get(i).getLocation().getLocation().getLng())))));
                    }
                }
            });
            placeFamous.getPlaceNeccessary(route.startLocation.latitude + "," + route.startLocation.longitude, "gas station", new CallBack<ArrayList<Place>>() {
                @Override
                public void next(ArrayList<Place> data) {
                    for(int i=0;i<data.size();i++){

                        originMarkers.add(mMap.addMarker(new MarkerOptions()
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.gasstation))
                                .title(data.get(i).getName())
                                .position(new LatLng(Double.parseDouble(data.get(i).getLocation().getLocation().getLat()),Double.parseDouble(data.get(i).getLocation().getLocation().getLng())))));
                    }
                }
            });
            if(t==chuoimang.size()-1)
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
             displayLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }
}
