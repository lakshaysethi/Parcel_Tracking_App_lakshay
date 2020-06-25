package com.mobileassignment3.parcel_tracking_app;

import com.mobileassignment3.parcel_tracking_app.model_classes.DeliveryJob;

import java.util.List;

public class MasterListDocument {
   public List<DeliveryJob> masterList;
    public MasterListDocument(){}

    public List<DeliveryJob> getMasterList() {
        return masterList;
    }

    public void setMasterList(List<DeliveryJob> masterList) {
        this.masterList = masterList;
    }

}