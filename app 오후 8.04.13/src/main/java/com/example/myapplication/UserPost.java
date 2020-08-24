package com.example.myapplication;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

//@IgnoreExtraProperties
public class UserPost {
    public UserPost(String address, String email, String isAdmin, String name, String tel) {
        this.address = address;
        this.email = email;
        this.isAdmin = isAdmin;
        this.name = name;
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    private String address;
    private String email;
    private String isAdmin;
    private String name;
    private String tel;


    public UserPost() {
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }



}
