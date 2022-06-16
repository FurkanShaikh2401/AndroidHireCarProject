package com.example.HireCar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingViewHolder> {

    ArrayList<BookingModel> data;
    Context context;

    public BookingAdapter(ArrayList<BookingModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_mybooking_card, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {

//        final BookingModel temp = data.get(position);

        holder.brandTitle.setText(data.get(position).getBrandTitle());
        holder.modelTitle.setText(data.get(position).getModelTitle());
        holder.startDate.setText(data.get(position).getStartDate());
        holder.endDate.setText(data.get(position).getEndDate());
        holder.startTime.setText(data.get(position).getStartTime());
        holder.endTime.setText(data.get(position).getEndTime());
        holder.priceTag.setText(data.get(position).getPriceTag());
//        holder.viewBtn.setText(data.get(position).getViewBtn());
        holder.carImage.setImageResource(data.get(position).getImgId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }




}
