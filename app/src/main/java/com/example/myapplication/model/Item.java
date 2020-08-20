package com.example.myapplication.model;

public class Item {
    private String imageUrl;
    private String name;
    private String detail;
    private String price;

    public Item() {
    }

    public Item(String imageUrl, String name, String detail, String price) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.detail = detail;
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}