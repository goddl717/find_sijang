package com.example.myapplication.model;

import com.example.myapplication.model.Item;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private String userId;
    private String storeName;
    private String imageUrl;
    private String detail;
    private Item item;

    public Store(String userId, String storeName, String imageUrl, String detail, Item item) {
        this.userId = userId;
        this.storeName = storeName;
        this.imageUrl = imageUrl;
        this.detail = detail;
        this.item = item;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userId", userId);
        result.put("storeName", storeName);
        result.put("imageUrl", imageUrl);
        result.put("detail", detail);
        result.put("item", item);
        return result;
    }
}
