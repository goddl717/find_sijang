package com.example.myapplication.ui.home;

import java.util.HashMap;
import java.util.Map;

public class Address {

    public String x;
    public String y;
    public String koreaType;

    public Address(){

    }
    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getKoreaType() {
        return koreaType;
    }

    public void setKoreaType(String koreaType) {
        this.koreaType = koreaType;
    }

    public Address(String x, String y, String koreaType) {
        this.x = x;
        this.y = y;
        this.koreaType = koreaType;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("x", x);
        result.put("y", y);
        result.put("koreaType", koreaType);

        return result;
    }
}
