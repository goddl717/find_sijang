package com.example.myapplication;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

//@IgnoreExtraProperties
public class UserPost {
    private String users;
    private String name;
    private String tel;
    private String email;
    private String address;
    private boolean isAdmin;

    public UserPost(String users, String name, String tel, String email, String address, boolean isAdmin) {
        this.users = users;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getUser() {
        return users;
    }

    public void setUser(String users) {
        this.users = users;
    }


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
