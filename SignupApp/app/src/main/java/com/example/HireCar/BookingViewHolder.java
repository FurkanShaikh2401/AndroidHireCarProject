package com.example.HireCar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookingViewHolder extends RecyclerView.ViewHolder {

    TextView brandTitle, modelTitle, startDate, endDate, startTime, endTime, priceTag;
    ImageView carImage;


    public BookingViewHolder(@NonNull View itemView) {
        super(itemView);
        brandTitle = itemView.findViewById(R.id.brandName);
        modelTitle = itemView.findViewById(R.id.modelName);
        startDate = itemView.findViewById(R.id.startdate_txt);
        endDate = itemView.findViewById(R.id.enddate_txt);
        startTime = itemView.findViewById(R.id.starttime_txt);
        endTime = itemView.findViewById(R.id.endtime_txt);
        priceTag = itemView.findViewById(R.id.price_txt);
        carImage =  itemView.findViewById(R.id.carImg);
    }
}
