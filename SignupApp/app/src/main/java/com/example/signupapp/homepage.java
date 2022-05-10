package com.example.signupapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;


public class homepage extends AppCompatActivity {

    String items[] = {"London", "Paris", "New York", "Silicon Valley","Las Vegas", "Los Angeles", "Tokyo", "Seattle",
            "Dubai", "Chicago", "Toronto", "Moscow","Atlanta", "Miami", "Bangkok", "Singapore"};

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    ImageView backtologin, ProfileBtn;

    TextView date_txt, start_time_txt,end_time_txt;
    // hour and minute
    int hour, minute;

    //Recyclerview object and CarAdapter object
    RecyclerView Carrcv;
    CarAdapter adapter;

    Button selectdatetimebtn, find_cars_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Carrcv = (RecyclerView) findViewById(R.id.recyclerView1);
        Carrcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CarAdapter(dataqueue());
        Carrcv.setAdapter(adapter);

        find_cars_btn = findViewById(R.id.find_cars_btn);
        backtologin = findViewById(R.id.home_back_btn);
        selectdatetimebtn = findViewById(R.id.selectdatetime);
        date_txt = findViewById(R.id.date_txt);
        start_time_txt = findViewById(R.id.starttime_txt);
        end_time_txt = findViewById(R.id.endtime_txt);

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);

        // initializing adapter items
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        autoCompleteTextView.setAdapter(adapterItems);

        // perform on when onItemClick is called
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+ item, Toast.LENGTH_SHORT).show();
            }
        });



        // back to login screen
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage.this, LoginActivity.class));
            }
        });

        // back to login screen
        find_cars_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(homepage.this, FindCarActivity.class);
               intent.putExtra("location", autoCompleteTextView.getText().toString());
               intent.putExtra("trip_dates", date_txt.getText().toString());
               intent.putExtra("startingtime", start_time_txt.getText().toString());
               intent.putExtra("endingtime", end_time_txt.getText().toString());
               startActivity(intent);
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
        setDatePicker();
    }


    // Model For RecyclerView
    private ArrayList<Model> dataqueue() {
        ArrayList<Model> holder = new ArrayList<>();

        Model mobj1 = new Model();
        mobj1.setBrandTitle("Hyundai");
        mobj1.setModelTitle("Grand i10");
        mobj1.setdTitle1("diesel");
        mobj1.setdTitle2("manual");
        mobj1.setdTitle3("5 seat");
        mobj1.setPriceTag("$1,944");
        mobj1.setImgId(R.drawable.car1);
        mobj1.setBookBtn("Book");

        holder.add(mobj1);

        Model mobj2 = new Model();
        mobj2.setBrandTitle("Hyundai");
        mobj2.setModelTitle("Grand i10");
        mobj2.setdTitle1("diesel");
        mobj2.setdTitle2("automatic");
        mobj2.setdTitle3("6 seat");
        mobj2.setPriceTag("$1,944");
        mobj2.setImgId(R.drawable.car1);
        mobj2.setBookBtn("Book");


        holder.add(mobj2);

        Model mobj3 = new Model();
        mobj3.setBrandTitle("Hyundai");
        mobj3.setModelTitle("Grand i10");
        mobj3.setdTitle1("diesel");
        mobj3.setdTitle2("manual");
        mobj3.setdTitle3("5 seat");
        mobj3.setPriceTag("$1,944");
        mobj3.setImgId(R.drawable.car1);
        mobj3.setBookBtn("Book");


        holder.add(mobj3);


        Model mobj4 = new Model();
        mobj4.setBrandTitle("Hyundai");
        mobj4.setModelTitle("Grand i10");
        mobj4.setdTitle1("petrol");
        mobj4.setdTitle2("automatic");
        mobj4.setdTitle3("5 seat");
        mobj4.setPriceTag("$1,944");
        mobj4.setImgId(R.drawable.car1);
        mobj4.setBookBtn("Book");


        holder.add(mobj4);

        Model mobj5 = new Model();
        mobj5.setBrandTitle("Hyundai");
        mobj5.setModelTitle("Grand i10");
        mobj5.setdTitle1("petrol");
        mobj5.setdTitle2("manual");
        mobj5.setdTitle3("7 seat");
        mobj5.setPriceTag("$1,944");
        mobj5.setImgId(R.drawable.car1);
        mobj5.setBookBtn("Book");

        holder.add(mobj5);

        Model mobj6 = new Model();
        mobj6.setBrandTitle("Hyundai");
        mobj6.setModelTitle("Grand i10");
        mobj6.setdTitle1("petrol");
        mobj6.setdTitle2("manual");
        mobj6.setdTitle3("5 seat");
        mobj6.setPriceTag("$1,944");
        mobj6.setImgId(R.drawable.car1);
        mobj6.setBookBtn("Book");

        holder.add(mobj6);


        Model mobj7 = new Model();
        mobj7.setBrandTitle("Hyundai");
        mobj7.setModelTitle("Grand i10");
        mobj7.setdTitle1("diesel");
        mobj7.setdTitle2("manual");
        mobj7.setdTitle3("7 seat");
        mobj7.setPriceTag("$1,944");
        mobj7.setImgId(R.drawable.car1);
        mobj7.setBookBtn("Book");


        holder.add(mobj7);

        Model mobj8 = new Model();
        mobj8.setBrandTitle("Hyundai");
        mobj8.setModelTitle("Grand i10");
        mobj8.setdTitle1("diesel");
        mobj8.setdTitle2("manual");
        mobj8.setdTitle3("5 seat");
        mobj8.setPriceTag("$1,944");
        mobj8.setImgId(R.drawable.car1);
        mobj8.setBookBtn("Book");

        holder.add(mobj8);

        Model mobj9 = new Model();
        mobj9.setBrandTitle("Hyundai");
        mobj9.setModelTitle("Grand i10");
        mobj9.setdTitle1("diesel");
        mobj9.setdTitle2("manual");
        mobj9.setdTitle3("6 seat");
        mobj9.setPriceTag("$1,944");
        mobj9.setImgId(R.drawable.car1);
        mobj9.setBookBtn("Book");

        holder.add(mobj9);


        Model mobj10 = new Model();
        mobj10.setBrandTitle("Hyundai");
        mobj10.setModelTitle("Grand i10");
        mobj10.setdTitle1("petrol");
        mobj10.setdTitle2("manual");
        mobj10.setdTitle3("5 seat");
        mobj10.setPriceTag("$1,944");
        mobj10.setImgId(R.drawable.car1);
        mobj10.setBookBtn("Book");


        holder.add(mobj10);

        return holder;
    }

    private void setDatePicker(){
        // material date picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("SELECT DATE");
        final MaterialDatePicker materialDatePicker = builder.build();

        // datetime picker
        selectdatetimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                date_txt.setText(materialDatePicker.getHeaderText());
                //Toast.makeText(homepage.this, "date: "+materialDatePicker.getHeaderText(), Toast.LENGTH_SHORT);
                setTimePicker();
            }
        });
    }


    // TimePicker for Starting Time
    private void setTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                // Initialize time picker dialog
                homepage.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // Initialize hour and minute
                        hour = selectedHour;
                        minute = selectedMinute;
                        //INitialize calendar
                        Calendar calendar = Calendar.getInstance();
                        // set hour and minute
                        calendar.set(0,0,0,selectedHour, selectedMinute);
                        //set selected time on text view
                        start_time_txt.setText(DateFormat.format("hh:mm aa", calendar));
                        setTimePicker2();
                        //Toast.makeText(homepage.this, "time: "+DateFormat.format("hh:mm aa", calendar), Toast.LENGTH_SHORT);
                    }
                }, 12, 0, false
        );
        // display previous selected time
        timePickerDialog.updateTime(hour, minute);
        timePickerDialog.setMessage("Start Time");
        // show dialog
        timePickerDialog.show();
    }

    //TimePicker for Ending Time
    private void setTimePicker2() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                // Initialize time picker dialog
                homepage.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        // Initialize hour and minute
                        hour = selectedHour;
                        minute = selectedMinute;
                        //INitialize calendar
                        Calendar calendar = Calendar.getInstance();
                        // set hour and minute
                        calendar.set(0,0,0,selectedHour, selectedMinute);
                        //set selected time on text view
                        end_time_txt.setText(DateFormat.format("hh:mm aa", calendar));
                        //Toast.makeText(homepage.this, "time: "+DateFormat.format("hh:mm aa", calendar), Toast.LENGTH_SHORT);
                    }
                }, 12, 0, false
        );
        // display previous selected time
        timePickerDialog.updateTime(hour, minute);
        timePickerDialog.setMessage("End Time");
        // show dialog
        timePickerDialog.show();
    }


}