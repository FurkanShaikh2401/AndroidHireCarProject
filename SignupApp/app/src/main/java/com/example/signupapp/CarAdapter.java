package com.example.signupapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    ArrayList<Model> data;

    public CarAdapter(ArrayList<Model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        View view = Inflater.inflate(R.layout.available_car_card, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.brandTitle.setText(data.get(position).getBrandTitle());
        holder.modelTitle.setText(data.get(position).getModelTitle());
        holder.dTitle1.setText(data.get(position).getdTitle1());
        holder.dTitle2.setText(data.get(position).getdTitle2());
        holder.dTitle3.setText(data.get(position).getdTitle3());
        holder.priceTag.setText(data.get(position).getPriceTag());
        holder.bookBtn.setText(data.get(position).getBookBtn());
        holder.carimg.setImageResource(data.get(position).getImgId());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
