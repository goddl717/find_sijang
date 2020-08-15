package com.example.myapplication;

public class users {

    public users() {
    }

    private String users;
    private String name;
    private String tel;
    private String email;
    private String address;
    private boolean isAdmin;


    public users(String users, String name, String tel, String email, String address, boolean isAdmin) {
        this.users = users;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
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


}
