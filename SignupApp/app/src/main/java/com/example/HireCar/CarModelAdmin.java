package com.example.HireCar;

public class CarModelAdmin {
    String brand_name, model_name, fuel, transmission, capacity, cost, car_image;
    String category_name, registration_number, available_flag, carId;

    public CarModelAdmin() {
    }

    public CarModelAdmin(String brand_name, String model_name, String fuel, String transmission, String capacity, String cost, String car_image, String category_name, String registration_number, String available_flag, String carid) {
        this.brand_name = brand_name;
        this.model_name = model_name;
        this.fuel = fuel;
        this.transmission = transmission;
        this.capacity = capacity;
        this.cost = cost;
        this.car_image = car_image;
        this.category_name = category_name;
        this.registration_number = registration_number;
        this.available_flag = available_flag;
        this.carId = carid;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCar_image() {
        return car_image;
    }

    public void setCar_image(String car_image) {
        this.car_image = car_image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getAvailable_flag() {
        return available_flag;
    }

    public void setAvailable_flag(String available_flag) {
        this.available_flag = available_flag;
    }

}
