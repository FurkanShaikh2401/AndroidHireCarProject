package com.example.HireCar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    Context context;
    ArrayList<Model> data;

    public CarAdapter(ArrayList<Model> data, Context context)
    {
        this.data = data;
        this.context = context;
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

        final Model temp = data.get(position);

        holder.brandTitle.setText(data.get(position).getBrandTitle());
        holder.modelTitle.setText(data.get(position).getModelTitle());
        holder.dTitle1.setText(data.get(position).getdTitle1());
        holder.dTitle2.setText(data.get(position).getdTitle2());
        holder.dTitle3.setText(data.get(position).getdTitle3());
        holder.priceTag.setText(data.get(position).getPriceTag());
        holder.bookBtn.setText(data.get(position).getBookBtn());
        holder.carimg.setImageResource(data.get(position).getImgId());

        holder.bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BookingSummaryActivity.class);
                intent.putExtra("brandName", temp.getBrandTitle());
                intent.putExtra("modelName", temp.getModelTitle());
                intent.putExtra("FuelTitle", temp.getdTitle1());
                intent.putExtra("TransmissionTitle", temp.getdTitle2());
                intent.putExtra("CapacityTitle", temp.getdTitle3());
                intent.putExtra("PriceTitle", temp.getPriceTag());
                //intent.putExtra("CarImage", temp.getImgId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
