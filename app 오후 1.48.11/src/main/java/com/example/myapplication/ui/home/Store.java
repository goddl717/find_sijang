package com.example.myapplication.ui.home;

import java.util.HashMap;
import java.util.Map;

public class Store {
    public String uid;
    public String cansold;
    public String item;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCansold() {
        return cansold;
    }

    public void setCansold(String cansold) {
        this.cansold = cansold;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Store(){

}
    public Store(String uid, String cansold, String item) {
        this.uid = uid;
        this.cansold = cansold;
        this.item = item;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("cansold", cansold);
        result.put("item", item);

        return result;
    }

}
