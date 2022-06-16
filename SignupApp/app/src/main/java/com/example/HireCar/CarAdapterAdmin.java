package com.example.HireCar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarAdapterAdmin extends RecyclerView.Adapter<CarAdapterAdmin.CarAdminViewHolder> {

    ArrayList<CarModelAdmin> dataList;
    Context context;

    public CarAdapterAdmin(ArrayList<CarModelAdmin> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CarAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_carview_card, parent, false);
        return new CarAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdminViewHolder holder, int position) {
        final CarModelAdmin temp = dataList.get(position);

        holder.carId.setText(dataList.get(position).getCarId());
        holder.registration_number.setText(dataList.get(position).getRegistration_number());
        holder.brand_name.setText(dataList.get(position).getBrand_name());
        holder.model_name.setText(dataList.get(position).getModel_name());
        holder.fuel.setText(dataList.get(position).getFuel());
        holder.transmission.setText(dataList.get(position).getTransmission());
        holder.capacity.setText(dataList.get(position).getCapacity()+"seats");
        holder.cost.setText(dataList.get(position).getCost());
        holder.category_name.setText(dataList.get(position).getCategory_name());
        holder.available_flag.setText(dataList.get(position).getAvailable_flag());
        holder.editBtn.setText("Edit");
        holder.delBtn.setText("Delete");

        Glide.with(context).load(dataList.get(position).getCar_image()).into(holder.car_image);

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditCarAdmin.class);
                intent.putExtra("carid", temp.getCarId());
                intent.putExtra("regno", temp.getRegistration_number());
                intent.putExtra("brandname", temp.getBrand_name());
                intent.putExtra("modelname", temp.getModel_name());
                intent.putExtra("fuel", temp.getFuel());
                intent.putExtra("trans", temp.getTransmission());
                intent.putExtra("cap", temp.getCapacity());
                intent.putExtra("cost", temp.getCost());
                intent.putExtra("catname", temp.getCategory_name());
                intent.putExtra("avlflag", temp.getAvailable_flag());
                intent.putExtra("carimage", temp.getCar_image());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_car(temp.getCarId());

            }
        });
    }

    public void delete_car(String id){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        db.collection("Cars").document(id).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "deleted the data ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,ViewCar.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CarAdminViewHolder extends RecyclerView.ViewHolder {

        TextView carId;
        TextView brand_name, model_name, fuel, transmission, capacity, cost, category_name, registration_number, available_flag;
        ImageView car_image;
        Button editBtn, delBtn;

        public CarAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            carId = itemView.findViewById(R.id.car_id_txt);
            brand_name = itemView.findViewById(R.id.brandName);
            model_name = itemView.findViewById(R.id.modelName);
            fuel = itemView.findViewById(R.id.fueltype_txt);
            transmission = itemView.findViewById(R.id.transtype_txt);
            capacity = itemView.findViewById(R.id.cap_txt);
            cost = itemView.findViewById(R.id.cost_txt);
            category_name = itemView.findViewById(R.id.bodytype_txt);
            registration_number = itemView.findViewById(R.id.regno_txt);
            available_flag = itemView.findViewById(R.id.avlflag_txt);
            car_image = itemView.findViewById(R.id.carImg);
            editBtn = itemView.findViewById(R.id.edtBtn);
            delBtn = itemView.findViewById(R.id.delBtn);
        }
    }
}