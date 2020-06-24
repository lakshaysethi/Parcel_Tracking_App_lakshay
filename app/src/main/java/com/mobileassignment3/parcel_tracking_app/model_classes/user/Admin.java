package com.mobileassignment3.parcel_tracking_app.model_classes.user;

import android.provider.ContactsContract;

public class Admin extends User{

    public Admin(ContactsContract.CommonDataKinds.Email email, String username, String password) {
        super(email, username, password);
    }

    
}
