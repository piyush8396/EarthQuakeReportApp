package com.example.earthquakereport;

import java.text.DecimalFormat;

public class EarthQuake {
    private double magnitude;
    private String place;
    private long date;
    private  String url;
    public EarthQuake(double magnitude,String place,long date,String url)
    {
        this.magnitude=magnitude;
        this.place=place;
        this.date=date;
        this.url=url;
    }

    public double getMagnitude() {
        DecimalFormat Format=new DecimalFormat("0.0");
        Format.format(magnitude);
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public long getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
