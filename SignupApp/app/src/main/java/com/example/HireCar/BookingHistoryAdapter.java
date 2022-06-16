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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.BookingHistoryViewHolder> {

    ArrayList<BookingHistoryModel> dataList;
    Context context;

    public BookingHistoryAdapter(ArrayList<BookingHistoryModel> data, Context context) {
        this.dataList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public BookingHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_mybooking_card, parent, false);
        return new BookingHistoryViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull BookingHistoryViewHolder holder, int position) {

//        final BookingModel temp = data.get(position);

        holder.modelname.setText(dataList.get(position).getModelTitle());
        holder.startdate.setText(dataList.get(position).getStartDate());
        holder.enddate.setText(dataList.get(position).getEndDate());
        holder.starttime.setText(dataList.get(position).getStartTime());
        holder.endtime.setText(dataList.get(position).getEndTime());
        holder.amount.setText(dataList.get(position).getPriceTag());
        holder.brandname.setText(dataList.get(position).getBrandTitle());

        //Picasso.with(context).load(dataList.get(position).getImageView2()).into(holder.car_image);
        Glide.with(context).load(dataList.get(position).getImage()).into(holder.carImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class BookingHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView startdate, enddate, starttime, endtime, brandname, modelname, amount;
        ImageView carImage;

        public BookingHistoryViewHolder(@NonNull View itemView){
            super(itemView);

            startdate = itemView.findViewById(R.id.startdate_txt);
            enddate = itemView.findViewById(R.id.enddate_txt);
            starttime = itemView.findViewById(R.id.starttime_txt);
            endtime = itemView.findViewById(R.id.endtime_txt);
            brandname = itemView.findViewById(R.id.brandName);
            modelname = itemView.findViewById(R.id.modelName);
            amount = itemView.findViewById(R.id.price_txt);
            carImage = itemView.findViewById(R.id.carImg);
        }
    }

}
