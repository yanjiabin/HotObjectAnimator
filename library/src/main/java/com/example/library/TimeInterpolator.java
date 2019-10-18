package com.example.library;

public class TimeInterpolator implements LinearInterpolator {

    @Override
    public float getInterpolator(float percentage) {
        return 0.5f*percentage;
    }
}
