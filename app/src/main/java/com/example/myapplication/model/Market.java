package com.example.myapplication.model;

import java.util.HashMap;
import java.util.Map;

public class Market {
    private Address address;
    private String imageurl;
    private String detail;


    public Market(){

    }

    public Market(Address address, String imageurl, String detail) {
        this.address = address;
        this.imageurl = imageurl;
        this.detail = detail;

    }



    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("address", address.toMap());
        result.put("imageurl", imageurl);
        result.put("detail", detail);
        return result;
    }
}