package com.example.HireCar;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    String pl, dl, sd, ed, st, et;
    String finalDays, finalHours;
    Context context;
    ArrayList<CarModel> dataList;
    HashMap<String, String> carIds;


    public CarAdapter(ArrayList<CarModel> data, HashMap<String, String> carIds, Context context, String start_date, String end_date, String start_time_txt, String end_time_txt, String autoCompleteTextView, String autoCompleteTextView2, String fdays, String fhours)
    {
        this.dataList = data;
        this.context = context;
        this.pl = autoCompleteTextView;
        this.dl = autoCompleteTextView2;
        this.sd = start_date;
        this.ed = end_date;
        this.st = start_time_txt;
        this.et = end_time_txt;
        this.finalDays = fdays;
        this.finalHours = fhours;
        this.carIds = carIds;
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

        final CarModel temp = dataList.get(position);

        holder.brandTitle.setText(dataList.get(position).getBrand_name());
        holder.modelTitle.setText(dataList.get(position).getModel_name());
        holder.fuelTitle.setText(dataList.get(position).getFuel());
        holder.transmissionTitle.setText(dataList.get(position).getTransmission());
        holder.capacityTitle.setText(dataList.get(position).getCapacity()+" seats");
        holder.cost.setText(dataList.get(position).getCost());
        holder.bookBtn.setText("Book");
//        holder.carImg.setImageResource(dataList.get(position).getImgId());
        Glide.with(context).load(dataList.get(position).getCar_image()).into(holder.carImg);

        holder.bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BookingSummaryActivity.class);
                intent.putExtra("brandName", temp.getBrand_name());
                intent.putExtra("modelName", temp.getModel_name());
                intent.putExtra("FuelTitle", temp.getFuel());
                intent.putExtra("TransmissionTitle", temp.getTransmission());
                intent.putExtra("CapacityTitle", temp.getCapacity());
                intent.putExtra("Cost", temp.getCost());
                intent.putExtra("carids", carIds);

                intent.putExtra("image", temp.getCar_image());

                intent.putExtra("PickUpLoc", pl);
                intent.putExtra("DropLoc",dl);
                intent.putExtra("Start_Date", sd);
                intent.putExtra("End_Date", ed);
                intent.putExtra("Start_Time", st);
                intent.putExtra("End_Time", et);

                intent.putExtra("finaldays", finalDays);
                intent.putExtra("finalhours", finalHours);

                //intent.putExtra("CarImage", temp.getImgId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}












//public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
//
//    ArrayList<CarModel> dataList;
//    Context context;
//    String model_name, category_name, fuel, tr;
//    String pl, dl;
//    String sd, ed, st, et;
//
//    public CarAdapter(ArrayList<CarModel> dataList, Context context,String start_date, String end_date, String start_time_txt, String end_time_txt, String autoCompleteTextView, String autoCompleteTextView2) {
//        this.dataList = dataList;
//        this.context = context;
//        this.pl = autoCompleteTextView;
//        this.dl = autoCompleteTextView2;
//        this.sd = start_date;
//        this.ed = end_date;
//        this.st = start_time_txt;
//        this.et = end_time_txt;
//    }
//
//    @NonNull
//    @Override
//    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_car_card, parent,false);
//        return new CarViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
////        holder.car_id.setText(dataList.get(position).getCar_id());
//        holder.model_name.setText(dataList.get(position).getModel_name());
//        holder.brand_name.setText(dataList.get(position).getBrand_name());
////        holder.category_name.setText(dataList.get(position).getCategory_name());
//        holder.fuel.setText(dataList.get(position).getFuel());
//        holder.transmission.setText(dataList.get(position).getTransmission());
//        holder.capacity.setText(dataList.get(position).getCapacity());
//        holder.cost.setText(dataList.get(position).getCost());
////        holder.available_flag.setText(dataList.get(position).getAvailable_flag());
//        //Picasso.with(context).load(dataList.get(position).getImageView2()).into(holder.car_image);
//        Glide.with(context).load(dataList.get(position).getImageView2()).into(holder.car_image);
//
//        //        holder.registration_number.setText(dataList.get(position).getRegistration_number());
////        holder.car_image.setText(dataList.get(position).getCar_image());
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }
//
//    class CarViewHolder extends RecyclerView.ViewHolder{
//        TextView car_id, cost, model_name, category_name, fuel, transmission, capacity, available_flag, registration_number, brand_name;
//        ImageView car_image;
//
//        public CarViewHolder(@NonNull View itemView){
//            super(itemView);
//            //car_id = itemView.findViewById(R.id.orderId);
//            model_name = itemView.findViewById(R.id.modelName);
//            brand_name = itemView.findViewById(R.id.brandName);
//            //category_name = itemView.findViewById(R.id.categoryname);
//            fuel = itemView.findViewById(R.id.fuel);
//            transmission = itemView.findViewById(R.id.transmission);
//            capacity = itemView.findViewById(R.id.capacity);
//            cost = itemView.findViewById(R.id.price);
////            available_flag = itemView.findViewById(R.id.bookbtn);
////            registration_number = itemView.findViewById(R.id.reg_txt);
//            car_image = itemView.findViewById(R.id.imageView);
//        }
//    }
//}