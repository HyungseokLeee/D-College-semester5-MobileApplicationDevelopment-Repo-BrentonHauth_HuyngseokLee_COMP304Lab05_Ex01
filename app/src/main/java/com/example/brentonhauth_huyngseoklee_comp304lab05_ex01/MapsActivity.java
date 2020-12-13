package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng mapLocation;
    private LatLng landMarkLocation;
    private LocationRequest mLocationRequest;
    private TextView mTextView;
    private List<Marker> mMarkers = new ArrayList<>();
    private Polyline mPolyline;

    public static final String TAG = "MapActivity";
    private static final String ERROR_MSG = "Google Play services are unavailable.";
    private static final int LOCATION_PERMISSION_REQUEST = 1;
    private static final int REQUEST_CHECK_SETTINGS = 2;

    private Landmark landmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mTextView = findViewById(R.id.myLocationText);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        GoogleApiAvailability availability =
                GoogleApiAvailability.getInstance();

        int result = availability.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (!availability.isUserResolvableError(result)) {
                Toast.makeText(this, ERROR_MSG, Toast.LENGTH_LONG).show();
            }
        }
        //Set the desired interval for active location updates, in milliseconds.
        // and the priority of the request.
        mLocationRequest = new LocationRequest()
                .setInterval(5000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //Temporal default values(Will be removed at the completion of the implementation

        Intent in = getIntent();
        int id = in.getIntExtra(Landmark.ID_EXTRA, -1);

        if (id == -1) {
            Toast.makeText(this,
                "invalid landmark!",
                Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        landmark = Landmark.getLandmarkById(id);

        if (landmark != null) {
            mapLocation = landmark.getLatLng();
        } else finish();
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
        mMap.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        // Add a marker in Sydney and move the camera
        //Currently commented out for when needed attributes are implemented.
        /*
        mMap.addMarker(new MarkerOptions().position(landMarkLocation).title("Marker in Toronto"));
         */

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        //set options for a poplyline
        PolylineOptions polylineOptions = new PolylineOptions()
                .color(Color.CYAN)
                .geodesic(true);
        //add polyline to the map
        mPolyline = mMap.addPolyline(polylineOptions);
    }
    LocationCallback mLocationCallback = new LocationCallback() {
        //called when device location information is available.
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location location = locationResult.getLastLocation();
            if (location != null) {
                updateTextView(location);
            }
            if (location != null) {
                updateTextView(location);
                if (mMap != null) {
                    LatLng latLng = new LatLng(location.getLatitude(),
                            location.getLongitude());
                    //animates the movement of the camera to the updated position
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                    Calendar c = Calendar.getInstance();
                    String dateTime = DateFormat.format("MM/dd/yyyy HH:mm:ss",
                            c.getTime()).toString();

                    int markerNumber = mMarkers.size()+1;
                    //adds to array list the marker added to the map
                    mMarkers.add(mMap.addMarker(new MarkerOptions()
                            .position(mapLocation)
                            .title(dateTime)
                            .snippet("Marker #" + markerNumber +
                                    " @ " + dateTime)));
                    //The list returned is a copy of the list of vertices
                    List<LatLng> points = mPolyline.getPoints();
                    //adds the latitude/longitude to the end of list points
                    points.add(latLng);
                    //updates polyline
                    mPolyline.setPoints(points);
                }
            }
        }
    };
    @Override
    //Update the current location each time the app becomes visible:
    protected void onStart() {
        super.onStart();

        // Check if we have permission to access high accuracy fine location.
        int permission = ActivityCompat.checkSelfPermission(this,
                ACCESS_FINE_LOCATION);

        // If permission is granted, fetch the last location.
        if (permission == PERMISSION_GRANTED) {
            getLastLocation();
        } else {
            // If permission has not been granted, request permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST);
        }

        // Check if the location settings are compatible with our Location Request.
        LocationSettingsRequest.Builder builder =
                new LocationSettingsRequest.Builder()
                        .addLocationRequest(mLocationRequest);
        // Determine if the relevant system settings are enabled on the device
        // to carry out the desired location request.
        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
        //To be notified when the task succeeds
        //called when the task completes successfully
        task.addOnSuccessListener(this, locationSettingsResponse -> {
            // Location settings satisfy the requirements of the Location Request.
            // Request location updates.
            requestLocationUpdates();
        });

        task.addOnFailureListener(this, e -> {
            // Extract the status code for the failure from within the Exception.
            int statusCode = ((ApiException) e).getStatusCode();

            switch (statusCode) {
                case CommonStatusCodes.RESOLUTION_REQUIRED:
                    try {
                        // Display a user dialog to resolve the location settings
                        // issue.
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(MapsActivity.this,
                                REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        Log.e(TAG, "Location Settings resolution failed.", sendEx);
                    }
                    break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                    // Location settings issues can't be resolved by user.
                    // Request location updates anyway.
                    Log.d(TAG, "Location Settings can't be resolved.");
                    requestLocationUpdates();
                    break;
            }
        });
    }
    private void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
                == PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)
                        == PERMISSION_GRANTED) {

            FusedLocationProviderClient fusedLocationClient
                    = LocationServices.getFusedLocationProviderClient(this);
            //register for location updates
            fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
        }
    }
    private void getLastLocation() {
        FusedLocationProviderClient fusedLocationClient;
        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)
                == PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION)
                        == PERMISSION_GRANTED) {
            //return the best most recent location currently available
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            updateTextView(location);
                        }
                    });
        }
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
    public void setMapLocation(LatLng newLatLng)
    {
        mapLocation = newLatLng;
    }
    public void setLandMarkLocations(LatLng newLatLng)
    {
        landMarkLocation = newLatLng;
    }

}