package com.example.root.projectfsoft.Modules;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Mai Thanh Hiep on 4/3/2016.
 */
public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;
    public String diem;

    public List<LatLng> points;
}
