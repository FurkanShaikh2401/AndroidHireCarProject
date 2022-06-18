package com.example.HireCar;

import static com.example.HireCar.DatabaseFiles.Constants.LOCATION_TABLE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.HireCar.DatabaseFiles.DBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class homepage extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView, autoCompleteTextView2;
    ArrayAdapter<String> adapterItems;
    public List<String> locationlist = new ArrayList<>();
    public String loc[];
    ImageView ProfileBtn;

    TextView start_time_txt, end_time_txt, start_date, end_date;
    // hour and minute
    int hour, minute;
    String finalDays, finalHours;

    //Recyclerview object and CarAdapter object
    RecyclerView Carrcv;
    CarAdapter adapter;
    FirebaseAuth mAuth;
    Button datetimebtn1, datetimebtn2, find_cars_btn;

    DBHelper dbHelper;
    SQLiteDatabase db;
    String locations[], locations1[];

    FirebaseFirestore fdb = FirebaseFirestore.getInstance();


    @Override
    public void onBackPressed() {
        finishAffinity();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // initializing fields
        find_cars_btn = findViewById(R.id.find_cars_btn);
        datetimebtn1 = findViewById(R.id.selectdatetime1);
        datetimebtn2 = findViewById(R.id.selectdatetime2);
        start_time_txt = findViewById(R.id.starttime_txt);
        end_time_txt = findViewById(R.id.endtime_txt);
        start_date = findViewById(R.id.startdate_txt);
        end_date = findViewById(R.id.enddate_txt);
        ProfileBtn = findViewById(R.id.profile_btn);


        mAuth = FirebaseAuth.getInstance();
        String c_user = mAuth.getCurrentUser().getPhoneNumber();
        // recyclerview and adapter initializing
//        Carrcv = (RecyclerView) findViewById(R.id.recyclerView1);
//        Carrcv.setLayoutManager(new LinearLayoutManager(this));

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        autoCompleteTextView2 = findViewById(R.id.auto_complete_txt2);


        displayLocationData();
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, locationlist);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView2.setAdapter(adapterItems);


        // perform on when onItemClick is called
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });


        // perform on when onItemClick is called
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });


        // Profile Activity Setting
        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage.this, ProfileSettingsActivity.class);
                startActivity(intent);
            }
        });


        datetimebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFirstDateTimePicker();
            }
        });

        datetimebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectSecondDateTimePicker();
            }
        });


        // findcars button click
        find_cars_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (autoCompleteTextView.getText().toString().isEmpty()) {
                    Toast.makeText(homepage.this, "Please select Pickup Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (autoCompleteTextView2.getText().toString().isEmpty()) {
                    Toast.makeText(homepage.this, "Please select Drop Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (start_date.getText().toString().equals("No Date Selected")) {
                    Toast.makeText(homepage.this, "Please select Start Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (end_date.getText().toString().equals("No Date Selected")) {
                    Toast.makeText(homepage.this, "Please select End Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (start_time_txt.getText().toString().equals("No Time Selected")) {
                    Toast.makeText(homepage.this, "Please select Start Time", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (end_time_txt.getText().toString().equals("No Time Selected")) {
                    Toast.makeText(homepage.this, "Please select End Time", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(homepage.this, FindCarActivity.class);
                intent.putExtra("pickuplocation", autoCompleteTextView.getText().toString());
                intent.putExtra("droplocation", autoCompleteTextView2.getText().toString());
                intent.putExtra("startdate", start_date.getText().toString());
                intent.putExtra("enddate", end_date.getText().toString());
                intent.putExtra("startingtime", start_time_txt.getText().toString());
                intent.putExtra("endingtime", end_time_txt.getText().toString());
                intent.putExtra("finaldays", finalDays);
                intent.putExtra("finalhours", finalHours);
                startActivity(intent);
            }
        });
    }

    private void displayLocationData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Location").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                locationlist.clear();
                for (DocumentSnapshot s : value) {
                    locationlist.add(s.getString("Name"));

                }

            }
        });
    }


    // First DateTime Picker
    private void selectFirstDateTimePicker() {

        CalendarConstraints.Builder calendarConstraint = new CalendarConstraints.Builder();
        calendarConstraint.setValidator(DateValidatorPointForward.now());


        // material date picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT START DATE");

        builder.setCalendarConstraints(calendarConstraint.build());

        final MaterialDatePicker materialDatePicker = builder.build();

        materialDatePicker.show(getSupportFragmentManager(), "Date Picker");

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Log.i("date1: ", materialDatePicker.getHeaderText());

                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis((Long) selection);
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = format.format(calendar.getTime());


                start_date.setText(formattedDate);
                Log.i("fdate: ", formattedDate);
                FirstTimePicker();

            }
        });
    }

    // First Time Picker
    private void FirstTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                // Initialize time picker dialog
                homepage.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // Initialize hour and minute
                        hour = selectedHour;
                        minute = selectedMinute;

                        //Initialize calendar
                        Calendar calendar = Calendar.getInstance();

                        // set hour and minute
                        calendar.set(0, 0, 0, selectedHour, selectedMinute);

                        //set selected time on text view
                        start_time_txt.setText(DateFormat.format("hh:mm aa", calendar));

                        //Toast.makeText(homepage.this, "time: "+DateFormat.format("hh:mm aa", calendar), Toast.LENGTH_SHORT);
                    }
                }, 12, 0, false
        );

        // display previous selected time
        timePickerDialog.updateTime(hour, minute);
        timePickerDialog.setMessage("Pickup Time");

        // show dialog
        timePickerDialog.show();
    }

    // Second DateTime Picker
    private void selectSecondDateTimePicker() {

        CalendarConstraints.Builder calendarConstraint = new CalendarConstraints.Builder();
        calendarConstraint.setValidator(DateValidatorPointForward.now());


        // material date picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT END DATE");
        builder.setCalendarConstraints(calendarConstraint.build());
        final MaterialDatePicker materialDatePicker = builder.build();

        // datetime picker
        materialDatePicker.show(getSupportFragmentManager(), "Date Picker");

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Log.i("date2: ", materialDatePicker.getHeaderText());

                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis((Long) selection);
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = format.format(calendar.getTime());

                end_date.setText(formattedDate);
                Log.i("sdate: ", formattedDate);
                SecondTimePicker();
            }
        });
    }

    // Second Time Picker
    private void SecondTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                // Initialize time picker dialog
                homepage.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // Initialize hour and minute
                        hour = selectedHour;
                        minute = selectedMinute;

                        //Initialize calendar
                        Calendar calendar = Calendar.getInstance();

                        // set hour and minute
                        calendar.set(0, 0, 0, selectedHour, selectedMinute);

                        //set selected time on text view
                        end_time_txt.setText(DateFormat.format("hh:mm aa", calendar));

                        calculateDateDuration();
                        //Toast.makeText(homepage.this, "time: "+DateFormat.format("hh:mm aa", calendar), Toast.LENGTH_SHORT);
                    }
                }, 12, 0, false
        );

        // display previous selected time
        timePickerDialog.updateTime(hour, minute);
        timePickerDialog.setMessage("Drop Time");

        // show dialog
        timePickerDialog.show();
    }

    // Calculate Date Duration
    private void calculateDateDuration() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date1 = dateFormat.parse(String.valueOf(start_date.getText()));
            Date date2 = dateFormat.parse(String.valueOf(end_date.getText()));

            long diff = Math.abs(date1.getTime() - date2.getTime());
            long diff2 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            int diff3 = (int) (diff2);

            String finaldate = String.valueOf(diff3);
            Log.i("Final", finaldate);
            finalDays = finaldate;
            calculateTimeDuration();


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Calculate Time Duration
    private void calculateTimeDuration() {


        String date1 = start_date.getText() + " " + start_time_txt.getText();
        String date2 = end_date.getText() + " " + end_time_txt.getText();

        String time1 = (String) start_time_txt.getText();
        String time2 = (String) end_time_txt.getText();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");

        try {
            Date ddate1 = simpleDateFormat.parse(time1);
            Date ddate2 = simpleDateFormat.parse(time2);

            long diff = ddate2.getTime() - ddate1.getTime();
            int days = (int) (diff / (1000 * 60 * 60 * 24));
            int hhours = (int) ((diff - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
            int min = (int) (diff - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hhours)) / (1000 * 60);
            hhours = (hhours < 0 ? -hhours : hhours);

            Log.i("hours", String.valueOf(hhours));
            Log.i("min", String.valueOf(min));
            Log.i("fhourmin", String.valueOf(hhours) + ":" + String.valueOf(min));
            finalHours = String.valueOf(hhours);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.i("ldate1", date1);
        Log.i("ldate2", date2);

    }


}