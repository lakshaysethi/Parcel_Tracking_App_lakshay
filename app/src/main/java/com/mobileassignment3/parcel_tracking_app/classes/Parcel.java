package com.mobileassignment3.parcel_tracking_app.classes;

public class Parcel {

    String type;// can be letter or  box small or big or other
    String description;// sender's description of contents or notes

    public Parcel(String type, String description) {
        this.type = type;
        this.description = description;
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

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
