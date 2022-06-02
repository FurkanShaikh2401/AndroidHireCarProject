package com.example.HireCar;

import  static com.example.HireCar.DatabaseFiles.Constants.LOCATION_TABLE;

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
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class homepage extends AppCompatActivity {

    String items[] = {"Paldi Cross Roads, Kocharab, Paldi, Ahmedabad, Gujarat",
            "Shivranjani Cross Road, Jodhpur Village, Ahmedabad, Gujarat",
            "Shyamal Cross Road, Shyamal, Ahmedabad, Gujarat",
            "Panchvati Cir, Ellisbridge, Ahmedabad, Gujarat",
            "Sardar Vallabhbhai Patel International Airport, Hansol, Ahmedabad, Gujarat",
            "Vastrapur Lake Garden, Sargam Marg, Vastrapur, Ahmedabad, Gujarat",
            "Prahaladnagar Garden, Prahlad Nagar, Ahmedabad, Gujarat",
            "Sanand Chokdi, Sarkhej, Sarkhej-Okaf, Gujarat",
            "Thaltej chokdi, Thaltej Road, Bhaikakanagar, Thaltej, Ahmedabad, Gujarat",
            "ISKCON Temple near Sarkhej - Gandhinagar Highway, Bodakdev, Ahmedabad, Gujarat"};

    AutoCompleteTextView autoCompleteTextView, autoCompleteTextView2;
    ArrayAdapter<String> adapterItems;
    public List<String> locationlist=new ArrayList<>();
    public String loc[];
    ImageView backtologin, ProfileBtn;

    TextView start_time_txt,end_time_txt, start_date, end_date;
    // hour and minute
    int hour, minute;

    //Recyclerview object and CarAdapter object
    RecyclerView Carrcv;
    CarAdapter adapter;
    FirebaseAuth mAuth;
    Button datetimebtn1, datetimebtn2, find_cars_btn;

    DBHelper dbHelper;
    SQLiteDatabase db;
    String locations[],locations1[];

    FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    DocumentReference ref = fdb.collection("Locations").document("5LMGsJVhGX66jjG7O5w8");



//    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // initializing fields
        find_cars_btn = findViewById(R.id.find_cars_btn);
        backtologin = findViewById(R.id.home_back_btn);
        datetimebtn1 = findViewById(R.id.selectdatetime1);
        datetimebtn2 = findViewById(R.id.selectdatetime2);
        start_time_txt = findViewById(R.id.starttime_txt);
        end_time_txt = findViewById(R.id.endtime_txt);
        start_date = findViewById(R.id.startdate_txt);
        end_date = findViewById(R.id.enddate_txt);
        ProfileBtn = findViewById(R.id.profile_btn);

        mAuth=FirebaseAuth.getInstance();
        String c_user=mAuth.getCurrentUser().getPhoneNumber();
        // recyclerview and adapter initializing
        Carrcv = (RecyclerView) findViewById(R.id.recyclerView1);
        Carrcv.setLayoutManager(new LinearLayoutManager(this));

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        autoCompleteTextView2 = findViewById(R.id.auto_complete_txt2);

//        dbHelper = new DBHelper(this);
//        db=dbHelper.getWritableDatabase();
////        db.openOrCreateDatabase("CarRental", null,null);
//        Cursor cursor=db.rawQuery("SELECT * FROM Location", null);
//        locations=new String[cursor.getCount()];
//
//        locations1=new String[cursor.getCount()];
//        cursor.moveToFirst();
//        for(int i=0;i<locations.length;i++){
//                locations[i]= cursor.getString(1);
//        }
//        ArrayAdapter<String> adp=new ArrayAdapter<~>(this, android.R.layout.)
//        ArrayList<String> locationn;
//        locations=dbHelper.getLoc();
        adapter = new CarAdapter(dataqueue(), getApplicationContext());
        Carrcv.setAdapter(adapter);



//        String loc[]= {"Paldi Cross Roads, Kocharab, Paldi, Ahmedabad, Gujarat",
//                "Shivranjani Cross Road, Jodhpur Village, Ahmedabad, Gujarat"};

//        SQLiteDatabase MyDB=this.getWritableDatabase();
//        Cursor cursor=MyDB.rawQuery("SELECT * FROM Location",null);
////        loc=new String[cursor.getCount()];
//        int p=cursor.getCount();
//        cursor.moveToFirst();
//        for(int i=0;i<p;i++){
//            loc[i]=cursor.getString(0);
//        }
//        dbHelper.getLoc();
//        String locn[]=dbHelper.getplant();

//        DBHelper dbHelper11 = new DBHelper(this);
//        Cursor res=dbHelper11.getplant();
//        StringBuffer buffer=new StringBuffer();
//        if(res.getCount() == 0){
//            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
//            return;
//
//        }else {
//
//            while (res.moveToNext()){
//                buffer.append(res.getString(0));
//            }
//        }
//        Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show();

        displayLocationData();
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,locationlist);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView2.setAdapter(adapterItems);



        // perform on when onItemClick is called
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item1: "+ item, Toast.LENGTH_SHORT).show();
            }
        });


        // perform on when onItemClick is called
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item2: "+ item, Toast.LENGTH_SHORT).show();
            }
        });


        // creating the Calendar instance
//        Calendar calendar = Calendar.getInstance();
//
//        // set the default system date for today's date
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String date = dateFormat.format(calendar.getTime());
//        Log.i("todaydate", String.valueOf(date));

        // back to login screen
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage.this, LoginActivity.class));
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
               Intent intent = new Intent(homepage.this, FindCarActivity.class);
               intent.putExtra("pickuplocation", autoCompleteTextView.getText().toString());
               intent.putExtra("droplocation", autoCompleteTextView2.getText().toString());
               intent.putExtra("startdate", start_date.getText().toString());
               intent.putExtra("enddate", end_date.getText().toString());
               intent.putExtra("startingtime", start_time_txt.getText().toString());
               intent.putExtra("endingtime", end_time_txt.getText().toString());
               startActivity(intent);
            }
        });
       // setDatePicker();
    }

    private void displayLocationData() {
//        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if(documentSnapshot.exists()){
//                    String name = documentSnapshot.getString("Name");
//                    Toast.makeText(homepage.this, name, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(homepage.this, e.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Location").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                locationlist.clear();
                for (DocumentSnapshot s : value) {
                    locationlist.add(s.getString("Name") );

                }

                Toast.makeText(homepage.this, locationlist.toString(), Toast.LENGTH_SHORT).show();
            }
        });
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
        mobj2.setBrandTitle("Maruti");
        mobj2.setModelTitle("Suzuki");
        mobj2.setdTitle1("petrol");
        mobj2.setdTitle2("automatic");
        mobj2.setdTitle3("6 seat");
        mobj2.setPriceTag("$2,250");
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

    // First DateTime Picker
    private void selectFirstDateTimePicker(){
        // material date picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT START DATE");

        final MaterialDatePicker materialDatePicker = builder.build();

        // datetime picker
        datetimebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Log.i("date1: ", materialDatePicker.getHeaderText());
//                selectSecondDate();
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis((Long) selection);
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate  = format.format(calendar.getTime());

//               showdate1.setText(formattedDate);
                start_date.setText(formattedDate);
                Log.i("fdate: ", formattedDate);
                FirstTimePicker();

            }
        });
    }

    // First Time Picker
    private void FirstTimePicker(){
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
                        calendar.set(0,0,0,selectedHour, selectedMinute);

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
    private void selectSecondDateTimePicker(){
        // material date picker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT END DATE");

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
                String formattedDate  = format.format(calendar.getTime());

                end_date.setText(formattedDate);
                Log.i("sdate: ", formattedDate);
                SecondTimePicker();
            }
        });
    }

    // Second Time Picker
    private void SecondTimePicker(){
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
                        calendar.set(0,0,0,selectedHour, selectedMinute);

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
    private void calculateDateDuration(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date1 = dateFormat.parse(String.valueOf(start_date.getText()));
            Date date2 = dateFormat.parse(String.valueOf(end_date.getText()));

            long diff = Math.abs(date1.getTime() - date2.getTime());
            long diff2 = (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            int diff3 = (int) (diff2);

            String finaldate = String.valueOf(diff3);
            Log.i("Final", finaldate);
            calculateTimeDuration();


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Calculate Time Duration
    private void calculateTimeDuration(){

        Toast.makeText(homepage.this, "calculate time is called...", Toast.LENGTH_SHORT).show();

        String date1 = start_date.getText() + " " + start_time_txt.getText();
        String date2 = end_date.getText() + " " + end_time_txt.getText();

        String time1 = (String) start_time_txt.getText();
        String time2 = (String) end_time_txt.getText();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");

        try {
            Date ddate1 = simpleDateFormat.parse(time1);
            Date ddate2 = simpleDateFormat.parse(time2);

            long diff = ddate2.getTime() - ddate1.getTime();
            int days = (int) (diff/( 1000 * 60 * 60 * 24));
            int hhours = (int) ((diff - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
            int min = (int) (diff - (1000*60*60*24*days) - (1000*60*60*hhours)) / (1000*60);
            hhours = (hhours < 0 ? -hhours : hhours);

            Log.i("hours", String.valueOf(hhours));
            Log.i("min", String.valueOf(min));
            Log.i("fhourmin",String.valueOf(hhours)+":"+String.valueOf(min));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.i("ldate1", date1);
        Log.i("ldate2", date2);

    }




/*
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
                String[] dates = materialDatePicker.getHeaderText().split(" ");

                Log.i("dates", String.valueOf(dates));
                Log.i("first:", dates[0]);
                Log.i("second:", dates[1]);
                Log.i("third:", dates[2]);
                Log.i("fourth:", dates[3]);
                Log.i("fifth:", dates[4]);
                Log.i("sixth:", dates[5]);
                Log.i("seven:", dates[6]);


                String fdate = dates[0] + dates[1] + String.valueOf(Calendar.getInstance().getWeekYear());
                String sdate = dates[3] + dates[4] + String.valueOf(Calendar.getInstance().getWeekYear());

                Log.i("FirstDate: ", fdate);
                Log.i("SecondDate: ", sdate);

                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis(Long.parseLong(fdate));
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate  = format.format(calendar.getTime());

                Log.i("final: ", formattedDate);

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
        timePickerDialog.setMessage("Pickup Time");
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
        timePickerDialog.setMessage("Drop Time");
        // show dialog
        timePickerDialog.show();
    }
*/

}