package com.example.onurbarman.webservice.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserList {


    private ArrayList<User> userList;

    public ArrayList<User> getUserArrayList() {
        return userList;
    }

    public void setUserArrayList(ArrayList<User> userList) {
        this.userList = userList;
    }
}
