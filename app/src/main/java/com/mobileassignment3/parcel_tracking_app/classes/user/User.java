package com.mobileassignment3.parcel_tracking_app.classes.user;

import android.provider.ContactsContract;


public class User {
    ContactsContract.CommonDataKinds.Email email;
    String username;
    String password;

    public User(ContactsContract.CommonDataKinds.Email email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(ContactsContract.CommonDataKinds.Email email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }


    public ContactsContract.CommonDataKinds.Email getEmail() {
        return email;
    }

    public void setEmail(ContactsContract.CommonDataKinds.Email email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email=" + email +
                ", username='" + username + '\'' +
                '}';
    }
}
