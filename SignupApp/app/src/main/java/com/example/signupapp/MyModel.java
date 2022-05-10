package com.example.signupapp;

public class MyModel
{
    private String order_title,order_number,model_number;
    private String pick_header,drop_header,pick_date,drop_date;
    private int img;

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getModel_number() {
        return model_number;
    }

    public void setModel_number(String model_number) {
        this.model_number = model_number;
    }

    public String getPick_header() {
        return pick_header;
    }

    public void setPick_header(String pick_header) {
        this.pick_header = pick_header;
    }

    public String getDrop_header() {
        return drop_header;
    }

    public void setDrop_header(String drop_header) {
        this.drop_header = drop_header;
    }

    public String getPick_date() {
        return pick_date;
    }

    public void setPick_date(String pick_date) {
        this.pick_date = pick_date;
    }

    public String getDrop_date() {
        return drop_date;
    }

    public void setDrop_date(String drop_date) {
        this.drop_date = drop_date;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
