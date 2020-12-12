package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

import com.google.android.gms.maps.model.LatLng;

public class Landmark {
    public final static String TYPE_EXTRA = "landmark_type";


    private int landmarkId;

    private LandmarkType type;

    private String name;

    private double longitude, latitude;

    private String address;

    public Landmark() {}

    public Landmark(int landmarkId, LandmarkType type,
                    String name, String address, double lat, double lng) {
        this.landmarkId = landmarkId;
        this.type = type;
        this.name = name;
        this.latitude = lat;
        this.longitude = lng;
        this.address = address;
    }

    public int getLandmarkId() { return landmarkId; }
    public void setLandmarkId(int landmarkId) { this.landmarkId = landmarkId; }

    public LandmarkType getType() { return type; }
    public void setType(LandmarkType type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LatLng getLatLng() { return new LatLng(latitude, longitude); }


    public static Landmark[] getLandmarksByType(LandmarkType type) {
        return new Landmark[0]; // temporary
    }

    // TODO: Remove this method when it is safe to do so
    public static int getTypeColor(LandmarkType type) {
        // Switch statement yields warning
        return type.getColor();
    }
}
