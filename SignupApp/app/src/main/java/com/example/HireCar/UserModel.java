package com.example.HireCar;

public class UserModel {
    String DL_number, DL_photo, email, fullname, is_admin, mobile;

    public UserModel() {
    }

    public UserModel(String DL_number, String DL_photo, String email, String fullname, String is_admin, String mobile) {
        this.DL_number = DL_number;
        this.DL_photo = DL_photo;
        this.email = email;
        this.fullname = fullname;
        this.is_admin = is_admin;
        this.mobile = mobile;
    }

    public String getDL_number() {
        return DL_number;
    }

    public void setDL_number(String DL_number) {
        this.DL_number = DL_number;
    }

    public String getDL_photo() {
        return DL_photo;
    }

    public void setDL_photo(String DL_photo) {
        this.DL_photo = DL_photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}


