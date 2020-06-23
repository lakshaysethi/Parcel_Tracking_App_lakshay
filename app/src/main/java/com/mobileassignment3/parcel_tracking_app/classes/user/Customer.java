package com.mobileassignment3.parcel_tracking_app.classes.user;

import android.location.Address;
import android.provider.ContactsContract;

public class Customer extends  User{
    Address address;

    public Customer(ContactsContract.CommonDataKinds.Email email, String username, String password, Address address) {
        super(email, username, password);
        this.address = address;
    }


}
