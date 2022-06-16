package com.example.HireCar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class CarBookAdapterAdmin extends RecyclerView.Adapter<CarBookAdapterAdmin.CarBookAdminViewHolder>{

    Context context;
    ArrayList<CarBookModelAdmin> dataList;
    HashMap<String, String> carIds;

    public CarBookAdapterAdmin(ArrayList<CarBookModelAdmin> dataList, Context context) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CarBookAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_car_book_card, parent,false);
        return new CarBookAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarBookAdminViewHolder holder, int position) {
        final CarBookModelAdmin temp = dataList.get(position);

        holder.car_id.setText(dataList.get(position).getCar_id());
        holder.model_name.setText(dataList.get(position).getModel_name());
        holder.pickup_location.setText(dataList.get(position).getPickup_location());
        holder.drop_location.setText(dataList.get(position).getDrop_location());
        holder.start_date.setText(dataList.get(position).getStart_date());
        holder.end_date.setText(dataList.get(position).getEnd_date());
        holder.start_time.setText(dataList.get(position).getStart_time());
        holder.end_time.setText(dataList.get(position).getEnd_time());
        holder.amount.setText(dataList.get(position).getAmount());
        holder.mobileNo.setText(dataList.get(position).getMobileno());
        Glide.with(context).load(dataList.get(position).getCar_image()).into(holder.car_image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CarBookAdminViewHolder extends RecyclerView.ViewHolder{
        TextView pickup_location, drop_location, start_date, end_date, start_time, end_time;
        TextView car_id, model_name, user_id, amount, mobileNo;
        ImageView car_image;

        public CarBookAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            car_id = itemView.findViewById(R.id.carId);
            model_name = itemView.findViewById(R.id.modelName_admin);
            pickup_location = itemView.findViewById(R.id.PickupLoc_admin);
            drop_location = itemView.findViewById(R.id.DropLoc_admin);
            start_date = itemView.findViewById(R.id.startdate_txt_admin);
            end_date = itemView.findViewById(R.id.enddate_txt_admin);
            start_time = itemView.findViewById(R.id.starttime_txt_admin);
            end_time = itemView.findViewById(R.id.endtime_txt_admin);
            car_image = itemView.findViewById(R.id.carImg_admin);
            amount = itemView.findViewById(R.id.price);
            mobileNo = itemView.findViewById(R.id.textMobileNo);
        }
    }
}
