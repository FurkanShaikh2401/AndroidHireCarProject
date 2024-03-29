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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookingSummaryActivity extends AppCompatActivity {

    ImageView back_to_findcar_btn;
    Button payment_btn;
    TextView model_name, start_date, end_date, start_time, end_time, pickuploc, droploc, baseFair, dpFair, refundableFair, totalFair, duration;
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
        model_name = findViewById(R.id.modelTag);

        CarIds = (HashMap<String, String>) getIntent().getSerializableExtra("carids");

        TestCheckBox = (CheckBox) findViewById(R.id.tandcck);
        payment_btn.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        Log.i("user", mAuth.getCurrentUser().getUid().toString());


        days = getIntent().getStringExtra("finaldays");
        hours = getIntent().getStringExtra("finalhours");

        model_name.setText(getIntent().getStringExtra("modelName"));
        pickuploc.setText(getIntent().getStringExtra("PickUpLoc"));
        droploc.setText(getIntent().getStringExtra("DropLoc"));
        start_date.setText(getIntent().getStringExtra("Start_Date"));
        end_date.setText(getIntent().getStringExtra("End_Date"));
        start_time.setText(getIntent().getStringExtra("Start_Time"));
        end_time.setText(getIntent().getStringExtra("End_Time"));


        if (hours.equals(0)) {
            duration.setText("Duration: " + days);
        } else {
            duration.setText("Duration: " + days + "days " + hours + "hours");
        }

//        Intent intent = getIntent();
//        pickuploc.setText(intent.getExtras().getString("PickUpLoc"));
//        Toast.makeText(getApplicationContext(), "Profile: " +
//                    getIntent().getIntExtra("CarImage",0), Toast.LENGTH_SHORT).show();


        // initializing and setting the onClickListener


        TestCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.collection("users").document(mAuth.getCurrentUser().getUid())
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.getResult().get("is_verify").equals("true")) {
                                payment_btn.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(BookingSummaryActivity.this, "To book car you have to become verified User.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
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

                for (Map.Entry<String, String> entry : CarIds.entrySet()) {

                    // if give value is equal to value from entry
                    // print the corresponding key
                    if (entry.getKey().equals(model_name)) {
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
                booking.put("model_name", model_name);
                booking.put("mobile", mAuth.getCurrentUser().getPhoneNumber());
                booking.put("car_image", getIntent().getStringExtra("image"));
//                booking.put("car_id", car_id[0].toString());
//                Log.i("passed", car_id[0].toString());

                db.collection("Cars").document(car_id[0]).update("available_flag", "false");

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
                Toast.makeText(BookingSummaryActivity.this, "Booked is done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MyBookingActivity.class);
                intent.putExtra("brandName", getIntent().getStringExtra("brandName"));
                intent.putExtra("modelName", getIntent().getStringExtra("modelName"));
                intent.putExtra("startDate", String.valueOf(start_date.getText().toString()));
                intent.putExtra("endDate", String.valueOf(end_date.getText().toString()));
                intent.putExtra("startTime", String.valueOf(start_time.getText().toString()));
                intent.putExtra("endTime", String.valueOf(end_time.getText().toString()));
                intent.putExtra("price", totalFair.getText().toString());
                intent.putExtra("carid", car_id[0]);
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

        perhourcost = cost / 24;
        hourcost = perhourcost * Integer.parseInt(hours);
        calTotalCost = (cost * Integer.parseInt(days)) + hourcost;

        dpFair.setText("800");
        refundableFair.setText("2500");
        baseFair.setText("Rs." + String.valueOf(calTotalCost));

        totalAmount = calTotalCost + Integer.parseInt(String.valueOf(dpFair.getText())) + Integer.parseInt(String.valueOf(refundableFair.getText()));
        dpFair.setText("Rs.800");
        refundableFair.setText("Rs.2500");
        totalFair.setText(String.valueOf(totalAmount));
        totalFair.setText(String.valueOf(totalAmount));
        totalFair.setText("Rs." + String.valueOf(totalAmount));
    }

}