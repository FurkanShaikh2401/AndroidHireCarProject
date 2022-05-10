package com.example.signupapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder
{
    TextView order_title,order_number,model_number;
    TextView pick_header,drop_header,pick_date,drop_date;
    ImageView img;
    Button detail_bttn;

    public myviewholder(@NonNull View itemView) {
        super(itemView);
        img=(ImageView)itemView.findViewById(R.id.car_img);
        detail_bttn=(Button)itemView.findViewById(R.id.detail_button);
        order_title=(TextView) itemView.findViewById(R.id.or_header);
        order_number=(TextView) itemView.findViewById(R.id.or_number);
        model_number=(TextView) itemView.findViewById(R.id.model_name);
        pick_header=(TextView) itemView.findViewById(R.id.pick_header);
        drop_header=(TextView) itemView.findViewById(R.id.drop_header);
        pick_date=(TextView) itemView.findViewById(R.id.pick_date);
        drop_date=(TextView) itemView.findViewById(R.id.drop_date);
    }
}
