package com.example.myapplication.model;

public class Item {
    private String itemName;
    private String imageUrl;
    private String count;
    private String price;

    public Item() {
    }

    public Item(String itemName, String imageUrl, String count, String price) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
        this.count = count;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
