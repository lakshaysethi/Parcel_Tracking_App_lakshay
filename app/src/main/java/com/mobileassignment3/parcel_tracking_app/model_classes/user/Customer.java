package com.mobileassignment3.parcel_tracking_app.model_classes.user;

import android.location.Address;
import android.provider.ContactsContract;

import com.mobileassignment3.parcel_tracking_app.model_classes.DeliveryJob;

import java.util.List;

public class Customer extends  User{
    Address address;
    List<DeliveryJob> deliveryJobList;

    public Customer() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<DeliveryJob> getDeliveryJobList() {
        return deliveryJobList;
    }

    public void setDeliveryJobList(List<DeliveryJob> deliveryJobList) {
        this.deliveryJobList = deliveryJobList;
    }

    public Customer(String email, String username, Address address, List<DeliveryJob> deliveryJobList) {
        super(email, username);
        this.address = address;
        this.deliveryJobList = deliveryJobList;
    }

}
