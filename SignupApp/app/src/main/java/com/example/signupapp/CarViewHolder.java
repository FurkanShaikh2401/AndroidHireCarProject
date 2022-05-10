package com.example.signupapp;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarViewHolder extends RecyclerView.ViewHolder {
    ImageView carimg;
    TextView brandTitle,modelTitle,dTitle1, dTitle2, dTitle3, priceTag;
    Button bookBtn;

    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        carimg = itemView.findViewById(R.id.carImg);
        brandTitle = itemView.findViewById(R.id.brandName);
        modelTitle = itemView.findViewById(R.id.modelName);
        dTitle1 = itemView.findViewById(R.id.diesel_txt);
        dTitle2 = itemView.findViewById(R.id.diesel_txt2);
        dTitle3 = itemView.findViewById(R.id.diesel_txt3);
        priceTag = itemView.findViewById(R.id.price_txt);
        bookBtn = itemView.findViewById(R.id.bookbtn);

    }
}
