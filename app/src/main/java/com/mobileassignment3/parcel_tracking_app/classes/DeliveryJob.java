package com.mobileassignment3.parcel_tracking_app.classes;

import com.mobileassignment3.parcel_tracking_app.classes.user.Customer;
import com.mobileassignment3.parcel_tracking_app.classes.user.Driver;

import java.util.Date;
import java.util.List;

public class DeliveryJob {

    String status;
    String trackingNumber;
    List<Parcel> ListOfParcels;
    Driver assignedDriver;
    Customer receiver;
    Date targetDeliveryTime;


    public DeliveryJob(String status, String trackingNumber, List<Parcel> listOfParcels, Driver assignedDriver, Customer receiver, Date targetDeliveryTime) {
        this.status = status;
        this.trackingNumber = trackingNumber;
        ListOfParcels = listOfParcels;
        this.assignedDriver = assignedDriver;
        this.receiver = receiver;
        this.targetDeliveryTime = targetDeliveryTime;
    }

    public DeliveryJob(String trackingNumber, List<Parcel> listOfParcels, Date targetDeliveryTime) {
        this.trackingNumber = trackingNumber;
        ListOfParcels = listOfParcels;
        this.targetDeliveryTime = targetDeliveryTime;
        this.status = "New Order";
    }


    public DeliveryJob() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public List<Parcel> getListOfParcels() {
        return ListOfParcels;
    }

    public void setListOfParcels(List<Parcel> listOfParcels) {
        ListOfParcels = listOfParcels;
    }

    public Driver getAssignedDriver() {
        return assignedDriver;
    }

    public void setAssignedDriver(Driver assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    public Date getTargetDeliveryTime() {
        return targetDeliveryTime;
    }

    public void setTargetDeliveryTime(Date targetDeliveryTime) {
        this.targetDeliveryTime = targetDeliveryTime;
    }

    @Override
    public String toString() {
        return "DeliveryJob{" +
                "status='" + status + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", ListOfParcels=" + ListOfParcels +
                ", assignedDriver=" + assignedDriver +
                ", receiver=" + receiver +
                ", targetDeliveryTime=" + targetDeliveryTime +
                '}';
    }
}
