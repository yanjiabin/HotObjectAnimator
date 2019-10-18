package com.example.library;

public class MyFloatFrameValue {
    private float percentage;

    private float mValue;

    public MyFloatFrameValue(float percentage, float mValue) {
        this.percentage = percentage;
        this.mValue = mValue;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getmValue() {
        return mValue;
    }

    public void setmValue(float mValue) {
        this.mValue = mValue;
    }
}
