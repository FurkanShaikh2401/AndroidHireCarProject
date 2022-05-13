package com.example.HireCar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myviewholder>
{

    ArrayList<MyModel> data;

    public myadapter(ArrayList<MyModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.activity_mybooking,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
//        holder.order_title.setText(data.get(position).getOrder_title());
        holder.order_number.setText(data.get(position).getOrder_number());
        holder.model_number.setText(data.get(position).getModel_number());
        holder.pick_header.setText(data.get(position).getPick_header());
        holder.pick_date.setText(data.get(position).getPick_date());
        holder.drop_header.setText(data.get(position).getDrop_header());
        holder.drop_date.setText(data.get(position).getDrop_date());
        holder.img.setImageResource(data.get(position).getImg());
//        holder.detail_bttn.setBottom(data.get(position).set);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
