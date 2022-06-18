package com.example.HireCar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
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
import com.example.HireCar.UserModel;
import com.example.HireCar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final UserModel temp = dataList.get(position);

        holder.user_full_name.setText(dataList.get(position).getFullname());
        holder.user_email.setText(dataList.get(position).getEmail());
        holder.user_phone.setText(dataList.get(position).getMobile());
        holder.user_dl_number.setText(dataList.get(position).getDL_number());
        Glide.with(context).load(dataList.get(position).getDL_photo()).into(holder.car_image);
        holder.vBtn.setText("Verify User");

        holder.vBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify_user(temp.getMobile());
            }
        });
        checkVerification(holder);

    }
    public void verify_user(String mob){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        db.collection("users")
                .whereEqualTo("moblie",mob)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Toast.makeText(context, "doc id:+ "+document.getId(), Toast.LENGTH_SHORT).show();
                                verifed_user(document.getId());
                            }
                        }
                    }
                });

//        db.collection("users").document(uId).update("is_verify","true");

    }

    public  void verifed_user(String UId){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        db.collection("users").document(UId).update("is_verify","true");
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context,ViewUsers.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void checkVerification(UserViewHolder holder){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        db.collection("users")
                .whereEqualTo("is_verify","true")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){
                            for(QueryDocumentSnapshot document : task.getResult()){
//                                Toast.makeText(context, "doc id:+ "+document.getId(), Toast.LENGTH_SHORT).show();
                                holder.vBtn.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView user_full_name, user_email, user_phone, user_dl_number;
        ImageView car_image;
        Button vBtn;

        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            user_full_name = itemView.findViewById(R.id.userfullname);
            user_email = itemView.findViewById(R.id.useremail);
            user_phone = itemView.findViewById(R.id.userphone);
            user_dl_number = itemView.findViewById(R.id.userdlnumber);
            car_image = itemView.findViewById(R.id.carImg);
            vBtn = itemView.findViewById(R.id.verify_user_btn);
        }
    }
}
