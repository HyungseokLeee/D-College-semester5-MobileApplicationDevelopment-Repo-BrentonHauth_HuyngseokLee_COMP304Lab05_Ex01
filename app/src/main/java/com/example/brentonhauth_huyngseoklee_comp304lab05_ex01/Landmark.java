package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

public class Landmark {
    public final static String TYPE_EXTRA = "landmark_type";


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

    public static Landmark[] getLandmarksByType(LandmarkType type) {
        return new Landmark[0]; // temporary
    }

    public static int getTypeColor(LandmarkType type) {
        // Switch statement yields warning
        if (type == LandmarkType.OLD_BUILDING) {
            return R.color.colorOldBuildings;
        } else if (type == LandmarkType.MUSEUM) {
            return R.color.colorMuseums;
        } else if (type == LandmarkType.STADIUM) {
            return R.color.colorStadiums;
        } else if (type == LandmarkType.ATTRACTION) {
            return R.color.colorAttractions;
        } else return 0;
    }
}

enum LandmarkType {
    // I may want to convert these into int constants,
    // as enums work differently in java compared to C#
    OLD_BUILDING,
    MUSEUM,
    STADIUM,
    ATTRACTION;

    public String format() {
        String str = this.toString();
        return str.replace('_', ' ') + "S";
    }
}
