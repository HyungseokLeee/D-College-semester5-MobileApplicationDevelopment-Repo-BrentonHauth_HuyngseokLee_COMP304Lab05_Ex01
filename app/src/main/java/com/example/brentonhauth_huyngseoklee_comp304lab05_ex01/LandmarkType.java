package com.example.brentonhauth_huyngseoklee_comp304lab05_ex01;

public enum LandmarkType {
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
