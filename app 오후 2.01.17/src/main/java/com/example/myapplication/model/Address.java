package com.example.myapplication.model;

import java.util.HashMap;
import java.util.Map;

public class Address {

    private String latitude;
    private String longitude;
    private String roadName;

    public Address() {
    }

    public Address(String latitude, String longitude, String roadName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.roadName = roadName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("latitude", latitude);
        result.put("longitude", longitude);
        result.put("roadName", roadName);
        return result;
    }
}
