package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng mapLocation;
    private LatLng[] landMarkLocations;
    private TextView mTextView;

    public static final String TAG = "WhereAmIActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        GoogleApiAvailability availability =
                GoogleApiAvailability.getInstance();

        mTextView = findViewById(R.id.myLocationText);
        //Temporal default values(Will be removed at the completion of the implementation
        mapLocation = new LatLng(43.6, -79.4);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void setMapLocation(LatLng newLatLng)
    {
        mapLocation = newLatLng;
    }
    public void setLandMarkLocations(LatLng[] newLatLng)
    {
        landMarkLocations = newLatLng;
    }
    private void updateTextView(Location location) {
        String latLongString = "No location found";
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat:" + lat + "\nLong:" + lng;
        }

        String address = geocodeLocation(location);

        String outputText = "Your Current Position is:\n" + latLongString;
        if (!address.isEmpty())
            outputText += "\n\n" + address;

        mTextView.setText(outputText);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(mapLocation).title("Marker in Toronto"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mapLocation));
    }
    private String geocodeLocation(Location location) {
        String returnString = "";

        if (location == null) {
            Log.d(TAG, "No Location to Geocode");
            return returnString;
        }

        if (!Geocoder.isPresent()) {
            Log.e(TAG, "No Geocoder Available");
            return returnString;
        } else {
            Geocoder gc = new Geocoder(this, Locale.getDefault());
            try {
                //return a list of addresses
                List<Address> addresses
                        = gc.getFromLocation(location.getLatitude(),
                        location.getLongitude(),
                        1); // One Result

                StringBuilder sb = new StringBuilder();

                if (addresses.size() > 0) {
                    Address address = addresses.get(0);

                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                        sb.append(address.getAddressLine(i)).append("\n");

                    sb.append(address.getLocality()).append("\n");
                    sb.append(address.getPostalCode()).append("\n");
                    sb.append(address.getCountryName());
                }
                returnString = sb.toString();
            } catch (IOException e) {
                Log.e(TAG, "I/O Error Geocoding.", e);
            }
            return returnString;
        }
    }

}