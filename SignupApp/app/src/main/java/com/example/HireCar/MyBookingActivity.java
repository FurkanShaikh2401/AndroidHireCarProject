package com.example.HireCar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyBookingActivity extends AppCompatActivity {
    RecyclerView BookingViewRcv;
    BookingAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);


        BookingViewRcv=(RecyclerView)findViewById(R.id.myrecyclerview2);
        BookingViewRcv.setLayoutManager(new LinearLayoutManager(this));

        adapter=new BookingAdapter(dataqueue(), getApplicationContext());
        BookingViewRcv.setAdapter(adapter);
    }

    public ArrayList<BookingModel> dataqueue()
    {
        ArrayList<BookingModel> holder=new ArrayList<>();

        BookingModel obj1=new BookingModel();
        obj1.setOrder_id("10001");
        obj1.setBrandTitle("Hyundai");
        obj1.setModelTitle("Grand i10");
        obj1.setStartDate("30/05/2022");
        obj1.setEndDate("30/05/2022");
        obj1.setStartTime("06:00 AM");
        obj1.setEndTime("06:00 PM");
        obj1.setPriceTag("$2,250");
        obj1.setViewBtn("View");
        obj1.setImgId(R.drawable.car1);
        holder.add(obj1);

        BookingModel obj2=new BookingModel();
        obj2.setOrder_id("10002");
        obj2.setBrandTitle("Maruti");
        obj2.setModelTitle("Swift");
        obj2.setStartDate("01/06/2022");
        obj2.setEndDate("05/06/2022");
        obj2.setStartTime("08:00 AM");
        obj2.setEndTime("08:00 PM");
        obj2.setPriceTag("$2,250");
        obj2.setViewBtn("View");
        obj2.setImgId(R.drawable.marutiswift);
        holder.add(obj2);

        BookingModel obj3=new BookingModel();
        obj3.setOrder_id("10003");
        obj3.setBrandTitle("Maruti");
        obj3.setModelTitle("Ciaz");
        obj3.setStartDate("10/06/2022");
        obj3.setEndDate("15/06/2022");
        obj3.setStartTime("07:00 AM");
        obj3.setEndTime("07:00 PM");
        obj3.setPriceTag("$3,250");
        obj3.setViewBtn("View");
        obj3.setImgId(R.drawable.maruticiaz);
        holder.add(obj3);

        return holder;
    }
}
