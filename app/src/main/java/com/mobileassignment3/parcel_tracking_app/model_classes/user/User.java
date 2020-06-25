package com.mobileassignment3.parcel_tracking_app.model_classes.user;

import android.provider.ContactsContract;

import java.util.ArrayList;


public class User {

public    final static int DRIVER = 1001;
    public final static int RECIEVER = 1002;
    public final static int ADMIN = 1003;

    String email;
    String username;

    ArrayList<Integer> typeArray = new ArrayList<>();

    public ArrayList<Integer> getTypeArray() {
        return typeArray;
    }
    public int getPrimaryType() {
            return this.typeArray.get(0);
    }
    public void setType(int type) {
        this.typeArray.set(0, type);
    }

    public void setSecondaryType(int type) {
        this.typeArray.set(1,type);
    }

    public void setThirdType(int type) {
        this.typeArray.set(3,type);
    }



    public User(String email, String username) {
        this.email = email;
        this.username = username;

    }

    public User(String username) {
        this.username = username;

    }

    public User() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    @Override
    public String toString() {
        return "User{" +
                "email=" + email +
                ", username='" + username + '\'' +
                '}';
    }
}
