package com.example.myapplication.model;


import java.util.HashMap;

public class Store {
    private String userId;
    private String storeName;
    private String imageUrl;
    private String detail;
    private String category;
    HashMap<String, Item> items = new HashMap<>();

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }

    public Store(String userId, String storeName, String imageUrl, String detail, String category) {
        this.userId = userId;
        this.storeName = storeName;
        this.imageUrl = imageUrl;
        this.detail = detail;
        this.category = category;
    }

    public Store() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("userId", userId);
//        result.put("storeName", storeName);
//        result.put("imageUrl", imageUrl);
//        result.put("detail", detail);
//        result.put("item", item.toMap());
//        return result;
//    }
}
