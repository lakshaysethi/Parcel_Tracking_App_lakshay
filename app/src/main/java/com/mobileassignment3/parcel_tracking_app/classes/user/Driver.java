package com.mobileassignment3.parcel_tracking_app.classes.user;

import android.location.Location;
import android.provider.ContactsContract;

import com.mobileassignment3.parcel_tracking_app.classes.DeliveryJob;

import java.util.List;

public class Driver extends User {

    List<DeliveryJob> deliveryJobList;
    List<DeliveryJob> listOfCompletedDeliveries;
    Boolean onlineNow;
    Location currentLocation;

    public Driver(ContactsContract.CommonDataKinds.Email email, String password) {
        super(email, password);
    }

    public Driver(ContactsContract.CommonDataKinds.Email email, String username, String password) {
        super(email, username, password);
    }

    public Driver(String username, String password) {
        super(username, password);
    }

    public Driver() {
    }


    public List<DeliveryJob> getDeliveryJobList() {
        return deliveryJobList;
    }

    public void setDeliveryJobList(List<DeliveryJob> deliveryJobList) {
        this.deliveryJobList = deliveryJobList;
    }

    public List<DeliveryJob> getListOfCompletedDeliveries() {
        return listOfCompletedDeliveries;
    }

    public void setListOfCompletedDeliveries(List<DeliveryJob> listOfCompletedDeliveries) {
        this.listOfCompletedDeliveries = listOfCompletedDeliveries;
    }

    public Boolean getOnlineNow() {
        return onlineNow;
    }

    public void setOnlineNow(Boolean onlineNow) {
        this.onlineNow = onlineNow;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "username='" + username + '\'' +
                '}';
    }
}
