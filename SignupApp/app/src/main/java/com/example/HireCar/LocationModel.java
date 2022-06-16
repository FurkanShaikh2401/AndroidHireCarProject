package com.example.HireCar;

public class LocationModel {
    String Name;


    public LocationModel() {
    }

    public LocationModel(String location_name) {
        this.Name = location_name;
    }

    public String getLocation_name() {
        return Name;
    }

    public void setLocation_name(String location_name) {
        this.Name = location_name;
    }
}
