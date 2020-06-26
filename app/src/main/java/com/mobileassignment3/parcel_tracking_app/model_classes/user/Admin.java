package com.mobileassignment3.parcel_tracking_app.model_classes.user;

import android.provider.ContactsContract;

import com.mobileassignment3.parcel_tracking_app.model_classes.DeliveryJob;

import java.util.List;

public class Admin extends User{


    List<DeliveryJob> deliveryJobList;

    public Admin() {
    }

    public Admin(String email, String username) {
        super(email, username);
    }

    public List<DeliveryJob> getDeliveryJobList() {
        return deliveryJobList;
    }

    public void setDeliveryJobList(List<DeliveryJob> deliveryJobList) {
        this.deliveryJobList = deliveryJobList;
    }

}
