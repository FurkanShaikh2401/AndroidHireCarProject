package com.example.HireCar;

public class CarBookModelAdmin {

    String car_id, car_image, pickup_location, drop_location, start_date, end_date, start_time, end_time, model_name, user_id, amount, mobile;

    public CarBookModelAdmin() {
    }

    public CarBookModelAdmin(String car_id, String car_image, String pickup_location, String drop_location, String start_date, String end_date, String start_time, String end_time, String model_name, String user_id, String amount, String mbno) {
        this.car_id = car_id;
        this.car_image = car_image;
        this.pickup_location = pickup_location;
        this.drop_location = drop_location;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.model_name = model_name;
        this.user_id = user_id;
        this.amount = amount;
        this.mobile = mbno;
    }

    public String getMobileno() {
        return mobile;
    }

    public void setMobileno(String mobileno) {
        this.mobile = mobileno;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getCar_image() {
        return car_image;
    }

    public void setCar_image(String car_image) {
        this.car_image = car_image;
    }

    public String getPickup_location() {
        return pickup_location;
    }

    public void setPickup_location(String pickup_location) {
        this.pickup_location = pickup_location;
    }

    public String getDrop_location() {
        return drop_location;
    }

    public void setDrop_location(String drop_location) {
        this.drop_location = drop_location;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}