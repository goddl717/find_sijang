package com.example.myapplication.model;

import java.util.HashMap;
import java.util.Map;

public class Market {
    private String address;
    private String imageurl;
    private String detail;
    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Market(){

    }

    public Market(Store store, String address, String imageurl, String detail) {
        this.store= store;
        this.address = address;
        this.imageurl = imageurl;
        this.detail = detail;

    }



    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
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
        result.put("address", address);
        result.put("imageurl", imageurl);
        result.put("detail", detail);
        result.put("store",store);
        return result;
    }
}