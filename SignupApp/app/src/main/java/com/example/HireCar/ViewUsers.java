package com.example.HireCar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.HireCar.UserAdapter;
import com.example.HireCar.UserModel;
import com.example.HireCar.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewUsers extends AppCompatActivity {

    RecyclerView userrcv,vfuserrecyclerview;
    ArrayList<UserModel> userList;
    ArrayList<UserModel> vfuserList;

    FirebaseFirestore db;
    UserAdapter userAdapter;
    verifiedUserAdapter verifiedUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        userrcv = findViewById(R.id.userrecyclerview);
        userrcv.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();

        vfuserrecyclerview=findViewById(R.id.vfuserrecyclerview);
        vfuserrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        vfuserList=new ArrayList<>();


        userAdapter = new UserAdapter(userList, getApplicationContext());
        userrcv.setAdapter(userAdapter);

        verifiedUserAdapter =new verifiedUserAdapter(vfuserList,getApplicationContext());
        vfuserrecyclerview.setAdapter(verifiedUserAdapter);


        db = FirebaseFirestore.getInstance();
        db.collection("users").whereEqualTo("is_verify","false").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            UserModel um = new UserModel();
                            um.setDL_photo(String.valueOf(d.getString("DL_photo")));
                            um.setFullname(d.getString("full name"));
                            um.setEmail(d.getString("email"));
                            um.setMobile(d.getString("moblie"));
                            um.setDL_number(d.getString("DL number"));
                            um.setIs_admin(d.getString("is_admin"));
                            um.setIs_verify("true");
                            Log.d("fullname", um.getFullname().toString());
                            userList.add(um);
                        }
                        userAdapter.notifyDataSetChanged();
                    }
                });

        db.collection("users")
                .whereEqualTo("is_verify","true")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            UserModel um = new UserModel();
                            um.setDL_photo(String.valueOf(d.getString("DL_photo")));
                            um.setFullname(d.getString("full name"));
                            um.setEmail(d.getString("email"));
                            um.setMobile(d.getString("moblie"));
                            um.setDL_number(d.getString("DL number"));
                            um.setIs_admin(d.getString("is_admin"));
                            um.setIs_verify("true");
                            Log.d("fullname", um.getFullname().toString());
                            vfuserList.add(um);
                        }
                        verifiedUserAdapter.notifyDataSetChanged();
                    }
                });

    }
}