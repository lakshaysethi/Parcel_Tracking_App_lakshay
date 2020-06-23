package com.mobileassignment3.parcel_tracking_app.classes;

public class Parcel {
    final int DELIVERED = 1;
    final int NOT_DELIVERED = 0;

    String type;// can be letter or  box small or big or other
    int status;
    String description;// sender's description of contents or notes

    public Parcel(String type, String description) {
        this.type = type;
        this.description = description;
        this.status = NOT_DELIVERED;
    }

    public Parcel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "type='" + type + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
