package com.example.HireCar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookingSummaryActivity extends AppCompatActivity {

    ImageView back_to_findcar_btn;
    Button payment_btn ;
    TextView start_date, end_date, start_time, end_time, pickuploc, droploc, baseFair, dpFair, refundableFair, totalFair, duration;
    String start_date1, end_date1, start_time1, end_time1, pickuploc1, droploc1;
    String days, hours;
    HashMap<String, String> CarIds;
    CheckBox TestCheckBox;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);

        start_time = findViewById(R.id.starttime_txt);
        end_time = findViewById(R.id.endtime_txt);
        start_date = findViewById(R.id.startdate_txt);
        end_date = findViewById(R.id.enddate_txt);
        pickuploc = findViewById(R.id.PickupLoc);
        droploc = findViewById(R.id.DropLoc);
        duration = findViewById(R.id.bs_duration);

        baseFair = findViewById(R.id.bs_basefair_price);
        dpFair = findViewById(R.id.bs_dpc_price);
        refundableFair = findViewById(R.id.bs_rfd_price);
        totalFair = findViewById(R.id.bs_total_price);
        payment_btn = findViewById(R.id.payment_btn);

        payment_btn.setVisibility(View.GONE);

        CarIds = (HashMap<String, String>)getIntent().getSerializableExtra("carids");


        TestCheckBox = (CheckBox)findViewById(R.id.tandcck);

        mAuth = FirebaseAuth.getInstance();
        Log.i("user",  mAuth.getCurrentUser().getUid().toString());
        Toast.makeText(getApplicationContext(), "brandName: " +
                getIntent().getStringExtra("brandName"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "modelName: " +
                getIntent().getStringExtra("modelName"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "FuelName: " +
                getIntent().getStringExtra("FuelTitle"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "TransmissionMode: " +
                getIntent().getStringExtra("TransmissionTitle"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Capcity: " +
                getIntent().getStringExtra("CapacityTitle"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Price: " +
                getIntent().getStringExtra("PriceTitle"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Pickup: " +
                getIntent().getStringExtra("PickUpLoc"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "Drop: " +
                getIntent().getStringExtra("DropLoc"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "StartDate: " +
                getIntent().getStringExtra("Start_Date"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "EndDate: " +
                getIntent().getStringExtra("End_Date"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "StartTime: " +
                getIntent().getStringExtra("Start_Time"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getApplicationContext(), "EndTime: " +
                getIntent().getStringExtra("End_Time"), Toast.LENGTH_SHORT).show();



        days = getIntent().getStringExtra("finaldays");
        hours = getIntent().getStringExtra("finalhours");

        pickuploc.setText(getIntent().getStringExtra("PickUpLoc"));
        droploc.setText(getIntent().getStringExtra("DropLoc"));
        start_date.setText(getIntent().getStringExtra("Start_Date"));
        end_date.setText(getIntent().getStringExtra("End_Date"));
        start_time.setText(getIntent().getStringExtra("Start_Time"));
        end_time.setText(getIntent().getStringExtra("End_Time"));

        if(hours.equals(0)){
            duration.setText("Duration: "+days);
        }
        else {
            duration.setText("Duration: "+days+"days "+hours+"hours");
        }

//        Intent intent = getIntent();
//        pickuploc.setText(intent.getExtras().getString("PickUpLoc"));
//        Toast.makeText(getApplicationContext(), "Profile: " +
//                    getIntent().getIntExtra("CarImage",0), Toast.LENGTH_SHORT).show();


        // initializing and setting the onClickListener
        back_to_findcar_btn = findViewById(R.id.bs_back_btn);
        back_to_findcar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingSummaryActivity.this, FindCarActivity.class));
            }
        });

        TestCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    payment_btn.setVisibility(View.VISIBLE);
                }
                else{
                    payment_btn.setVisibility(View.GONE);
                }
            }
        });


        payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] car_id = new String[1];
                String id;
                String user_id;
                String model_name = getIntent().getStringExtra("modelName");
                Log.i("mname", model_name);
                BookingCarModel bm = new BookingCarModel();
                Map<String, String> booking = new HashMap<>();
                //userid
                //carid

                for(Map.Entry<String, String> entry: CarIds.entrySet()) {

                    // if give value is equal to value from entry
                    // print the corresponding key
                    if(entry.getKey().equals(model_name)) {
                        //System.out.println("The key for value " + value + " is " + entry.getKey());
                        Log.i("bid", entry.getValue().toString());
                        car_id[0] = entry.getValue().toString();
                        break;
                    }
                }

//
                booking.put("pickup_location", pickuploc.getText().toString());
                booking.put("drop_location", droploc.getText().toString());
                booking.put("start_date", start_date.getText().toString());
                booking.put("end_date", end_date.getText().toString());
                booking.put("start_time", start_time.getText().toString());
                booking.put("end_time", end_time.getText().toString());
                booking.put("amount", totalFair.getText().toString());
                booking.put("user_id", mAuth.getCurrentUser().getUid().toString());
                //car_id[0]="MpkV8gkl0sWD1kp9GyMz";
                booking.put("car_id", car_id[0]);
//                booking.put("car_id", car_id[0].toString());
//                Log.i("passed", car_id[0].toString());

                // Add a new document with a generated ID
                db.collection("Booking")
                        .add(booking)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.i("DocumentSnapshot added with ID: ", documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("error", e.toString());
                            }
                        });
                Toast.makeText(BookingSummaryActivity.this, "Payment is successfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MyBookingActivity.class);
                intent.putExtra("brandName", getIntent().getStringExtra("brandName"));
                intent.putExtra("modelName", getIntent().getStringExtra("modelName"));
                intent.putExtra("startDate", String.valueOf(start_date));
                intent.putExtra("endDate", String.valueOf(end_date));
                intent.putExtra("startTime", String.valueOf(start_time));
                intent.putExtra("endTime", String.valueOf(end_time));
                intent.putExtra("price", totalFair.getText().toString());
                startActivity(intent);
            }
        });

        calculateCost();
    }

    private void calculateCost() {
        int totalAmount;
        int calTotalCost;
        int perhourcost;
        int hourcost;
        int cost = Integer.parseInt(getIntent().getStringExtra("Cost"));

        perhourcost = cost/24;
        hourcost = perhourcost * Integer.parseInt(hours);
        calTotalCost = (cost * Integer.parseInt(days)) + hourcost;

        dpFair.setText("800");
        refundableFair.setText("2500");
        baseFair.setText("Rs."+String.valueOf(calTotalCost));

        totalAmount = calTotalCost + Integer.parseInt(String.valueOf(dpFair.getText())) + Integer.parseInt(String.valueOf(refundableFair.getText()));
        dpFair.setText("Rs.800");
        refundableFair.setText("Rs.2500");
        totalFair.setText(String.valueOf(totalAmount));
        totalFair.setText(String.valueOf(totalAmount));
        totalFair.setText("Rs."+String.valueOf(totalAmount));
    }

}