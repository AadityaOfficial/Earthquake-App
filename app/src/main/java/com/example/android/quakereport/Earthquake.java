package com.example.android.quakereport;

/**
 * Created by aadit on 4/8/2017.
 */

public class Earthquake {
    double mMagnitude;
    String mLocation;
    long mTimeInMilliseconds;
    String mUrl;

    public Earthquake(double mMagnitude, String mLocation, long mTime, String mUrl) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mTimeInMilliseconds = mTime;
        this.mUrl = mUrl;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocationt() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
