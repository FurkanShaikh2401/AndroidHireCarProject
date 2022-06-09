package com.example.HireCar;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class CarViewHolder extends RecyclerView.ViewHolder {
    ImageView carImg;
    TextView brandTitle,modelTitle,fuelTitle, transmissionTitle, capacityTitle, cost;
    Button bookBtn;

    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        carImg = itemView.findViewById(R.id.carImg);
        brandTitle = itemView.findViewById(R.id.brandName);
        modelTitle = itemView.findViewById(R.id.modelName);
        fuelTitle = itemView.findViewById(R.id.fuel);
        transmissionTitle = itemView.findViewById(R.id.transmission);
        capacityTitle= itemView.findViewById(R.id.capacity);
        cost = itemView.findViewById(R.id.price);
        bookBtn = itemView.findViewById(R.id.bookbtn);

    }
}
