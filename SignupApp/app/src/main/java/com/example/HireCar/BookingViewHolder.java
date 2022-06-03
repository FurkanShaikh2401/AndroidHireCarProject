package com.example.HireCar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookingViewHolder extends RecyclerView.ViewHolder {

    TextView order_id;
    TextView brandTitle, modelTitle, startDate, endDate, startTime, endTime, priceTag;
    ImageView carImage;
    Button viewBtn;

    public BookingViewHolder(@NonNull View itemView) {
        super(itemView);
        order_id = itemView.findViewById(R.id.orderId);
        brandTitle = itemView.findViewById(R.id.brandName);
        modelTitle = itemView.findViewById(R.id.modelName);
        startDate = itemView.findViewById(R.id.startdate_txt);
        endDate = itemView.findViewById(R.id.enddate_txt);
        startTime = itemView.findViewById(R.id.starttime_txt);
        endTime = itemView.findViewById(R.id.endtime_txt);
        priceTag = itemView.findViewById(R.id.price_txt);
        viewBtn = itemView.findViewById(R.id.bookviewbtn);
        carImage =  itemView.findViewById(R.id.carImg);
    }
}
