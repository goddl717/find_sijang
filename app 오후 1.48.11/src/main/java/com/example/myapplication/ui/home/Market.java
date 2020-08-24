package com.example.myapplication.ui.home;

import java.util.HashMap;
import java.util.Map;

public class Market {
    public Address address;
    public String imageurl;
    public String detail;
    public Store store;

    public Market(Address address, String imageurl, String detail, Store store) {
        this.address = address;
        this.imageurl = imageurl;
        this.detail = detail;
        this.store = store;
    }



    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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

    public Market(){

    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("address", address.toMap());
        result.put("imageurl", imageurl);
        result.put("detail", detail);
        result.put("store",store.toMap());

        return result;
    }
}
