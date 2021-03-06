package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

public enum LandmarkType {
    // I may want to convert these into int constants,
    // as enums work differently in java compared to C#

    MALL(R.color.colorMalls),
    PARK(R.color.colorParks),
    OLD_BUILDING(R.color.colorOldBuildings),
    MUSEUM(R.color.colorMuseums),
    STADIUM(R.color.colorStadiums),
    ATTRACTION(R.color.colorAttractions);

    private final int color;

    LandmarkType(int color) {
        this.color = color;
    }

    public int getColor() { return color; }
    public int getColorFrom(Context c) { return c.getResources().getInteger(color); }
    public int getColorFrom(View v) { return v.getResources().getInteger(color); }

    public String format(boolean plural) {
        String str = toString().replace('_', ' ');
        return plural ? str + "S" : str;
    }

    public String format() {
        return format(true);
    }
}
