package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

public class Landmark {
    private int landmarkId;

    private LandmarkType type;

    private String name;

    // May want to use longitude & latitude instead of address
    private String address;

    public Landmark() {}

    public Landmark(int landmarkId, LandmarkType type, String name, String address) {
        this.landmarkId = landmarkId;
        this.type = type;
        this.name = name;
        this.address = address;
    }

    public int getLandmarkId() { return landmarkId; }
    public void setLandmarkId(int landmarkId) { this.landmarkId = landmarkId; }

    public LandmarkType getType() { return type; }
    public void setType(LandmarkType type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}

enum LandmarkType {
    OLD_BUILDING,
    MUSEUM,
    STADIUM,
    ATTRACTION
}
