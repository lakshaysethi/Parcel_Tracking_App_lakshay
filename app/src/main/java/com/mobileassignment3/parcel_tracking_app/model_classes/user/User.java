package com.mobileassignment3.parcel_tracking_app.model_classes.user;

import android.provider.ContactsContract;


public class User {

final int DRIVER = 1001;
final int RECIEVER = 1002;
final int ADMIN = 1003;
    String email;
    String username;

    int[] typeArray;

    public int[] getTypeArray() {
        return typeArray;
    }
    public int getPrimaryType() {
            return this.typeArray[0];
    }
    public void setType(int type) {
        this.typeArray[0] = type;
    }

    public void setSecondaryType(int type) {
        this.typeArray[1] = type;
    }

    public void setThirdType(int type) {
        this.typeArray[3] = type;
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
