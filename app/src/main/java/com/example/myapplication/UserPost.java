package com.example.myapplication;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

//@IgnoreExtraProperties
public class UserPost {


    private String name;
    private String tel;
    private String email;
    private String address;
    private boolean isAdmin;

    public UserPost() {
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public UserPost(String name, String tel, String email, String address, boolean isAdmin) {

        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", name);
        result.put("tel", tel);
        result.put("email", email);
        result.put("address", address);
        result.put("isAdmin", isAdmin);
        return result;
    }
}
