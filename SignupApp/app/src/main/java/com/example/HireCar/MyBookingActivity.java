package com.example.HireCar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MyBookingActivity extends AppCompatActivity {
    String start_date, end_date, start_time, end_time, brandName, modelName, amount, carid;

    FirebaseFirestore db;
    FirebaseStorage fs;
    StorageReference listRef;

    FirebaseAuth mAuth;

    String bokid, carImage, bodyType, FinalImage;

    BookingHistoryAdapter bookingHistoryAdapter;
    RecyclerView bookrcv;

    ArrayList<String> amountsList;
    ArrayList<String> imagelist;
    ArrayList<BookingHistoryModel> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);

        bookrcv = findViewById(R.id.myrecyclerview2);
        bookrcv.setLayoutManager(new LinearLayoutManager(this));


        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
        fs = FirebaseStorage.getInstance();
        listRef = fs.getReference().child("CarImages");

        bookList = new ArrayList<>();
        bookingHistoryAdapter = new BookingHistoryAdapter(bookList, getApplicationContext());
        bookrcv.setAdapter(bookingHistoryAdapter);



        Intent intent = getIntent();
        start_date =intent.getStringExtra("startDate");
        end_date =intent.getStringExtra("endDate");
        start_time =intent.getStringExtra("startTime");
        end_time =intent.getStringExtra("endTime");
        amount =intent.getStringExtra("price");
        modelName =intent.getStringExtra("modelName");
        carid =intent.getStringExtra("carid");


        Toast.makeText(getApplicationContext(), "StartDate: " + start_date, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "EndDate: " + end_date, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "StartTime: " + start_time, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "EndTime: " + end_time, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Amount: " + amount, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "ModelName: " + modelName, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "CarId: " + carid, Toast.LENGTH_SHORT).show();


//        Toast.makeText(getApplicationContext(), "Model: " +intent.getStringExtra("modelName"), Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "ImageLink" +intent.getStringExtra("carImage"), Toast.LENGTH_SHORT).show();


        db.collection("Booking").orderBy("end_date").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list){
                            if(d.getString("user_id").equals(mAuth.getCurrentUser().getUid().toString())) {
                                BookingHistoryModel bhm = new BookingHistoryModel();
                                bhm.setModelTitle(d.getString("model_name"));
                                bhm.setStartDate(d.getString("start_date"));
                                bhm.setEndDate(d.getString("end_date"));
                                bhm.setStartTime(d.getString("start_time"));
                                bhm.setEndTime(d.getString("end_time"));
                                bhm.setPriceTag(d.getString("amount"));
                                bhm.setImage(String.valueOf(d.getString("car_image")));
                                bookList.add(bhm);
                            }
                        }
                        bookingHistoryAdapter.notifyDataSetChanged();

                    }
                });

//        db.collection("Cars").document(carid).update("available_flag","false");

        //getBookingDetails();
        //getBookingDetaisl();
    }

    private void getBookingDetails() {


    }

//    public ArrayList<BookingModel> dataqueue()
//    {
//        ArrayList<BookingModel> holder=new ArrayList<>();
//
//        BookingModel obj1=new BookingModel();
//        obj1.setOrder_id("10001");
//        obj1.setBrandTitle("Hyundai");
//        obj1.setModelTitle("Grand i10");
//        obj1.setStartDate("30/05/2022");
//        obj1.setEndDate("30/05/2022");
//        obj1.setStartTime("06:00 AM");
//        obj1.setEndTime("06:00 PM");
//        obj1.setPriceTag("$2,250");
////        obj1.setViewBtn("View");
//        obj1.setImgId(R.drawable.car1);
//        holder.add(obj1);
//
//        BookingModel obj2=new BookingModel();
//        obj2.setOrder_id("10002");
//        obj2.setBrandTitle("Maruti");
//        obj2.setModelTitle("Swift");
//        obj2.setStartDate("01/06/2022");
//        obj2.setEndDate("05/06/2022");
//        obj2.setStartTime("08:00 AM");
//        obj2.setEndTime("08:00 PM");
//        obj2.setPriceTag("$2,250");
////        obj2.setViewBtn("View");
//        obj2.setImgId(R.drawable.marutiswift);
//        holder.add(obj2);
//
//        BookingModel obj3=new BookingModel();
//        obj3.setOrder_id("10003");
//        obj3.setBrandTitle("Maruti");
//        obj3.setModelTitle("Ciaz");
//        obj3.setStartDate("10/06/2022");
//        obj3.setEndDate("15/06/2022");
//        obj3.setStartTime("07:00 AM");
//        obj3.setEndTime("07:00 PM");
//        obj3.setPriceTag("$3,250");
////        obj3.setViewBtn("View");
//        obj3.setImgId(R.drawable.maruticiaz);
//        holder.add(obj3);
//
//        return holder;
//    }

//    public void getBookingDetaisl(){
////        String ph=mAuth.getCurrentUser().getPhoneNumber().toString();
//        FirebaseAuth ffd = FirebaseAuth.getInstance();
////        Toast.makeText(this, ffd.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
////        Toast.makeText(this, mAuth.getCurrentUser().getUid().toString(), +Toast.LENGTH_SHORT).show();
//        ffs = FirebaseFirestore.getInstance();
//        ffs.collection("Booking")
//                .whereEqualTo("user_id",ffd.getCurrentUser().getUid())
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                        for(DocumentSnapshot d:list){
//                            Toast.makeText(MyBookingActivity.this, "Uid-> "+String.valueOf(d.getString("car_id")), Toast.LENGTH_SHORT).show();
////                            c_user=mAuth.getCurrentUser().getUid().toString();
//                            String c_id = String.valueOf(d.getString("car_id"));
////                            if(c_id.equals("o7M5XjC3UmqKI1BFGRDc")){
////                                Toast.makeText(MyBookingActivity.this, " True ", Toast.LENGTH_SHORT).show();
//                                String de=getCarModel(c_id);
//                                Toast.makeText(MyBookingActivity.this, "Car Detals :->"+ getCarModel(c_id)+"--"+carImg+"--"+carModel, Toast.LENGTH_SHORT).show();
////                            }
//
//                        }
//                    }
//                });
//    }
//
//    public String getCarModel(String cid){
//        String[] carModel = new String[1];
//        ffs = FirebaseFirestore.getInstance();
//        ffs.collection("Cars").document(cid).get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        Toast.makeText(MyBookingActivity.this, "--"+documentSnapshot.getString("model_name"), Toast.LENGTH_SHORT).show();
//                        carBrand=documentSnapshot.getString("brand_name");
//                        carModel[0]=documentSnapshot.getString("car_image");
////                        return carModel[0];
////                        carModel =documentSnapshot.getString("model_name");
//
//                    }
//                });
//        Toast.makeText(this, "---"+carModel[0], Toast.LENGTH_SHORT).show();
//        return carModel[0];
//    }

}
