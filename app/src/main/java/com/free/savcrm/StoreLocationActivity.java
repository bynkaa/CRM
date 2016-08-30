package com.free.savcrm;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bynkaa on 7/24/2016.
 */
public class StoreLocationActivity extends FragmentActivity {


    static final LatLng storeAddress = new LatLng(1.289378, 103.846692);
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_location);
        ButterKnife.bind(this);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.markers_map11)).getMap();
        Marker TP = map.addMarker(new MarkerOptions().
                position(storeAddress).title(""));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(storeAddress, 15));
    }

    @OnClick(R.id.back)
    void doBack()
    {
        onBackPressed();
    }
}
