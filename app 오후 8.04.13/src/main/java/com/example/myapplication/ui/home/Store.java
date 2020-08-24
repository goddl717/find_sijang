package com.example.myapplication.ui.home;

public class Store {
    private String title;
    private int ResourceID;

    public Store(String title, int resourceID) {
        this.title = title;
        ResourceID = resourceID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResourceID() {
        return ResourceID;
    }

    public void setResourceID(int resourceID) {
        ResourceID = resourceID;
    }
}
