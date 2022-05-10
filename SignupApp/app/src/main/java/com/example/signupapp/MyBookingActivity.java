package com.example.signupapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyBookingActivity extends AppCompatActivity {
    RecyclerView rcv;
    myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);
        setTitle("Recycler and card view demo");

        rcv=(RecyclerView)findViewById(R.id.myrecyclerview2);
        rcv.setLayoutManager(new LinearLayoutManager(this));

        adapter=new myadapter(dataqueue());
        rcv.setAdapter(adapter);
    }

    public ArrayList<MyModel> dataqueue()
    {
        ArrayList<MyModel> holder=new ArrayList<>();

        MyModel obj1=new MyModel();
        obj1.setOrder_number("098088687683");
        obj1.setDrop_date("09/09/2021");
        obj1.setPick_date("03/09/2021");
        obj1.setModel_number("car model number");
        obj1.setImg(R.drawable.car1);
        holder.add(obj1);

        MyModel obj2=new MyModel();
        obj2.setOrder_number("098088687683");
        obj2.setDrop_date("09/09/2021");
        obj2.setPick_date("03/09/2021");
        obj2.setModel_number("car model number");
        obj2.setImg(R.drawable.car1);
        holder.add(obj2);

        MyModel obj3=new MyModel();
        obj3.setOrder_number("098088687683");
        obj3.setDrop_date("09/09/2021");
        obj3.setPick_date("03/09/2021");
        obj3.setModel_number("car model number");
        obj3.setImg(R.drawable.car1);
        holder.add(obj3);

        return holder;
    }
}
