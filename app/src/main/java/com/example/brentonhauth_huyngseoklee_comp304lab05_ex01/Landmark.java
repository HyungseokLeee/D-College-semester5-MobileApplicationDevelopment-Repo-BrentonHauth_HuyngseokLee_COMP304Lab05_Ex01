package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class Landmark {
    public final static String
        TYPE_EXTRA = "landmark_type",
        ID_EXTRA = "landmark_id";
    private static int idCount = 0;

    private final static Landmark[] landmarks = {
        new Landmark(LandmarkType.ATTRACTION, "CN Tower", "290 Bremner Blvd, Toronto, ON M5V 3L9", 43.642651583304676, -79.3871211755523),
        new Landmark(LandmarkType.ATTRACTION, "Distillery Historic District", "55 Mill St, Toronto, ON M5A 3C4", 43.6504076517245, -79.3591435538638),
        new Landmark(LandmarkType.ATTRACTION, "Harbourfront Centre", "235 Queens Quay W, Toronto, ON M5V 3G3", 43.639495847692935, -79.38298376020919),

        new Landmark(LandmarkType.STADIUM, "Rogers Centre", "1 Blue Jays Way, Toronto, ON M5V 1J1", 43.64189624086562, -79.38909005835845),
        new Landmark(LandmarkType.STADIUM, "Scotia Bank Arena", "40 Bay St, (at Lakeshore Blvd.), Toronto, CA M5J 2X2", 43.64347089088837, -79.3791018026179),
        new Landmark(LandmarkType.STADIUM, "BMO Field", "170 Princes' Blvd, Toronto, ON M6K 3C3", 43.635511998194964, -79.41775045914567),

        new Landmark(LandmarkType.MUSEUM, "Spadina Museum", "285 Spadina Rd, Toronto, ON M5R 2V5", 43.67917194305236, -79.40806137184951),
        new Landmark(LandmarkType.MUSEUM, "Casa Loma", "1 Austin Terrace, Toronto, ON M5R 1X8", 43.67811667035288, -79.40942393399706),
        new Landmark(LandmarkType.MUSEUM, "Art Gallery of Ontario", "317 Dundas St W, Toronto, ON M5T 1G4", 43.653676442437586, -79.3925337602086),

        new Landmark(LandmarkType.PARK, "Trinity Bellwoods Park", "790 Queen St W, Toronto, ON M6J 1G3", 43.647352349794474, -79.41376751788052),
        new Landmark(LandmarkType.PARK, "High Park", "1873 Bloor St W, Toronto, ON M6R 2Z3", 43.65134950957768, -79.46314275276985),
        new Landmark(LandmarkType.PARK, "Bickford Park", "400 Grace St, Toronto, ON M6G 3A9", 43.662621333907644, -79.41873072532773),

        new Landmark(LandmarkType.MALL, "Dufferin Mall", "900 Dufferin St, Toronto, ON M6H 4A9", 43.65768864086945, -79.43610475617078),
        new Landmark(LandmarkType.MALL, "CF Toronto Eaton Centre", "220 Yonge St, Toronto, ON M5B 2H1", 43.65455461632417, -79.38072086020863),
        new Landmark(LandmarkType.MALL, "Yorkdale Shopping Centre", "3401 Dufferin St, Toronto, ON M6A 2T9", 43.72558714501656, -79.45216064671304),

        new Landmark(LandmarkType.OLD_BUILDING, "Clock Tower", "Toronto, ON M5A 3R6", 43.651262446905754, -79.35860932882731),
        new Landmark(LandmarkType.OLD_BUILDING, "George Brown House", "186 Beverley St, Toronto, ON M5T 1L4", 43.65695142792792, -79.39571405571562),
        new Landmark(LandmarkType.OLD_BUILDING, "Toronto Old City Hall", "60 Queen St W, Toronto, ON M5H 2M3", 43.65363724351847, -79.38161950704281),
    };


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

    private Landmark(LandmarkType type, String name, String address, double lat, double lng) {
        this(idCount++, type, name, address, lat, lng);
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


    public static Landmark[] getLandmarksByType(LandmarkType type, boolean b) {
        Landmark[] ofType = new Landmark[3]; // Temporary hard code length
        int x = 0;
        for (Landmark landmark : landmarks) {
            if (landmark.type == type) {
                Log.d("getLandmarksByType", "found: " + landmark.name);
                ofType[x++] = landmark;
                if (x == 3) break;
            }
        }

        return ofType; // temporary
    }

    // TODO: Remove this method when it is safe to do so
    public static int getTypeColor(LandmarkType type) {
        // Switch statement yields warning
        return type.getColor();
    }

    public static Landmark getLandmarkById(int id) {
        for (Landmark lm : landmarks) {
            if (lm.getLandmarkId() == id) {
                return lm;
            }
        }
        return null;
    }
}
