package com.example.HireCar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    ArrayList<LocationModel> dataList;
    Context context;


    public LocationAdapter(ArrayList<LocationModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_card, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        final LocationModel temp = dataList.get(position);

        holder.loc_name.setText(dataList.get(position).getLocation_name());
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditLocation.class);
                intent.putExtra("LocName", temp.getLocation_name());
                intent.putExtra("flag", "edit");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_loc(temp.getLocation_name());
//                Intent intent = new Intent(context, EditLocation.class);
//                intent.putExtra("LocName", temp.getLocation_name());
//                intent.putExtra("flag", "delete");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(intent);
            }
        });
    }

    public void delete_loc(String del_loction){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        db.collection("Location")
                .whereEqualTo("Name",del_loction)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isComplete()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Toast.makeText(context, "doc id:+ "+document.getId(), Toast.LENGTH_SHORT).show();
                                del_loc(document.getId());
//
                            }
                        }
                    }
                });
    }

    public void del_loc(String loc){
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        db.collection("Location").document(loc).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "deleted the data ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,ViewLocations.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class LocationViewHolder extends RecyclerView.ViewHolder{

        TextView loc_name;
        Button editBtn, delBtn;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            loc_name = itemView.findViewById(R.id.car_loc_txt);
            editBtn = itemView.findViewById(R.id.edit_location);
            delBtn = itemView.findViewById(R.id.del_location);
        }
    }
}
