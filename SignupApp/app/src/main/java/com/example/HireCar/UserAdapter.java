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
import com.example.HireCar.UserModel;
import com.example.HireCar.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    ArrayList<UserModel> dataList;
    Context context;

    public UserAdapter(ArrayList<UserModel> userList, Context applicationContext) {
        this.dataList = userList;
        this.context = applicationContext;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final UserModel temp = dataList.get(position);

        holder.user_full_name.setText(dataList.get(position).getFullname());
        holder.user_email.setText(dataList.get(position).getEmail());
        holder.user_phone.setText(dataList.get(position).getMobile());
        holder.user_dl_number.setText(dataList.get(position).getDL_number());
        Glide.with(context).load(dataList.get(position).getDL_photo()).into(holder.car_image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView user_full_name, user_email, user_phone, user_dl_number;
        ImageView car_image;

        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            user_full_name = itemView.findViewById(R.id.userfullname);
            user_email = itemView.findViewById(R.id.useremail);
            user_phone = itemView.findViewById(R.id.userphone);
            user_dl_number = itemView.findViewById(R.id.userdlnumber);
            car_image = itemView.findViewById(R.id.carImg);
        }
    }
}
