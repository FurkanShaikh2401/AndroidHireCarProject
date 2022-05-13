package com.example.HireCar;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Model {
    private int imgId;
    private String brandTitle,modelTitle,dTitle1, dTitle2, dTitle3, priceTag, bookBtn;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getBrandTitle() {
        return brandTitle;
    }

    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle;
    }

    public String getModelTitle() {
        return modelTitle;
    }

    public void setModelTitle(String modelTitle) {
        this.modelTitle = modelTitle;
    }

    public String getdTitle1() {
        return dTitle1;
    }

    public void setdTitle1(String dTitle1) {
        this.dTitle1 = dTitle1;
    }

    public String getdTitle2() {
        return dTitle2;
    }

    public void setdTitle2(String dTitle2) {
        this.dTitle2 = dTitle2;
    }

    public String getdTitle3() {
        return dTitle3;
    }

    public void setdTitle3(String dTitle3) {
        this.dTitle3 = dTitle3;
    }

    public String getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(String priceTag) {
        this.priceTag = priceTag;
    }

    public String getBookBtn() {
        return bookBtn;
    }

    public void setBookBtn(String bookBtn) {
        this.bookBtn = bookBtn;
    }
}
